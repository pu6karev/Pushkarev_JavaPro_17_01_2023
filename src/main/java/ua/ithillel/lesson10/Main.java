package ua.ithillel.lesson10;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException {

        shouldOutInfo();
        shouldOutDebug();

        fileOverSize();

    }

    public static void shouldOutInfo() {
        var config = new FileLoggerConfiguration("src\\main\\java\\storage\\info.log", LoggingLevel.INFO, 1000);

        // --- создаем объект, формирующий файл для логгирования
        FileLogger fileLogger = new FileLogger(config);
        try{
            fileLogger.info("first");
            fileLogger.info("second");
        } catch (FileMaxSizeReachedException fmse){
            fmse.printStackTrace();
        }
    }

    public static void shouldOutDebug() {
        var config = new FileLoggerConfiguration("src\\main\\java\\storage\\debug.log", LoggingLevel.DEBUG, 1000);

        // --- создаем объект, формирующий файл для логгирования
        FileLogger fileLogger = new FileLogger(config);
        try{
            fileLogger.debug("first");
            fileLogger.debug("second");
        } catch (FileMaxSizeReachedException fmse){
            fmse.printStackTrace();
        }
    }

    public static void fileOverSize(){
        var config = new FileLoggerConfiguration("src\\main\\java\\storage\\outOver.log", LoggingLevel.INFO, 100);

        // --- создаем объект, формирующий файл для логгирования
        FileLogger fileLogger = new FileLogger(config);
        for (int i = 0; i < 10; i++) {
            fileLogger.infoExt("first");
            fileLogger.infoExt("second");
            // --- вводим секундную задержку, так как в имени файла будет время и они не должны перезаписаться
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }

        }

    }
}