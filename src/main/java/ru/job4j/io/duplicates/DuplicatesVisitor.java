package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> uniqFileFileProperty = new HashMap<>();

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(attrs.size(), file.getFileName().toString());
        uniqFileFileProperty.computeIfAbsent(newFile, x -> new ArrayList<>()).add(file);

        return CONTINUE;
    }

    public void duplicatesPrinter() {
        uniqFileFileProperty.entrySet()
                .stream()
                .filter(x -> x.getValue().size() > 1)
                .forEach(x -> x.getValue()
                        .forEach(y -> System.out.println(y.toAbsolutePath())));
    }

}
