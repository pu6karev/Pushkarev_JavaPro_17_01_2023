package ua.ithillel.lesson24_2;

import lombok.SneakyThrows;
import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson23.HeroDto;
import ua.ithillel.lesson23.HeroFabric;
import ua.ithillel.lesson23.HeroService;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

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
        serverProcess(dataSource, clientSocket);
        System.out.println("exit thread id=" + id);
    }

    public void serverProcess(DataSource dataSource, Socket clientSocket) throws IOException {
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

            HeroService heroService = HeroFabric.createServiceDao(dataSource);
            List<Hero> heroList = heroService.getByName(nameHero);

            if(heroList.size() > 0){
                System.out.println(heroList);
                out.println(heroList);
            } else {
                out.println("The name of your hero is not found, please try to type correctly");
            }

        }
    }

    public String parseName(String commandName){
        String name = null;
        if(commandName.startsWith("-name ")){
            name = commandName.substring(6);
        }
        return name;
    }

    public boolean existHero(DataSource dataSource, String nameHero){
        List<HeroDto> heroDto = HeroFabric.createService(dataSource).getHeroes();
        for (HeroDto h : heroDto) {
            if(nameHero.equals(h.getName())) return true;
        }
        return false;
    }
}
