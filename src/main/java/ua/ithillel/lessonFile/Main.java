package ua.ithillel.lessonFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) throws IOException {

        createFile("src\\main\\java\\storage\\text.log");
    }

    private static File createFile(String filePath) throws IOException {
        File file = new File(filePath);                             // полный путь вместе с именем файла
        System.out.println("file.exists()=" + file.exists());

        Path path = Paths.get(file.getParent());                    // получим путь из каталогов к самому файлу
        Path wholePath = Files.createDirectories(path);             // создадим путь с каталогами, если он не существует

        System.out.println("file.exists()=" + file.exists());
        System.out.println("file.getName()=" + file.getName());
        System.out.println("file.getParent()=" + file.getParent());

        if (!file.exists())  {
            file.createNewFile();
        }

        return file;
    }
}