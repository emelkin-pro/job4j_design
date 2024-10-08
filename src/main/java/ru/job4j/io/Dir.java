package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/user/IdeaProjects/job4j_design");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isFile()) {
                System.out.println(subfile.getName() + " - " + subfile.length());
            } else if (subfile.isDirectory()) {
                System.out.println(subfile.getName() + " - " + subfile.getTotalSpace());
            }
        }
    }
}
