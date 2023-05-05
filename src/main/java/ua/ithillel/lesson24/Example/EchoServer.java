package ua.ithillel.lesson24.Example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket = serverSocket.accept();

        OutputStream write = clientSocket.getOutputStream();
        InputStream read = clientSocket.getInputStream();

        var out = new PrintWriter(write, true);
        var in = new BufferedReader(new InputStreamReader(read));

        String inputLine;

        while ((inputLine = in.readLine()) != null){
            out.println(inputLine);
            if(inputLine.equals("exit")){
                break;
            }
            System.out.println("inputLine=" + inputLine);
        }

        serverSocket.close();
    }
}
