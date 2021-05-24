package puzzles.sudoku;

import exactcover.builder.DancingLinksBuilder;
import exactcover.solver.DLX;
import exactcover.solver.DLXWatcher;

import java.util.Collection;
import java.util.LinkedList;

public class NxNSudokuDLX {
    int dimension;
    int blockDimension;
    int numToFind;
    DancingLinksBuilder dlb;

    public NxNSudokuDLX(int blockDimension) {
        constructBasicDLX(blockDimension);
        System.out.printf("%dx%d Sudoku Dancing Links Constructed%n", dimension, dimension);
    }

    public NxNSudokuDLX(SudokuBoard sb) {
        constructBasicDLX((int) Math.sqrt(sb.getDimension()));
        for (String given : sb.allFilled()) {
            addGiven(given);
        }
    }

    private void constructBasicDLX(int blockDimension) {
        this.dimension = blockDimension * blockDimension;
        this.blockDimension = blockDimension;
        this.numToFind = dimension * dimension;
        this.dlb = new DancingLinksBuilder();
        addAllConstraints();
        addAllEntries();
    }

    private void addAllConstraints() {
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                dlb.addRequiredConstraint(String.format("R%dC%d", i, j));//all cells have one value
                dlb.addRequiredConstraint(String.format("R%dV%d", i, j));//rows contain one of each number 1-dimension
                dlb.addRequiredConstraint(String.format("C%dV%d", i, j));//columns contain one of each number 1-dimension
                dlb.addRequiredConstraint(String.format("B%dV%d", i, j));//blocks contain one of each number 1-dimension
            }
        }
    }


    private Collection<String> allConstraintsFilled(int row, int column, int value) {
        Collection out = new LinkedList<String>();
        out.add(String.format("R%dC%d", row, column));
        out.add(String.format("R%dV%d", row, value));
        out.add(String.format("C%dV%d", column, value));
        out.add(String.format("B%dV%d", getBlock(row, column), value));
        return out;
    }

    private int getBlock(int row, int column) {
        return convert(column) + (convert(row) - 1) * blockDimension;
    }

    private int convert(int in) {
        return (in + blockDimension - 1) / blockDimension;
    }

    private void addAllEntries() {
        for (int row = 1; row <= dimension; row++) {
            for (int column = 1; column <= dimension; column++) {
                for (int value = 1; value <= dimension; value++) {
                    String entry = String.format("R%dC%dV%d", row, column, value);
                    dlb.addEntry(entry, allConstraintsFilled(row, column, value));
                }
            }
        }
    }

    public boolean addGiven(String given) {
        numToFind--;
        return dlb.lock(given);
    }

    public void solve(DLXWatcher watcher) {
        DLX dlx = new DLX(dlb.getDancingLinks(), watcher);
        dlx.searchForAll(numToFind);
    }

    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(
                "800000000" +
                        "003600000" +
                        "070090200" +
                        "050007000" +
                        "000045700" +
                        "000100030" +
                        "001000068" +
                        "008500010" +
                        "090000400");
        NxNSudokuDLX sdlx = new NxNSudokuDLX(board);
        sdlx.solve(new SudokuWatcher(board));
    }
}
