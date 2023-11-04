package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity == 0 ? 10 : capacity];
    }

    private void grow() {
        container = Arrays.copyOf(container, size * 2);
    }

    @Override
    public void add(T value) {
        modCount++;
        if (container.length == size) {
            grow();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        if (Objects.checkIndex(index, size) != index) {
            throw new IndexOutOfBoundsException();
        }
        if (container.length == size) {
            grow();
        }
        T rsl = container[index];
        System.arraycopy(container, index,
                container, index + 1,
                size - index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        modCount++;
        final int newSize = size - 1;
        if (Objects.checkIndex(index, size) == size) {
            throw new IndexOutOfBoundsException();
        }
        T rsl = container[index];
        if (Objects.checkIndex(index, newSize) == index) {
            System.arraycopy(container, index + 1,
                    container, index, newSize - index);
            size = newSize;
            container[size] = null;
        }
        return rsl;
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, size) != index) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }
}