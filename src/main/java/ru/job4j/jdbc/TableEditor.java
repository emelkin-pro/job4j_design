package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.StringJoiner;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private Connection initConnection() throws Exception {
        Class.forName(this.properties.getProperty("driver_class"));
        String url = this.properties.getProperty("url");
        String login = this.properties.getProperty("username");
        String password = this.properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
        return this.connection;
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        );
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }

    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s",
                tableName,
                columnName,
                type
        );
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName,
                columnName
        );
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName,
                columnName,
                newColumnName
        );
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }

    }

    public String getTableScheme(String tableName) throws Exception {

        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        }
        String url = config.getProperty("url");
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        String driver = config.getProperty("driver_class");

        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        System.out.println(driver);

        TableEditor tableEditor = new TableEditor(config);

        try (Connection connection = tableEditor.initConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS demo_table(%s, %s);",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT"
                );

                tableEditor.dropTable("table1");

                System.out.println("Создание");
                tableEditor.createTable("table1");
                System.out.println(tableEditor.getTableScheme("table1"));

                System.out.println("Добавление столбца");
                tableEditor.addColumn("table1", "kek", "text");
                System.out.println(tableEditor.getTableScheme("table1"));

                System.out.println("Переименование столбца");
                tableEditor.renameColumn("table1", "kek", "lolkek");
                System.out.println(tableEditor.getTableScheme("table1"));

                System.out.println("Удаление столбца");
                tableEditor.dropColumn("table1", "lolkek");
                System.out.println(tableEditor.getTableScheme("table1"));

                System.out.println("Удаление таблицы");
                tableEditor.dropTable("table1");

            }
        }
    }
}