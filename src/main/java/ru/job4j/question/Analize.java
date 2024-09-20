package ru.job4j.question;


import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        HashMap<Integer, String> map = new HashMap<>();
        int counterAdd = 0;
        int counterChange = 0;
        int counterRemove = 0;

        for (User user : previous) {
            map.put(user.getId(), user.getName());
        }

        for (User user : current) {
            String userName = map.remove(user.getId());
            if (userName == null) {
                counterAdd++;
            } else if (!userName.equals(user.getName())) {
                counterChange++;
            }
            counterRemove = map.size();
        }
        return new Info(counterAdd, counterChange, counterRemove);
    }

}
