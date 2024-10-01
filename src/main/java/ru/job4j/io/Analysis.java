package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(
                new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target, true)
                     ))) {
            List<String> listStr = input.lines().toList();
            StringBuilder rsl = new StringBuilder();
            boolean fall = false;
            for (String str : listStr) {
                if (!fall
                        && !Objects.isNull(str)
                        && (str.contains("400") || str.contains("500"))) {
                    rsl.append(str.split(" ", 2)[1]).append(";");
                    fall = true;
                } else if (fall
                        && !Objects.isNull(str)
                        && !(str.contains("400")
                        || str.contains("500"))) {
                    rsl.append(str.split(" ", 2)[1]).append(";\n");
                    fall = false;
                }
            }
            output.write(rsl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}