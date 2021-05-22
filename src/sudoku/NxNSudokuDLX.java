package sudoku;

import exactcover.builder.DancingLinksBuilder;
import exactcover.solver.DLX;

import java.util.Collection;
import java.util.LinkedList;

public class NxNSudokuDLX {
    int N;
    int numToFind;
    DancingLinksBuilder dlb;

    public NxNSudokuDLX(int N) {
        this.N = N;
        this.numToFind = N * N * N * N;
        this.dlb = new DancingLinksBuilder();
        addAllConstraints();
        addAllEntries();
        System.out.printf("%dx%d Sudoku Dancing Links Constructed%n",N*N,N*N);
    }

    private void addAllConstraints() {
        for (int i = 1; i <= N * N; i++) {
            for (int j = 1; j <= N * N; j++) {
                dlb.addRequiredConstraint(String.format("R%dC%d", i, j));//all cells have one value
                dlb.addRequiredConstraint(String.format("R%dV%d", i, j));//rows contain one of each number 1-N
                dlb.addRequiredConstraint(String.format("C%dV%d", i, j));//columns contain one of each number 1-N
                dlb.addRequiredConstraint(String.format("B%dV%d", i, j));//blocks contain one of each number 1-N
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
        return convert(column) + (convert(row) - 1) * N;
    }

    private int convert(int in) {
        return (in + N - 1) / N;
    }

    private void addAllEntries() {
        for (int row = 1; row <= N * N; row++) {
            for (int column = 1; column <= N * N; column++) {
                for (int value = 1; value <= N * N; value++) {
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

    public void solve() {
        DLX dlx = new DLX(dlb.getDancingLinks(),new SudokuWatcher());
        dlx.searchForAll(numToFind);
    }

    public static void main(String[] args) {
        System.out.println("Solving 'Hard' 9x9");
        NxNSudokuDLX sdlx = new NxNSudokuDLX(3);
        sdlx.addGiven("R1C1V8");
        sdlx.addGiven("R2C3V3");
        sdlx.addGiven("R3C2V7");
        sdlx.addGiven("R2C4V6");
        sdlx.addGiven("R3C5V9");
        sdlx.addGiven("R3C7V2");
        sdlx.addGiven("R4C2V5");
        sdlx.addGiven("R4C6V7");
        sdlx.addGiven("R5C5V4");
        sdlx.addGiven("R5C6V5");
        sdlx.addGiven("R5C7V7");
        sdlx.addGiven("R6C4V1");
        sdlx.addGiven("R6C8V3");
        sdlx.addGiven("R7C3V1");
        sdlx.addGiven("R7C8V6");
        sdlx.addGiven("R7C9V8");
        sdlx.addGiven("R8C3V8");
        sdlx.addGiven("R8C4V5");
        sdlx.addGiven("R8C8V1");
        sdlx.addGiven("R9C2V9");
        sdlx.addGiven("R9C7V4");
        sdlx.solve();
    }
}
