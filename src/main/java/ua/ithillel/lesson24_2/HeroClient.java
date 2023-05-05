package ua.ithillel.lesson24_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HeroClient {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        try(Socket socket = new Socket("localhost",7777);

        var write = new PrintWriter(socket.getOutputStream(), true);
        var read = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Введите имя героя через команду : -name <Имя>");
            String inputLine;
            while ((inputLine = scanner.nextLine()) != null) {
                System.out.println("Вы ввели: " + inputLine);
                write.println(inputLine);
                if (inputLine.equals("exit")) {
                    break;
                } else {
                    System.out.println("inputLine = " + read.readLine());
                }
            }
        }
    }
}
