package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;


    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
    //    dishes = ConsoleHelper.getAllDishesForOrder();
        initDishes();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();

    }

    public int getTotalCookingTime() {
        int timeToCookAllDishes = 0;
        for (Dish dish : dishes) {
            timeToCookAllDishes += dish.getDuration();
        }
        return timeToCookAllDishes;
    }
    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        String orderToString;
        if (dishes.size() == 0 || dishes == null) {
            orderToString = "";
        } else {
            orderToString = "Your order:" + dishes.toString() + " of " + tablet.toString() + ", cooking time " + getTotalCookingTime() + "min";
        }
        return orderToString;
    }

    public Tablet getTablet() {
        return tablet;
    }
}
