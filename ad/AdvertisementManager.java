package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    private final List<Advertisement> optimalVideos = new ArrayList<>();

    public List<Advertisement> getOptimalVideos() {
        return optimalVideos;
    }

    public long getAmount() {
        long amount = 0;
        if (!optimalVideos.isEmpty()) {
            for (Advertisement ad : optimalVideos) {
                amount += ad.getAmountPerOneDisplaying();
            }
        }
        return amount;
    }

    public int getTotalDuration() {
        int duration = 0;
        if (!optimalVideos.isEmpty()) {
            for (Advertisement ad : optimalVideos) {
                duration += ad.getDuration();
            }
        }
        return duration;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> availableVideos = storage.list();
        if (availableVideos.isEmpty()) {
            throw new NoVideoAvailableException();
        }
        availableVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int value = (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                if (value == 0) {
                    value = (int) o2.getDuration() - o1.getDuration();
                }
                return value;
            }
        });

        for (Advertisement video : availableVideos) {

            if (timeSeconds >= video.getDuration() && video.getHits() > 0) {
                optimalVideos.add(video);
                timeSeconds = timeSeconds - video.getDuration();
            }
        }
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(optimalVideos, getAmount(), getTotalDuration()));
        for (Advertisement ad : optimalVideos) {
            System.out.println(ad);
            ad.revalidate();
        }

    }
}
