package ua.ithillel.lesson17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HeroAction {
    private final List<Hero> list;

    public HeroAction() {
        this.list = new ArrayList<>();
    }

    public HeroAction(List<Hero> list) {
        this.list = list;
    }

    public void add(Hero hero){
        list.add(hero);
    }

    public List<Hero> getAll(){
        return list;
    }

    public double getAvgHeight(){

      return list.stream().filter(s -> s.getHeight() > 0)
                .mapToDouble(Hero::getHeight)
                .average().getAsDouble();
    }

    public String getHighestName(){

        return  list.stream().max(Comparator.comparingDouble(Hero::getHeight))
                .get().getName();
    }

    public String getHeaviestName(){

        return  list.stream().max(Comparator.comparingInt(Hero::getWeight))
                .get().getName();
    }

    // --- Кількість осіб в кожній гендерній групі
    public Map<String, Long> groupGender(){

        return list.stream().collect(Collectors.groupingBy(Hero::getGender, Collectors.counting()));
    }

     // --- Кількість осіб в кожному угрупуванні (добро / зло / інші)
    public Map<String, Long> groupAlignment(){

        return list.stream().collect(Collectors.groupingBy(Hero::getAlignment, Collectors.counting()));
    }

     // --- 5 назв самих популярних видавців
    public List<String> getPopularPublisher(int leaders){

        return list.stream()
                .collect(Collectors.groupingBy(Hero::getPublisher, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(leaders)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // --- 3 назви найрозповсюдженіших кольорів волосся
    public List<String> getPopularHairColor(int leaders){

        return list.stream()
                .filter(s -> !s.getHairColor().equals("-"))
                .collect(Collectors.groupingBy(Hero::getHairColor, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(leaders)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String getMostPopularEyeColor(){

        return list.stream()
                .collect(Collectors.groupingBy(Hero::getEyeColor, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

}
