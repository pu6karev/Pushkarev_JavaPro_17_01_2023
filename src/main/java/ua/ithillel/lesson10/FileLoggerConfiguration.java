package ua.ithillel.lesson10;

import java.io.File;
import java.io.IOException;

public class FileLoggerConfiguration {
    private final File file;
    private final LoggingLevel eLevel;

    private final long maxFileSize;


    public FileLoggerConfiguration(String fileName, LoggingLevel eLevel, long maxFileSize) {
        this.file = createFile(fileName);
        this.eLevel = eLevel;
        this.maxFileSize = maxFileSize;
    }

    public File getFile() {
        return file;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public LoggingLevel getLogLevel() {
        return eLevel;
    }

    // --- создадим в корне проекта директорию "fileStorage" и в ней файл логгирования
    private File createFile(String fileName){
        File filePath = new File("fileStorage");
        filePath.mkdir();
        File file = new File(filePath + "\\" + fileName + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}