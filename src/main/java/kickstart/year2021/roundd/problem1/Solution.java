package kickstart.year2021.roundd.problem1;

import java.util.Scanner;

/**
 * Solution for Arithmetic Square - Round D 2021 - Kick Start 2021.
 *
 * @author Johnny Lim
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int cases = in.nextInt();
            for (int t = 1; t <= cases; t++) {
                int[][] grid = new int[3][3];
                for (int row = 0; row < grid.length; row++) {
                    for (int column = 0; column < grid[row].length; column++) {
                        if (row == 1 && column == 1) {
                            continue;
                        }

                        grid[row][column] = in.nextInt();
                    }
                }

                int max = 0;

                grid[1][1] = (grid[0][0] + grid[2][2]) / 2;
                int answer = getAnswer(grid);
                max = Math.max(max, answer);

                grid[1][1] = (grid[2][0] + grid[0][2]) / 2;
                answer = getAnswer(grid);
                max = Math.max(max, answer);

                grid[1][1] = (grid[1][0] + grid[1][2]) / 2;
                answer = getAnswer(grid);
                max = Math.max(max, answer);

                grid[1][1] = (grid[0][1] + grid[2][1]) / 2;
                answer = getAnswer(grid);
                max = Math.max(max, answer);

                System.out.printf("Case #%d: %d%n", t, max);
            }
        }
    }

    private static int getAnswer(int[][] grid) {
        int answer = 0;
        if (grid[0][0] - grid[0][1] == grid[0][1] - grid[0][2]) {
            answer++;
        }
        if (grid[1][0] - grid[1][1] == grid[1][1] - grid[1][2]) {
            answer++;
        }
        if (grid[2][0] - grid[2][1] == grid[2][1] - grid[2][2]) {
            answer++;
        }
        if (grid[0][0] - grid[1][0] == grid[1][0] - grid[2][0]) {
            answer++;
        }
        if (grid[0][1] - grid[1][1] == grid[1][1] - grid[2][1]) {
            answer++;
        }
        if (grid[0][2] - grid[1][2] == grid[1][2] - grid[2][2]) {
            answer++;
        }
        if (grid[0][0] - grid[1][1] == grid[1][1] - grid[2][2]) {
            answer++;
        }
        if (grid[2][0] - grid[1][1] == grid[1][1] - grid[0][2]) {
            answer++;
        }
        return answer;
    }

}
