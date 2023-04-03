package ua.ithillel.Lesson18;

import lombok.*;

@Data
@Builder

public class HeroBuilder {
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

}