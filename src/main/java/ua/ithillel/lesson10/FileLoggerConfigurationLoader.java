package ua.ithillel.lesson10;

import java.io.InputStream;
import java.util.Scanner;

public class FileLoggerConfigurationLoader {

    public FileLoggerConfiguration load(){

        InputStream file = FileLoggerConfiguration.class.getResourceAsStream("/logs-config.properties");
        Scanner scanner = new Scanner(file);

        String[] strFromFile = new String[3];
        int counter = 0;

        while (scanner.hasNextLine()){
            String wholeString = scanner.nextLine();
            int begId = wholeString.indexOf("=");
            strFromFile[counter++] = wholeString.substring(begId+1);
        }

        FileLoggerConfiguration flc = new FileLoggerConfiguration(strFromFile[0],
                LoggingLevel.valueOf(strFromFile[1]), Integer.parseInt(strFromFile[2]));

        return flc;
    }
}
