package kickstart.year2020.rounda.problem1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for Allocation - Round A 2020 - Kick Start 2020.
 *
 * @author Johnny Lim
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int cases = in.nextInt();
            for (int t = 1; t <= cases; t++) {
                int n = in.nextInt();
                int b = in.nextInt();
                int[] ais = new int[n];
                for (int i = 0; i < n; i++) {
                    ais[i] = in.nextInt();
                }
                Arrays.sort(ais);
                int sum = 0;
                int answer = 0;
                for (int i = 0; i < n; i++) {
                    sum += ais[i];
                    if (sum > b) {
                        break;
                    }
                    answer++;
                }
                System.out.printf("Case #%d: %d%n", t, answer);
            }
        }
    }

}
