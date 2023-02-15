package ua.ithillel.lesson6;

public class Article {
    private final String name;
    private Integer counter;

    public Article(String name) {
        this.name = name;
        this.counter = 1;
    }

    public void incrementCounter() {
        ++counter;
    }

    public String getName() {
        return name;
    }

    public Integer getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", counter=" + counter +
                '}';
    }
}
