package ua.ithillel.lesson17;

public class Hero {
    String name;
    String gender;
    String eyeColor;
    String race;
    String hairColor;
    double height;
    String publisher;
    String skinColor;
    String alignment;
    int weight;

    public Hero(String name, String gender, String eyeColor, String race, String hairColor, double height,
                String publisher, String skinColor, String alignment, int weight) {
        this.name = name;
        this.gender = gender;
        this.eyeColor = eyeColor;
        this.race = race;
        this.hairColor = hairColor;
        this.height = height;
        this.publisher = publisher;
        this.skinColor = skinColor;
        this.alignment = alignment;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getRace() {
        return race;
    }

    public String getHairColor() {
        return hairColor;
    }

    public double getHeight() {
        return height;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getAlignment() {
        return alignment;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "\nHero{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", race='" + race + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", height=" + height +
                ", publisher='" + publisher + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", alignment='" + alignment + '\'' +
                ", weight=" + weight +
                '}';
    }
}
