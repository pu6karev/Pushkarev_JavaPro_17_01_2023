package ua.ithillel.lesson39;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(8);
        tree.insert(10);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(14);
        tree.insert(4);
        tree.insert(7);
        tree.insert(13);

        System.out.print("Выводим в правильном порядке: ");
        tree.traverse();

    }
}
