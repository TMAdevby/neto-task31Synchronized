package ru.netology.second;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TheWay implements Runnable {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static final Object loggerLock = new Object();

    @Override
    public void run() {
        String str = generateRoute("RLRFR", 100);
        int rCount = 0;
        for (char ch : str.toCharArray()) {
            if (ch == 'R') {
                rCount++;
            }
        }
        synchronized (sizeToFreq) {
            sizeToFreq.merge(rCount, 1, Integer::sum); // короче и безопаснее
        }


        synchronized (loggerLock) {
            loggerLock.notify(); // будим логгер
        }
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