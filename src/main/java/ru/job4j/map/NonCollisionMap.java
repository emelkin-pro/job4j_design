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
        if (LOAD_FACTOR * capacity <= count) {
            expand();
        }
        int index = indexByKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexByKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean keyCompare(K key1, K key2) {
        int hash1 = hash(Objects.hashCode(key1));
        int hash2 = hash(Objects.hashCode(key2));
        boolean rsl = false;

        if (hash1 == hash2) {
            if (Objects.equals(key1, key2)) {
                rsl = true;
            }
        }
        return rsl;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> oldTableElement : table) {
            if (oldTableElement != null) {
                int newIndex = indexByKey(oldTableElement.key);
                newTable[newIndex] = oldTableElement;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexByKey(key);
        V value = null;
        if (table[index] != null) {
            if (keyCompare(key, table[index].key)) {
                value = table[index].value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexByKey(key);
        boolean rsl = false;

        if (table[index] != null) {
            if (keyCompare(key, table[index].key)) {
                table[index] = null;
                modCount++;
                count--;
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
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
