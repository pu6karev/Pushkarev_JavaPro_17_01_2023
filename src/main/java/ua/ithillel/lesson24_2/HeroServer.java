package ua.ithillel.lesson24_2;

import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeroServer {

    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // --- создаем объект DataSource и подключаемся к базе данных
        DataSource dataSource = createDataSource();
        // --- создаем серверный сокет
        ServerSocket serverSocket = new ServerSocket(7777);

        while (true) {
            Socket clientSocket = serverSocket.accept();                // ожидаем клиентский запрос
            threadPool.execute(new Runner(dataSource, clientSocket));
        }

        //serverSocket.close();
    }

    public static DataSource createDataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dbHero");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

}
