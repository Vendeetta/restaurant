package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private final static StatisticManager statisticManager = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();


    private StatisticManager() {

    }

    public static StatisticManager getInstance() {
        return statisticManager;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public TreeMap<String, Long> getProfitMap() {
        TreeMap<String, Long> res = new TreeMap<>();
        List<EventDataRow> rows = statisticStorage.get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (EventDataRow row : rows) {
            VideoSelectedEventDataRow temp = (VideoSelectedEventDataRow) row;
            String date = format.format(temp.getDate());
            if (!res.containsKey(date)) {
                res.put(date, temp.getAmount());
            }else res.put(date, res.get(date) + temp.getAmount());


        }
        return res;
    }

    public Map<String, Map<String, Integer>> getCookWorkloadingMap() {
        Map<String, Map<String, Integer>> res = new HashMap(); //name, time
        List<EventDataRow> rows = statisticStorage.get(EventType.COOKED_ORDER);
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow row : rows) {
            CookedOrderEventDataRow dataRow = (CookedOrderEventDataRow) row;
            String date = format.format(dataRow.getDate());
            if (!res.containsKey(date)) {
                res.put(date, new HashMap<String, Integer>());
            }
            Map<String, Integer> cookMap = res.get(date);
            String cookName = dataRow.getCookName();
            if (!cookMap.containsKey(cookName)) {
                cookMap.put(cookName, 0);
            }

            Integer totalTime = cookMap.get(cookName);
            cookMap.put(cookName, totalTime + dataRow.getTime());
        }

        return res;
    }



    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            storage = new HashMap<>();
            for (EventType event : EventType.values()) {
                storage.put(event, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType type) {
            if (!this.storage.containsKey(type))
                throw new UnsupportedOperationException();

            return this.storage.get(type);
        }
    }
}
