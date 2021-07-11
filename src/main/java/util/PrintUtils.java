package util;

/**
 * Utilities for printing.
 *
 * @author Johnny Lim
 */
public final class PrintUtils {

    public static void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void print(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            print(data[i]);
        }
    }

    private PrintUtils() {
    }

}
