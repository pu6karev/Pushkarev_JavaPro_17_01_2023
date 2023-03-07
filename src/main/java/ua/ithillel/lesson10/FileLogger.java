package ua.ithillel.lesson10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class FileLogger {

    FileLoggerConfiguration config;
    File file;
    PrintWriter pw;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
        file = createFile(config.getPath());

    }

    public void debug(String notification) throws FileMaxSizeReachedException {
        if(file.length() >= config.getMaxFileSize()){
            pw.close();
            String txt = " cur.size = " + file.length() + " max.size = " + file.length() + " path:" + file.getPath();
            throw new FileMaxSizeReachedException(txt);
        }

        info(notification);

        String date = String.format("%tF %<tT.%<tL", new Date());
        pw.println(date + "  DEBUG  " + notification);
        pw.flush();

    }

    public void info(String notification) throws FileMaxSizeReachedException {
        if(file.length() >= config.getMaxFileSize()){
            pw.close();
            String txt = " cur.size = " + file.length() + " max.size = " + file.length() + " path:" + file.getPath();
            throw new FileMaxSizeReachedException(txt);
        }

        String date = String.format("%tF %<tT.%<tL", new Date());
        pw.println(date + "  INFO  "  + notification);
        pw.flush();
    }

    public void debugExt(String notification) {
        if(file.length() >= config.getMaxFileSize()){
            pw.close();
            String txt = " cur.size = " + file.length() + " max.size = " + file.length() + " path:" + file.getPath();
            file = createFile(config.getPath());
        }

        infoExt(notification);

        String date = String.format("%tF %<tT.%<tL", new Date());
        pw.println(date + "  DEBUG  " + notification);
        pw.flush();

    }

    public void infoExt(String notification) {
        if(file.length() >= config.getMaxFileSize()){
            pw.close();
            String txt = " cur.size = " + file.length() + " max.size = " + file.length() + " path:" + file.getPath();
            file = createFile(config.getPath());
        }

        String date = String.format("%tF %<tT.%<tL", new Date());
        pw.println(date + "  INFO  "  + notification);
        pw.flush();
    }

    /* Создадим в директории "java" папку "storage" и в ней файл логгирования.
       Получив в качестве параметра полный путь, разделим на отдельные части созадание директорий и
       создание файла, с проверкой его существования
    */
    private File createFile(String filePath) {
        System.out.println("createFile");

        File file = new File(filePath);                         // задаем полный путь вместе с именем файла
        Path Parentpath = Paths.get(file.getParent());          // получим путь из каталогов без файла


         // --- к имени файла, перед расширением ".log" добавим дату создания файла
        String[] names = file.getName().split("\\.");             // разобъем имя файла перед и после точки
        names[0] += String.format("_%tF_%<tT.", new Date());            // добавим перед точкой дату
        String newName = file.getParent() +"\\"+ names[0] + names[1];
        String rightName = newName.replace(':', '-');    // замена двоеточия на дефис
        File file1 = new File(rightName);

        try{
            Files.createDirectories(Parentpath);                // создадим полный путь с каталогами, если он не существует
            if (!file1.exists())  file1.createNewFile();        // создадим файл, если он не существует
            pw = new PrintWriter(file1);
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return file1;
    }

}