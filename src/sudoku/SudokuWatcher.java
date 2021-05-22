package sudoku;

import exactcover.solver.DLXWatcher;
import exactcover.solver.Node;

public class SudokuWatcher extends DLXWatcher {
    @Override
    protected void solutionUpdated(Node[] soln, int idx) {
       // printCurrentSolution(soln,idx);
    }

    @Override
    protected void solutionFound(Node[] soln) {
        printCurrentSolution(soln,soln.length-1);
    }
}
