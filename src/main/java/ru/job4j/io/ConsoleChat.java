package ru.job4j.io;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        /*содержит логику чата*/
        Scanner scanner = new Scanner(System.in);
        List<String> answers = readPhrases();
        boolean continuousWork = true;
        boolean isItEnd = false;
        List<String> chat = new ArrayList<>();
        do {
            StringBuilder ask = new StringBuilder();
            ask.append(scanner.nextLine());
            if (OUT.contentEquals(ask)) {
                continuousWork = false;
                isItEnd = true;
                chat.add(ask.toString());
            }
            if (STOP.contentEquals(ask)) {
                continuousWork = false;
                chat.add(ask.toString());
            }
            if (CONTINUE.contentEquals(ask)) {
                continuousWork = true;
            }
            if (continuousWork) {
                String answer = answers.get((int) (Math.random() * (answers.size())));
                chat.add(ask.toString());
                chat.add(answer);
                System.out.println(answer);
            }
        } while (!isItEnd);
        saveLog(chat);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        /*читает фразы из файла*/
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            input.lines().forEach(phrases::add);
            saveLog(phrases);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path))) {
            for (String str : log) {
                output.write(str);
                output.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error argument.");
        }
        ConsoleChat consoleChat = new ConsoleChat(args[0], args[1]);
        consoleChat.run();
    }
}
