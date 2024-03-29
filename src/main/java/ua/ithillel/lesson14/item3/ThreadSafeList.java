package ua.ithillel.lesson14.item3;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<T> implements Runnable{

    private final List<T> list;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ThreadSafeList(List<T> list) {
        this.list = list;
    }

    public void add(T value) {
        if(lock.writeLock().tryLock()){
            try {
                list.add(value);
            } finally {
                lock.writeLock().unlock();
            }
        }
        System.out.println("add value=" + value);
    }

    public T remove(int index) {
        if(lock.writeLock().tryLock()){
            try {
                list.remove(index);
            } finally {
                lock.writeLock().unlock();
            }
        }
        System.out.println("rm value=" + index);
        return null;
    }

    public T get(int index) {
        if(lock.readLock().tryLock()){
            try {
                return list.get(index);
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }

    @Override
    public void run() {

    }
}
