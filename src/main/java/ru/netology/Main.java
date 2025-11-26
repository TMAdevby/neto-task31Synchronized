package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new TheWay());
            list.add(thread);
            thread.start();
        }

        for (int i = 0; i < list.size(); i++) {
            try {
                list.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        if (maxEntry != null) {
            System.out.println("Самое частое количество повторений " + maxEntry.getKey() +
                    " (встретилось " + maxEntry.getValue() + " раз)");
        }

        System.out.println("Другие размеры:");
        for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
            if (!entry.equals(maxEntry)) {
                System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
            }
        }

        Integer threadCount = 0;
        for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
            threadCount += entry.getValue();
        }
        System.out.println("Количество потоков равно " + threadCount );

    }
}