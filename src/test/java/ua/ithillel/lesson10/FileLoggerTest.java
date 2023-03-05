package ua.ithillel.lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoggerTest {

    @Test
    public void shouldLoader() {
        FileLoggerConfigurationLoader fileLoader = new FileLoggerConfigurationLoader();
        FileLoggerConfiguration flc = fileLoader.load();

        assertEquals("src\\main\\java\\storage\\fromFile.log", flc.getPath());
        assertEquals(100, flc.getMaxFileSize());
    }
}
