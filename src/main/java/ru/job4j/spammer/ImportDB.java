package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(x -> {
                List<String> strArr = new ArrayList<>();
                Arrays.stream(x.trim().split(";", 2)).forEach(strArr::add);
                if (strArr.size() == 2) {
                    for (String str : strArr) {
                        if (str == null || "".equals(str)) {
                            throw new IllegalArgumentException("Пустой параметр");
                        }
                    }
                    users.add(new User(strArr.get(0), strArr.get(1)));
                } else {
                    System.out.println(strArr.toString());

                    throw new IllegalArgumentException("Не правильная строка данных");
                }
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users (name, email) values (?,?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}