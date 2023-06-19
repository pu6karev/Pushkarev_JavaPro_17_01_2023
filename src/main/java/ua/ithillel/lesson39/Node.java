package ua.ithillel.lesson39;

public class Node {
    private final int value;
    private Node left;          // заглушка для будущей вставки меньшего значения слева
    private Node right;         // заглушка для будущей вставки большего значения справа

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // --- при вставке числа определям влево или вправо отнести и проверяем нужно создать новый узел или заполняем текущий
    public void insert(int value) {
        if (value < this.value) {
            if (left == null) {
                left = new Node(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new Node(value);
            } else {
                right.insert(value);
            }
        }
    }

    // --- выход вначале на наименьшее левое значение при помощи рекурсии, потом идем вверх по наростающей влево/вправо
    public void traverse() {
        if (left != null) {
            left.traverse();
        }
        System.out.print(value + " ");
        if (right != null) {
            right.traverse();
        }
    }
}
