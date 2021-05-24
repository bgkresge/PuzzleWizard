package exactcover.solver;

import puzzles.Board;
import puzzles.Visualizer;

import java.util.StringJoiner;

public abstract class DLXWatcher {


    protected abstract void solutionUpdated(Node[] soln, int idx);

    protected abstract void solutionFound(Node[] soln);

    protected String outputCurrentSolution(Node[] soln, int end) {
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i <= end; i++) {
            sj.add(soln[i].row.title);
        }
        return sj.toString();
    }

}
