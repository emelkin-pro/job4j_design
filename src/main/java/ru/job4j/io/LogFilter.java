package ru.job4j.io;

import java.util.Collections;
import java.util.List;
import java.io.*;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader input = new BufferedReader(new FileReader(this.file))) {
            return input.lines()
                    .filter(s -> {
                        String[] s1 = s.split(" ");
                        return s1.length > 2  && "404".equals(s1[s1.length - 2]);
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
