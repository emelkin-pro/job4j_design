package ru.job4j.generics;

import java.util.Objects;

public class Tiger extends Predator {
    private String name;

    @Override
    public String getName() {

        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Tiger tiger = (Tiger) o;
        return Objects.equals(name, tiger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Tiger{"
                + "name='" + name + '\''
                + '}';
    }
}
