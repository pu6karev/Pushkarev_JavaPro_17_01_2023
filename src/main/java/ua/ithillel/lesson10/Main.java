package ua.ithillel.lesson10;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // --- создаем конфигурационые файлы
        FileLoggerConfiguration configInfo = new FileLoggerConfiguration("logInfo", LoggingLevel.INFO, 1000);
        FileLoggerConfiguration configDebug = new FileLoggerConfiguration("logDebug", LoggingLevel.DEBUG, 1000);

        // --- создаем объект, формирующий файл для логгирования типа INFO
        FileLogger fileInfo = new FileLogger(configInfo);
        try{
            fileInfo.makeRecord("first");
            fileInfo.makeRecord("second");
        } catch (FileMaxSizeReachedException fmse){
            fmse.printStackTrace();
        }

        // --- создаем объект, формирующий файл для логгирования типа DEBUG
        FileLogger fileDebug = new FileLogger(configDebug);
        try{
            fileDebug.makeRecord("first");
            fileDebug.makeRecord("second");
        } catch (FileMaxSizeReachedException fmse){
            fmse.printStackTrace();
        }
    }
}