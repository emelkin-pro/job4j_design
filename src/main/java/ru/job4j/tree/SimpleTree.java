package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentNodeWrapped = findBy(parent);
        if (parentNodeWrapped.isPresent() && findBy(child).isEmpty()) {
            Node<E> parentNode = parentNodeWrapped.get();
            parentNode.getChildren().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.getChildren());
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> byValue = x -> x.getValue().equals(value);
        return findByPredicate(byValue);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> doesNotBi = x -> x.getChildren().size() >= 3;
        return findByPredicate(doesNotBi).isEmpty();
    }
}