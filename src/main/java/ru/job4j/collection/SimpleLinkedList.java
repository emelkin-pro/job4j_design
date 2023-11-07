package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    private Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> lst = last;
        final Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (head == null) {
            head = newNode;
        } else {
            lst.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Iterator<E> iterator = this.iterator();
        for (int i = 0; i < Objects.checkIndex(index, size); i++) {
            iterator.next();
        }
        return iterator.next();
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {

            private Node<E> pointer = null;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer == null ? head != null : pointer.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                pointer = pointer == null ? head : pointer.next;
                return pointer.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}