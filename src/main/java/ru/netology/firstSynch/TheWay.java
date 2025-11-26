package ru.netology.firstSynch;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TheWay implements Runnable {

    public static final Map<Integer, Integer> sizeToFreq = new ConcurrentHashMap<>();

    @Override
    public void run() {
        String str = generateRoute("RLRFR", 100);
        Integer rCount = 0;
        for (char ch : str.toCharArray()) {
            if (ch == 'R') {
                rCount++;
            }
        }
//    synchronized (sizeToFreq) {
        sizeToFreq.merge(rCount, 1, Integer::sum);
//    }

    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
