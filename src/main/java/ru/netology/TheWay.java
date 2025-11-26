package ru.netology;

import java.util.*;

public class TheWay implements Runnable {

    public static final Map<Integer, Integer> sizeToFreq = new HashMap<Integer, Integer>();

    @Override
    public void run() {
        String str = generateRoute("RLRFR", 100);
        Integer rCount = 0;
        for (char ch : str.toCharArray()) {
            if(ch == 'R'){
                rCount++;
            }
        }
    synchronized (sizeToFreq) {
        if (sizeToFreq.containsKey(rCount)) {
            sizeToFreq.put(rCount, sizeToFreq.get(rCount + 1));
        } else {
            sizeToFreq.put(rCount, 1);
        }
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
