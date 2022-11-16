package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> orderedDishes = new ArrayList<>();
        writeMessage("Выберите блюдо:");
        writeMessage(Dish.allDishesToString());
        String orderedDish = null;
        while (true) {
            writeMessage("Введите желаемое блюдо: ");
            orderedDish = readString().toUpperCase();
            if(orderedDish.equals("EXIT")){
                break;
            }
            else if (Dish.isPresent(orderedDish)) {
                orderedDishes.add(Dish.valueOf(orderedDish));
            } else {
                writeMessage("Такого блюда нет в меню, введите, пожалуйста, блюдо еще раз.");
            }

        }
        return orderedDishes;
    }
}
