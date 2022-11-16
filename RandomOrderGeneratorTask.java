package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private boolean isActive = true;
    public void Disable(){
        isActive = false;

    }
    private List<Tablet> tablets = new ArrayList<>();
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.interval = interval;
        this.tablets = tablets;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                int randomTablet = (int) (Math.random() * tablets.size());
                tablets.get(randomTablet).createTestOrder();
                Thread.sleep(interval);
            } catch (InterruptedException e) {

            }
        }
    }
}
