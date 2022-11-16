package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;

    }

    public Order createOrder() {
        Order newOrder = null;
        try {
            newOrder = new Order(this);
            ConsoleHelper.writeMessage(newOrder.toString());
            refactor(newOrder);

        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + newOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return newOrder;
    }

    private void refactor(Order newOrder) throws InterruptedException {
        if (!newOrder.isEmpty()) {
            queue.put(newOrder);
            AdvertisementManager manager = new AdvertisementManager(newOrder.getTotalCookingTime() * 60);
            manager.processVideos();
        }
    }

    public void createTestOrder() {
        TestOrder newOrder = null;
        try {
            newOrder = new TestOrder(this);
            refactor(newOrder);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }
}
