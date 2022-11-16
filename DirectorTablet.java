package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        TreeMap<String, Long> profitMap = statisticManager.getProfitMap();

        double total = 0.0;
        for (Map.Entry<String, Long> entry : profitMap.entrySet()) {

            double amount = 1.0 * entry.getValue() / 100;
            System.out.println(entry.getKey() + " - " + String.format(Locale.ENGLISH, "%.2f", amount));
            total += amount;
        }
        System.out.println("Total - " + String.format(Locale.ENGLISH, "%.2f", total));
    }
//написал коммент
    public void printCookWorkloading() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Map<String, Integer>> cookWorkloadingMap = statisticManager.getCookWorkloadingMap();
        ArrayList<String> list = new ArrayList(cookWorkloadingMap.keySet());
        Collections.sort(list);

        for (String key : list) {
            Map<String, Integer> cookMap = cookWorkloadingMap.get(key);
            System.out.println(key);

            ArrayList<String> cookNames = new ArrayList(cookMap.keySet());
            Collections.sort(cookNames);
            for (String cookName : cookNames) {
                System.out.println(cookName + " - " + ((cookMap.get(cookName) + 59) / 60) + " min");
            }

            System.out.println();
        }
    }
    public void printActiveVideoSet() {
        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> activeVideos = manager.activeVideos();
        activeVideos.stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).forEach(x -> System.out.println(x.getName() + " - " + x.getHits()));

    }

    public void printArchivedVideoSet() {
        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> unActiveVideos = manager.unActiveVideos();
        unActiveVideos.stream().sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())).forEach(x -> System.out.println(x.getName()));
    }
}
