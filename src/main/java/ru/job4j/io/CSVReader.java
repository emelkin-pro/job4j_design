package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        boolean first = true;
        Scanner scanner = new Scanner(new File(argsName.get("path")));
        String separator = argsName.get("delimiter");
        List<StringBuilder> table = new ArrayList<>();
        List<String> filter = List.of(argsName.get("filter").split(","));
        int[] pattern = new int[filter.size()];

        while (scanner.hasNextLine()) {
            StringBuilder row = new StringBuilder();
            List<String> strList = List.of(scanner.nextLine().split(separator));
            if (first) {
                first = false;
                pattern = tablePattern(filter, strList);
                addRowToTable(pattern, row, separator, strList);
            } else {
                addRowToTable(pattern, row, separator, strList);
            }
            table.add(row);
        }
        printCSV(argsName, table);
    }

    private static int[] tablePattern(List<String> filter, List<String> strList) {
        int[] pattern = new int[filter.size()];
        for (int i = 0; i < strList.size(); i++) {
            for (int j = 0; j < filter.size(); j++) {
                if (strList.get(i).equals(filter.get(j))) {
                    if (i == 0) {
                        pattern[0] = i;
                    } else {
                        pattern[j] = i;
                    }
                }
            }
        }
        return pattern;
    }

    private static void addRowToTable(int[] pattern, StringBuilder row,
                                      String separator, List<String> strList) {
        for (int integer : pattern) {
            if (row.toString().equals("")) {
                row.append(strList.get(integer));
            } else {
                row.append(separator).append(strList.get(integer));
            }
        }
    }

    private static void printCSV(ArgsName argsName, List<StringBuilder> table) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            if ("stdout".equals(argsName.get("out"))) {
                table.forEach(output::println);
            } else {
                table.forEach(output::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void check(ArgsName argsName) throws Exception {
        Path out = Path.of(argsName.get("out"));
        Path path = Path.of(argsName.get("path"));
        if (!"stdout".equals(argsName.get("out")) && Files.isDirectory(out)) {
            throw new IllegalArgumentException("out is invalid: " + out);
        }
        if (!Files.exists(path) && !path.toString().endsWith(".csv")) {
            throw new IllegalArgumentException("path is invalid: " + path);
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        check(argsName);
        handle(argsName);
    }
}

