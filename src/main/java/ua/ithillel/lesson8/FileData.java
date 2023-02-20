package ua.ithillel.lesson8;

public class FileData {
    private final String name;
    private final String path;
    private final int size;

    public FileData(String path, String name, int size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }
}
