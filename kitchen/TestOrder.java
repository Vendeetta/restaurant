package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order{
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
        initDishes();
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        Dish[] dish = Dish.values();
        int size = (int)(Math.random()*dish.length);
        for (int i = 0; i < size; i++) {
            int rnd = (int)(Math.random()*size);
            dishes.add(dish[rnd]);
        }
    }
}
