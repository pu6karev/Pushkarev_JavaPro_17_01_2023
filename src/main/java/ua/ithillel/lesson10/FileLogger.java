package ua.ithillel.lesson10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class FileLogger {

    FileLoggerConfiguration config;
    PrintWriter pw;

    public FileLogger(FileLoggerConfiguration config) throws FileNotFoundException {
        this.config = config;
        pw = new PrintWriter(config.getFile());
    }

    // --- запись в лог файл, в зависимости от заданного уровня логгирования
    public void makeRecord(String notification) throws FileMaxSizeReachedException{

        switch (config.getLogLevel()){
            case DEBUG: debug(notification);
            case INFO : info(notification);
        }
    }

    public void debug(String notification) throws FileMaxSizeReachedException {
        File file = config.getFile();
        if(file.length() >= config.getMaxFileSize()){
            String txt = " cur.size = " + file.length() + " max.size = " + file.length() + " path:" + file.getPath();
            throw new FileMaxSizeReachedException(txt);
        }

        String date = String.format("%tF %<tT.%<tL", new Date());
        pw.println(date + "  DEBUG  " + notification);
        pw.flush();

    }

    public void info(String notification) throws FileMaxSizeReachedException {
        File file = config.getFile();
        if(file.length() >= config.getMaxFileSize()){
            String txt = " cur.size = " + file.length() + " max.size = " + file.length() + " path:" + file.getPath();
            throw new FileMaxSizeReachedException(txt);
        }

        String date = String.format("%tF %<tT.%<tL", new Date());
        pw.println(date + "  INFO  " + notification);
        pw.flush();
    }

}