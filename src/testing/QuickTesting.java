package testing;

import util.PermutationFactory;

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
        for(int i=0;i<size;i++){
            out[i]=i+1;
        }
        return out;
    }

    public static void main(String[] args) {
//        String[] example = new String[]{"A","B","C","D","E","F","G","H","I","J","K"};
//        PermutationFactory<String> pf = new PermutationFactory<>(example);
//        while(pf.hasNext()){
//            System.out.println(String.join(", ",pf.next()));
//        }
        Integer[] example = makeExample(5);
        PermutationFactory<Integer> pf = new PermutationFactory<>(example);
        while (pf.hasNext()) {
            pf.next();
        }
        System.out.println(pf.getPermutationCount() + " permutations");
        System.out.println("Length: " + example.length);
        System.out.println("Should have " + factorial(example.length) + " permutations");
    }
}
