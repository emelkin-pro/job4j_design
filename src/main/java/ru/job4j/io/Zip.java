package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Path;

public class Zip {
    private Path directory;
    private String exclude;
    private File outputFile;

    private void check(ArgsName argsName) {
        this.directory = Path.of(argsName.get("d"));
        this.exclude = argsName.get("e");
        this.outputFile = new File(argsName.get("o"));
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("directory is invalid: " + directory);
        }
        if (!this.outputFile.getName().contains(".zip")) {
            throw new IllegalArgumentException("target is invalid: " + outputFile);
        }
    }

    private void zippingFiles() {
        List<Path> filesForPack = Search.search(this.directory, path ->
                !path.toFile().getName().endsWith(exclude));
        filesForPack.forEach(System.out::println);
        this.packFiles(filesForPack, outputFile);
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                System.out.println(source.toString());
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.check(argsName);
        zip.zippingFiles();
    }
}