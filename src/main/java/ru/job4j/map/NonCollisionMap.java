package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (((float) count / (float) capacity) >= LOAD_FACTOR) {
            expand();
        }

        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> oldTableElement : table) {
            if (oldTableElement != null) {
                int newIndex = hash(Objects.hashCode(oldTableElement.key) & (newTable.length - 1));
                newTable[newIndex] = new MapEntry<>(oldTableElement.key, oldTableElement.value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int hashKey = hash(Objects.hashCode(key));
        int index = indexFor(hashKey);
        V value = null;

        if (table[index] != null) {
            int hashTableKey = hash(Objects.hashCode(table[index].key));
            if (hashKey == hashTableKey) {
                if (Objects.equals(key, table[index].key)) {
                    value = table[index].value;
                }
            }
        }

        return value;
    }

    @Override
    public boolean remove(K key) {
        int hashKey = hash(Objects.hashCode(key));
        int index = indexFor(hashKey);
        boolean rsl = false;

        if (table[index] != null) {
            int hashTableKey = hash(Objects.hashCode(table[index].key));
            if (hashKey == hashTableKey) {
                if (Objects.equals(key, table[index].key)) {
                    table[index] = null;
                    modCount++;
                    count--;
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        Iterator<K> iterator = new Iterator<>() {

            private MapEntry<K, V>[] data = table;
            private int index;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < data.length && data[index] == null) {
                    index++;
                }
                return index < data.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[index++].key;
            }
        };
        return iterator;
    }

    private static class MapEntry<K, V> {

        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
