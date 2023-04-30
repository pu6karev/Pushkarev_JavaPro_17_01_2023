package ua.ithillel.lesson24;

import org.postgresql.ds.PGSimpleDataSource;
import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson22.HeroDao;
import ua.ithillel.lesson22.HeroDaoImpl;
import ua.ithillel.lesson23.HeroDto;
import ua.ithillel.lesson23.HeroFabric;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeroServerMulti {

    private static final int NumberTry = 5;

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(NumberTry);

        // --- создаем объект DataSource и подключаемся к базе данных
        DataSource dataSource = createDataSource();
        // --- создаем серверный сокет
        ServerSocket serverSocket = new ServerSocket(8080);

        for (int i = 0; i < NumberTry; i++) {
            Socket clientSocket = serverSocket.accept();                // ожидаем клиентский запрос
            threadPool.execute(new Runner(dataSource, clientSocket));
        }

        serverSocket.close();
    }

    public static void serverProcess(DataSource dataSource, Socket clientSocket) throws IOException {
        // --- создаем поток приема данных от клиента, чтобы получить имя героя
        var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // --- создаем обратный поток для ответа клиенту
        var out = new PrintWriter(clientSocket.getOutputStream(), true);

        String getCommandName;
        while((getCommandName = in.readLine()) != null){

            String nameHero = parseName(getCommandName);
            if (nameHero == null) {
                out.println("Неправильно задан формат команды, попробуйте: -name <Имя>");
                continue;
            }

            if (nameHero.equals("exit")) break;

            // --- проверим в DTO наличие героя
            if(existHero(dataSource, nameHero)){
                // --- создаем DAO
                HeroDao heroDao = new HeroDaoImpl(dataSource);
                // --- получим полные данные по герою для сериализации
                List<Hero> names = heroDao.findByName(nameHero);
                System.out.println(names);
                out.println(names);
            } else {
                out.println("The name of your hero is not found, please try to type correctly");
            }
        }
    }


    public static DataSource createDataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dbHero");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    public static String parseName(String commandName){
        String name = null;
        if(commandName.startsWith("-name ")){
            name = commandName.substring(6);
        }
        return name;
    }

    public static boolean existHero(DataSource dataSource, String nameHero){
        List<HeroDto> heroDto = HeroFabric.createService(dataSource).getHeroes();
        for (HeroDto h : heroDto) {
            if(nameHero.equals(h.getName())) return true;
        }
        return false;
    }

}
