package ua.ithillel.lesson24;

import lombok.SneakyThrows;
import javax.sql.DataSource;
import java.net.Socket;

public class Runner implements Runnable {

    private static int id;
    private final DataSource dataSource;
    private final Socket clientSocket;

    public Runner(DataSource dataSource, Socket clientSocket) {
        this.dataSource = dataSource;
        this.clientSocket = clientSocket;
        id++;
    }

    @SneakyThrows
    @Override
    public void run() {
        HeroServerMulti.serverProcess(dataSource, clientSocket);
        System.out.println("exit thread id=" + id);
    }
}
