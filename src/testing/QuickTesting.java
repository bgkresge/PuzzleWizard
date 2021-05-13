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
//        String[] example = new String[]{"A","B","C","D","E","F","G","H","I","J","K"};
//        PermutationFactory<String> pf = new PermutationFactory<>(example);
//        while(pf.hasNext()){
//            System.out.println(String.join(", ",pf.next()));
//        }
//        Integer[] example = makeExample(5);
//        PermutationFactory<Integer> pf = new PermutationFactory<>(example);
//        while (pf.hasNext()) {
//            pf.next();
//        }
//        System.out.println(pf.getPermutationCount() + " permutations");
//        System.out.println("Length: " + example.length);
//        System.out.println("Should have " + factorial(example.length) + " permutations");
        DancingLinksBuilder dlb=new DancingLinksBuilder();
        for(int i=1;i<=7;i++){
            dlb.addRequiredConstraint(String.valueOf(i));
        }
        dlb.addEntry("A", Arrays.asList("1","4","7"));
        dlb.addEntry("B", Arrays.asList("1","4"));
        dlb.addEntry("C", Arrays.asList("4","5","7"));
        dlb.addEntry("D", Arrays.asList("3","5","6"));
        dlb.addEntry("E", Arrays.asList("2","3","6","7"));
        dlb.addEntry("F", Arrays.asList("2","7"));
        DLX dlx=new DLX(dlb.getDancingLinks());
        dlx.searchForAll(3);
//        DancingLinksBuilder dlb = new DancingLinksBuilder();
//        dlb.addRequiredConstraint("pants pocket filled");
//        dlb.addRequiredConstraint("coat pocket filled");
//        dlb.addRequiredConstraint("backpack filled");
//        dlb.addRequiredConstraint("keys placed");
//        dlb.addRequiredConstraint("wallet placed");
//        dlb.addRequiredConstraint("phone placed");
//        dlb.addEntry("keys->pants", Arrays.asList("pants pocket filled", "keys placed"));
//        dlb.addEntry("keys->coat", Arrays.asList("coat pocket filled", "keys placed"));
//        dlb.addEntry("keys->backpack", Arrays.asList("backpack filled", "keys placed"));
//        dlb.addEntry("wallet->pants", Arrays.asList("pants pocket filled", "wallet placed"));
//        dlb.addEntry("wallet->coat", Arrays.asList("coat pocket filled", "wallet placed"));
//        dlb.addEntry("wallet->backpack", Arrays.asList("backpack filled", "wallet placed"));
//        dlb.addEntry("phone->pants", Arrays.asList("pants pocket filled", "phone placed"));
//        dlb.addEntry("phone->coat", Arrays.asList("coat pocket filled", "phone placed"));
//        dlb.addEntry("phone->backpack", Arrays.asList("backpack filled", "phone placed"));
//        DLX dlx = new DLX(dlb.getDancingLinks());
//        dlx.searchForAll(3);
    }
}
