package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().filter(s -> !(Objects.isNull(s) || s.equals("") || s.contains("#")))
                    .forEach(s -> {
                        String[] strArr = s.split("=", 2);
                        if (strArr[0].equals("") || strArr[1].equals("")) {
                            throw new IllegalArgumentException();
                        } else {
                            this.values.put(strArr[0], strArr[1]);
                            System.out.println(strArr[0] + strArr[1]);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));

    }

}