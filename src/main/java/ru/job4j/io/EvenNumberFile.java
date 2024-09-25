package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            int[] numbArray = Arrays.stream(
                            text.toString().split(System.lineSeparator()))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int numb : numbArray) {
                String evenOrNont = "нечетное";
                if (numb % 2 == 0) {
                    evenOrNont = "четное";
                }
                System.out.println(numb + " - " + evenOrNont);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}