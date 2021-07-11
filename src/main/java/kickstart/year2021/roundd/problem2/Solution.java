package kickstart.year2021.roundd.problem2;

import java.util.Arrays;
import java.util.Scanner;

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
                int c = in.nextInt();
                int[][] intervals = new int[n][2];
                for (int i = 0; i < intervals.length; i++) {
                    for (int j = 0; j < intervals[i].length; j++) {
                        intervals[i][j] = in.nextInt();
                    }
                }

                int answer = getAnswer(n, c, intervals);
                System.out.printf("Case #%d: %d%n", t, answer);
            }
        }
    }

    private static int getAnswer(int n, int c, int[][] intervals) {
        int min = 10_000;
        int max = 1;
        for (int i = 0; i < intervals.length; i++) {
            min = Math.min(min, intervals[i][0]);
            max = Math.max(max, intervals[i][1]);
        }

        int[] intervalCounts = new int[max - min - 1];
        for (int i = min + 1; i < max; i++) {
            int intervalCount = getIntervalCount(intervals, i);
            intervalCounts[i - min - 1] = intervalCount;
        }

        Arrays.sort(intervalCounts);

        int sum = 0;
        int cuts = 0;
        for (int i = intervalCounts.length - 1; i >= 0; i--) {
            sum += intervalCounts[i];
            cuts++;
            if (cuts == c) {
                break;
            }
        }
        return sum + n;
    }

    private static int getIntervalCount(int[][] intervals, int cut) {
        int answer = 0;
        for (int j = 0; j < intervals.length; j++) {
            if (cut > intervals[j][0] && cut < intervals[j][1]) {
                answer++;
            }
        }
        return answer;
    }

}
