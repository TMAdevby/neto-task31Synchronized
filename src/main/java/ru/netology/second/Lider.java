package ru.netology.second;

import java.util.Map;

public class Lider implements Runnable{
    @Override
    public void run() {


        Map.Entry<Integer, Integer> maxValue = null;

        for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
            if (maxValue == null || entry.getValue() > maxValue.getValue()) {
                maxValue = entry;
            }
        }
        System.out.println("Лидер среди частот число " + maxValue.getKey() + " встречается "
                    + maxValue.getValue() + " раз.");


    }
}
