package ua.ithillel.lesson13;

public interface GenericHillelList<T> {

    /**
     * Додати новий елемент до списка
     */
    void add(T item);

    /**
     * Видалити елемент із списка за визначенним індексом
     * @return видалений елемент
     */
    T remove(int index);

    /**
     * Перевіряє чи є такий елемент у списку.
     * @return true - якщо є, false - якщо ні
     */
    boolean contains(T item);

    /**
     * Знайти індекс елемента у списку
     * @return індекс (0 - відповідає першому елементу), -1 - якщо не знайдено
     */
    int indexOf(T item);
    /**
     * Розмір списка
     */
    int size();
    /**
     * Повертає елемент списка за вказанним індексом
     */
    T get(int index);

    /**
     * Всі елементи списка
     */
    T[] getAll(T[] type);
    Object[] getAll();
}
