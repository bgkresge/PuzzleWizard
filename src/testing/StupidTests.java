package testing;

import java.util.Collection;
import java.util.LinkedList;

public class StupidTests {

    public static int factorial(int n) {
        int out = 1;
        for (int i = n; i > 1; i--) {
            out *= i;
        }
        return out;
    }

    public static <T> Collection<T[]> allPermutations(T[] elements) {
        Collection<T[]> out = new LinkedList<T[]>();
        out.add(elements.clone());
        int len = elements.length;
        int[] indices = new int[len];
        int i = 0;
        while (i < len) {
            if (indices[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indices[i], i);
                out.add(elements.clone());
                indices[i]++;
                i = 0;
            } else {
                indices[i] = 0;
                i++;
            }
        }
        return out;
    }

    private static <T> T[] swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
        return elements;
    }

    public static void main(String[] args) {
        String[] example = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        System.out.println(String.join(", ", example));
        System.out.println("Length: " + example.length);
        System.out.println("Should have " + factorial(example.length) + " permutations");
        Collection allPerms = allPermutations(example);
        System.out.println(allPerms.size() + " permutations");
    }
}
