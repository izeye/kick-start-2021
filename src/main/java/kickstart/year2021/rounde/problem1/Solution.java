package kickstart.year2021.rounde.problem1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for Shuffled Anagrams - Round E 2021 - Kick Start 2021.
 *
 * This is based on its "Analysis" section.
 *
 * @author Johnny Lim
 */
public class Solution {

    private static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int cases = in.nextInt();
            for (int t = 1; t <= cases; t++) {
                String s = in.next();

                String shuffledAnagram = getShuffledAnagram(s);
                System.out.printf("Case #%d: %s%n", t, shuffledAnagram);
            }
        }
    }

    static String getShuffledAnagram(String s) {
        if (s.length() == 1) {
            return IMPOSSIBLE;
        }

        CharacterTracker[] trackers = new CharacterTracker[s.length()];
        for (int i = 0; i < s.length(); i++) {
            trackers[i] = new CharacterTracker(s.charAt(i), i);
        }
        Arrays.sort(trackers, (o1, o2) -> Character.compare(o1.c, o2.c));

        int half = s.length() / 2;

        int count = 1;
        char previous = trackers[0].c;
        for (int i = 1; i < trackers.length; i++) {
            char c = trackers[i].c;
            if (c == previous) {
                count++;
                if (count > half) {
                    return IMPOSSIBLE;
                }
            }
            else {
                count = 1;
            }
            previous = c;
        }

        CharacterTracker[] swapped = new CharacterTracker[trackers.length];
        int secondHalfLength = trackers.length - half;
        System.arraycopy(trackers, half, swapped, 0, secondHalfLength);
        System.arraycopy(trackers, 0, swapped, secondHalfLength, half);

        char[] chars = new char[s.length()];
        for (int i = 0; i < trackers.length; i++) {
            CharacterTracker tracker = trackers[i];
            chars[tracker.index] = swapped[i].c;
        }
        return new String(chars);
    }

    static class CharacterTracker {

        final char c;
        final int index;

        CharacterTracker(char c, int index) {
            this.c = c;
            this.index = index;
        }

    }

}
