package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        this.storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (this.storage.containsKey(id)) {
            this.storage.put(id, model);
        }
        return this.storage.containsKey(id);
    }

    @Override
    public void delete(String id) {
        this.storage.remove(id);
    }

    @Override
    public T findById(String id) {
        return this.storage.get(id);
    }
}