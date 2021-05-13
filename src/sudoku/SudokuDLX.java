package sudoku;

import exactcover.builder.DancingLinksBuilder;
import exactcover.solver.DLX;
import exactcover.solver.DancingLinks;

import java.util.Collection;
import java.util.LinkedList;

public class SudokuDLX {


    private static void addAllConstraints(DancingLinksBuilder dlb) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                dlb.addRequiredConstraint(String.format("R%dC%d", i, j));//all cells have one value
                dlb.addRequiredConstraint(String.format("R%dV%d", i, j));//rows contain one of each number 1-9
                dlb.addRequiredConstraint(String.format("C%dV%d", i, j));//columns contain one of each number 1-9
                dlb.addRequiredConstraint(String.format("B%dV%d", i, j));//blocks contain one of each number 1-9
            }
        }
    }

    private static void addAllEntries(DancingLinksBuilder dlb) {
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                for (int value = 1; value <= 9; value++) {
                    String entry = String.format("R%dC%dV%d", row, column, value);
                    dlb.addEntry(entry, allConstraintsFilled(row, column, value));
                }
            }
        }
    }

    private static Collection<String> allConstraintsFilled(int row, int column, int value) {
        Collection out = new LinkedList();
        out.add(String.format("R%dC%d", row, column));
        out.add(String.format("R%dV%d", row, value));
        out.add(String.format("C%dV%d", column, value));
        out.add(String.format("B%dV%d", getBlock(row, column), value));
        return out;
    }

    private static int getBlock(int row, int column) {
        return convert(column) + (convert(row) - 1) * 3;
    }

    private static int convert(int in) {
        return (in + 2) / 3;
    }

    public static void main(String[] args) {
        DancingLinksBuilder dlb = new DancingLinksBuilder();
        addAllConstraints(dlb);
        addAllEntries(dlb);
        dlb.lock("R1C1V8");
        dlb.lock("R2C3V3");
        dlb.lock("R3C2V7");
        dlb.lock("R2C4V6");
        dlb.lock("R3C5V9");
        dlb.lock("R3C7V2");
        dlb.lock("R4C2V5");
        dlb.lock("R4C6V7");
        dlb.lock("R5C5V4");
        dlb.lock("R5C6V5");
        dlb.lock("R5C7V7");
        dlb.lock("R6C4V1");
        dlb.lock("R6C8V3");
        dlb.lock("R7C3V1");
        dlb.lock("R7C8V6");
        dlb.lock("R7C9V8");
        dlb.lock("R8C3V8");
        dlb.lock("R8C4V5");
        dlb.lock("R8C8V1");
        dlb.lock("R9C2V9");
        dlb.lock("R9C7V4");
        DLX dlx =new DLX(dlb.getDancingLinks());
        dlx.searchForAll(81-21);
    }
}
