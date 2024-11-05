package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

        Scanner scanner = new Scanner(new File(argsName.get("path")));
        String separator = argsName.get("delimiter");
        List<StringBuilder> table = new ArrayList<>();
        boolean first = true;
        List<String> filter = List.of(argsName.get("filter").split(","));
        List<Integer> pattern = new ArrayList<>(filter.size());
        while (scanner.hasNextLine()) {
            StringBuilder row = new StringBuilder();
            List<String> strList = List.of(scanner.nextLine().split(separator));

            if (first) {
                first = false;
                for (int i = 0; i < strList.size(); i++) {
                    for (int j = 0; j < filter.size(); j++) {
                        if (strList.get(i).equals(filter.get(j))) {
                            if (i == 0) {
                                pattern.add(i);
                            } else {
                                if (pattern.size() < j) {
                                    pattern.add(i);
                                } else {
                                    pattern.add(j, i);
                                }
                            }
                        }
                    }
                }
                for (Integer integer : pattern) {
                    if (row.toString().equals("")) {
                        row.append(strList.get(integer));
                    } else {
                        row.append(separator).append(strList.get(integer));
                    }
                }

            } else {
                for (Integer integer : pattern) {
                    if (row.toString().equals("")) {
                        row.append(strList.get(integer));
                    } else {
                        row.append(separator).append(strList.get(integer));
                    }
                }
            }
            table.add(row);
        }

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
