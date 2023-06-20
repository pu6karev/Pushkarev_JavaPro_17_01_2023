package ua.ithillel.lesson39;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> traverse() {
        if (root != null) {
            return root.traverse();
        }
        return new ArrayList<>();
    }

}
