package ua.ithillel.lesson39;

public class Tree {
    private Node root;

    public Tree() {
        this.root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            root.insert(value);
        }
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }
}
