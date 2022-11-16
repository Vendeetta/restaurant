package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();
    public static void main(String[] args) throws IOException, InterruptedException {
        Cook cook1 = new Cook("Petya");
        Cook cook = new Cook("Vasya");
        cook1.setQueue(ORDER_QUEUE);
        cook.setQueue(ORDER_QUEUE);
        StatisticManager manager = StatisticManager.getInstance();
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook1.addObserver(waiter);
        Tablet tablet1 = new Tablet(1);
        Tablet tablet2 = new Tablet(2);
        Tablet tablet3 = new Tablet(3);
        Tablet tablet4 = new Tablet(4);
        Tablet tablet5 = new Tablet(5);
        tablet1.setQueue(ORDER_QUEUE);
        tablet2.setQueue(ORDER_QUEUE);
        tablet3.setQueue(ORDER_QUEUE);
        tablet4.setQueue(ORDER_QUEUE);
        tablet5.setQueue(ORDER_QUEUE);
        List<Tablet> tablets = new ArrayList<>();
        tablets.add(tablet1);
        tablets.add(tablet2);
        tablets.add(tablet3);
        tablets.add(tablet4);
        tablets.add(tablet5);
        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(task);
        new Thread(cook).start();
        new Thread(cook1).start();
        thread.start();
        Thread.sleep(1000);
        task.Disable();
        thread.join();



        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();



    }
}
