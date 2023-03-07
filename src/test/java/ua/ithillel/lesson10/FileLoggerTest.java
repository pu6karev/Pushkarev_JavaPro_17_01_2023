package ua.ithillel.lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoggerTest {

    @Test
    public void shouldCreateFileLogger() {
        var config = new FileLoggerConfiguration("src\\main\\java\\storage\\info.log", LoggingLevel.INFO, 1000);
        FileLogger fileLogger = new FileLogger(config);

        assertEquals("src\\main\\java\\storage\\info.log", config.getPath());
        assertEquals(1000, config.getMaxFileSize());
    }

    @Test
    public void shouldLoader() {
        FileLoggerConfigurationLoader fileLoader = new FileLoggerConfigurationLoader();
        FileLoggerConfiguration flc = fileLoader.load();

        assertEquals("src\\main\\java\\storage\\fromFile.log", flc.getPath());
        assertEquals(100, flc.getMaxFileSize());
    }
}
