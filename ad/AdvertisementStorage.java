package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private final static AdvertisementStorage single = new AdvertisementStorage();
    private AdvertisementStorage(){
    Object someContent = new Object();
    add(new Advertisement(someContent, "First video", 5000, 100, 3*60));
    add(new Advertisement(someContent, "Second video", 100, 10, 15*60));
    add(new Advertisement(someContent, "Third video", 400, 10, 10*60));
    }
    public static AdvertisementStorage getInstance(){
        return single;
    }

    private final List<Advertisement> videos = new ArrayList<>();

    public List<Advertisement> list(){
        return videos;
    }
    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}

