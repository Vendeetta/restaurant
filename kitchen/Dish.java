package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);
    private int duration;

    public int getDuration() {
        return duration;
    }
    Dish(int duration){
        this.duration=duration;
    }


    public static boolean isPresent(String data) {

        try {
            Dish.valueOf(data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String allDishesToString() {
        return Arrays.stream(Dish.values())
                .map(Enum::toString)
                .collect(Collectors.joining(", "));
    }
}
