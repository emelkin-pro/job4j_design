package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = input.readLine();
                    Pattern exit = Pattern.compile("msg=Exit");
                    Pattern hellow = Pattern.compile("msg=Hello");
                    Pattern what = Pattern.compile("msg=");
                    if (hellow.matcher(line).find()) {
                        output.write("Hello, dear friend.\r\n\r\n".getBytes());
                        output.flush();

                    }
                    if (exit.matcher(line).find()) {
                        output.write("GoodBye\r\n\r\n".getBytes());
                        output.flush();
                        server.close();
                    }
                    if (!hellow.matcher(line).find()
                            && !exit.matcher(line).find()
                            && what.matcher(line).find()) {
                        output.write("What\r\n\r\n".getBytes());
                        output.flush();
                    }

                    for (String string = input.readLine();
                         string != null && !string.isEmpty();
                         string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}