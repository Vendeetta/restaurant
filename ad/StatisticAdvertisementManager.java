package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private final static StatisticAdvertisementManager statisticAdvertisementManager = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private StatisticAdvertisementManager(){

    }
    public static StatisticAdvertisementManager getInstance(){
        return statisticAdvertisementManager;
    }
    public List<Advertisement> activeVideos(){
        List<Advertisement> allVideo = storage.list();
        List<Advertisement> activeVideos = new ArrayList<>();
        for (Advertisement video : allVideo){
            if(video.getHits()>0){
                activeVideos.add(video);
            }
        }
        return activeVideos;
    }
    public List<Advertisement> unActiveVideos(){
        List<Advertisement> allVideo = storage.list();
        List<Advertisement> activeVideos = new ArrayList<>();
        for (Advertisement video : allVideo){
            if(video.getHits() == 0){
                activeVideos.add(video);
            }
        }
        return activeVideos;
    }
}
