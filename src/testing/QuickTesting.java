package testing;

import exactcover.builder.DancingLinksBuilder;
import exactcover.solver.DLX;
import util.PermutationFactory;

import java.util.Arrays;

public class QuickTesting {
    public static long factorial(int n) {
        long out = 1;
        for (int i = n; i > 1; i--) {
            out *= i;
        }
        return out;
    }

    private static Integer[] makeExample(int size) {
        Integer[] out = new Integer[size];
        for (int i = 0; i < size; i++) {
            out[i] = i + 1;
        }
        return out;
    }

    public static void main(String[] args) {

    }
}
