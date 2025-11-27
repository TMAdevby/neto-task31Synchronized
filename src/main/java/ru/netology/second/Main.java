package ru.netology.second;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();

        Thread loggerThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                synchronized (TheWay.loggerLock) {
                    try {
                        TheWay.loggerLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }

                Map.Entry<Integer, Integer> maxEntry = null;
                synchronized (TheWay.sizeToFreq) {
                    for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
                        if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                            maxEntry = entry;
                        }
                    }
                }
                if (maxEntry != null) {
                    System.out.println("Лидер среди частот число " + maxEntry.getKey() +
                            " встречается " + maxEntry.getValue() + " раз.");
                }
            }
        });

        loggerThread.start();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new TheWay());
            list.add(thread);
            thread.start();
        }

        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        loggerThread.interrupt();

        Map.Entry<Integer, Integer> maxEntry = null;
        synchronized (TheWay.sizeToFreq) {
            for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
                if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
                }
            }
        }

        if (maxEntry != null) {
            System.out.println("Самое частое количество повторений " + maxEntry.getKey() +
                    " (встретилось " + maxEntry.getValue() + " раз)");
        }

        System.out.println("Другие размеры:");
        synchronized (TheWay.sizeToFreq) {
            for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
                if (!entry.equals(maxEntry)) {
                    System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
                }
            }
        }

        int threadCount = 0;
        synchronized (TheWay.sizeToFreq) {
            for (Map.Entry<Integer, Integer> entry : TheWay.sizeToFreq.entrySet()) {
                threadCount += entry.getValue();
            }
        }
        System.out.println("Количество потоков равно " + threadCount);
    }
}