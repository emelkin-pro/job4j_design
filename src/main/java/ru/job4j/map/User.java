package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(1991, 1, 1);
        User user1 = new User("Kek", 7, calendar);
        int hashUser1 = user1.hashCode();
        int hash1 = hashUser1 ^ (hashUser1 >>> 16);
        int bucket1 = hash1 & 15;

        User user2 = new User("Kek", 7, calendar);
        int hashUser2 = user2.hashCode();
        int hash2 = hashUser2 ^ (hashUser2 >>> 16);
        int bucket2 = hash2 & 15;

        User user3 = new User("Kek", 7, calendar);
        int hashUser3 = user3.hashCode();
        int hash3 = hashUser3 ^ (hashUser3 >>> 16);
        int bucket3 = hash3 & 15;

        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.put(user3, new Object());
        map.put(user1, new Object());

        System.out.println(map);
        System.out.println(map.get(user1));
        System.out.println(map.get(user2));
        System.out.println(map.get(user3));
    }
}

