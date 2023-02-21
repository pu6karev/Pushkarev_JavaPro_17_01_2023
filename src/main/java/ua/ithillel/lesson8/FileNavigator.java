package ua.ithillel.lesson8;

import java.util.*;

public class FileNavigator {

    private final List<FileData> totalList = new ArrayList<>();
    private final Map<String, List<FileData>> map = new HashMap<>();

    public Map<String, List<FileData>> getMap() {
        return map;
    }


    public void add(FileData fileData){
        String currentPath = fileData.getPath();

        for (String pathKey: map.keySet()) {
            if (map.containsKey(currentPath)) {
                map.get(currentPath).add(fileData);
                return;
            }
        }

        List<FileData> list = new ArrayList<>();
        list.add(fileData);
        map.put(currentPath, list);
    }


    public List<FileData> find(String path){
        return map.get(path);
    }


    public List<FileData> filterBySize(int maxSize){

        List<FileData> limitSizeList = new ArrayList<>();

        for (List<FileData> list: map.values()){
            for (FileData fileData : list){
                if(fileData.getSize() <= maxSize){
                    limitSizeList.add(fileData);
                }
            }
        }

        return limitSizeList;
    }


    public void remove(String path){
        map.remove(path);
    }


    public List<FileData> sortBySize(){
        List<FileData> totalList = new ArrayList<>();

        for (List<FileData> list: map.values()){
            totalList.addAll(list);
        }

        Collections.sort(totalList, new FileSizeComparator());

        return totalList;
    }


    // --- перегруженный метод для 7-го пункта с исключением, если переданный путь и путь в файле не совпадает
    public void add(String path, FileData fileData){
        String currentPath = fileData.getPath();

        if (!path.equals(currentPath)){
            throw new WrongPathException("You,ve written wrong path to the method");
        }

        add(fileData);
    }

}
