package ua.ithillel.lesson8;

import java.util.*;

public class FileNavigator {

    private List<FileData> totalList = new ArrayList<>();
    private Map<String, List<FileData>> map = new HashMap<>();

//    public List<FileData> getTotalList() {
//        return totalList;
//    }

    public Map<String, List<FileData>> getMap() {
        return map;
    }

    public void add(FileData fileData){
        String currentPath = fileData.getPath();

        for (Map.Entry<String, List<FileData>> me: map.entrySet()){
            if(currentPath.equals(me.getKey())){
                me.getValue().add(fileData);
                return;
            }
        }

        List<FileData> list = new ArrayList<>();
        list.add(fileData);
        map.put(currentPath, list);
    }

    public List<FileData> find(String path){

        for (String pathKey: map.keySet()){
            if(path.equals(pathKey)){
                return map.get(pathKey);
            }
        }
        return null;
    }

    public List<FileData> filterBySize(int maxSize){

        List<FileData> limitSizeList = new ArrayList<>();

        for (Map.Entry<String, List<FileData>> me: map.entrySet()){
            for (FileData fileData : me.getValue()){
                if(fileData.getSize() <= maxSize){
                    limitSizeList.add(fileData);
                }
            }
        }


        return limitSizeList;
    }

    public void remove(String path){

        for (String pathKey: map.keySet()){
            if(path.equals(pathKey)){
                remove(pathKey);
                return;
            }
        }
    }

    public Set<FileData> sortBySize(){
        Set<FileData> listSorted = new LinkedHashSet<>();

        // --- скопируем в общий список ArrayList "значения" из Map, каждое из "значений" тоже является списком файлов
        List<FileData> totalList = new ArrayList<>();
        for (Map.Entry<String, List<FileData>> me: map.entrySet()){
            totalList.addAll(me.getValue());
        }

        // --- перебираем общий список ArrayList, находим файл-объект с мин.размером файла и возвращаем его индекс.
        // --- по этому индексу вставляем копию файла в список LinkedHashSet, а из ArrayList удаляем
        while (totalList.size() > 0){
            int minId = getMinimumIndex(totalList);
            listSorted.add(totalList.get(minId));
            totalList.remove(minId);
        }

        return listSorted;
    }

    // --- перебор всех файлов списка, с проверкой размера файла и возвратом индекса с наименьшим размером файла
    private int getMinimumIndex(List<FileData> totalList){
        int index = 0;
        int minSize = totalList.get(index).getSize();

        for (int i = 1; i < totalList.size(); i++) {
            FileData fileData = totalList.get(i);
            if(fileData.getSize() < minSize){
                minSize = fileData.getSize();
                index = i;
            }
        }
        return index;
    }

}
