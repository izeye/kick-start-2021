package kickstart.year2021.roundd.problem2;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Solution for Cutting Intervals - Round D 2021 - Kick Start 2021.
 *
 * RE with Test Set 2.
 *
 * @author Johnny Lim
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int cases = in.nextInt();
            for (int t = 1; t <= cases; t++) {
                int n = in.nextInt();
                long c = in.nextLong();
                long[][] intervals = new long[n][2];
                for (int i = 0; i < intervals.length; i++) {
                    for (int j = 0; j < intervals[i].length; j++) {
                        intervals[i][j] = in.nextLong();
                    }
                }

                Map<Long, MutableInteger> map = new TreeMap<>();
                for (long[] interval : intervals) {
                    MutableInteger valueToIncrease = map.computeIfAbsent(interval[0] + 1, (k) -> new MutableInteger());
                    valueToIncrease.value++;
                    MutableInteger valueToDecrease = map.computeIfAbsent(interval[1], (k) -> new MutableInteger());
                    valueToDecrease.value--;
                }

                long[] array = new long[n + 1];
                Long previousKey = null;
                int current = 0;
                for (var entry : map.entrySet()) {
                    Long key = entry.getKey();
                    if (previousKey != null) {
                        array[current] += key - previousKey;
                    }
                    current += entry.getValue().value;
                    previousKey = key;
                }

                long answer = n;
                long cut = 0;
                for (int i = array.length - 1; i >= 0; i--) {
                    long thisCut = Math.min(array[i], c - cut);
                    answer += i * thisCut;
                    cut += thisCut;
                    if (cut > c) {
                        break;
                    }
                }
                System.out.printf("Case #%d: %d%n", t, answer);
            }
        }
    }

    static class MutableInteger {
        int value;
    }

}
