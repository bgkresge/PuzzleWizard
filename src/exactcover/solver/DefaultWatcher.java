package exactcover.solver;

import java.util.StringJoiner;

public class DefaultWatcher extends DLXWatcher{
    @Override
    protected void solutionUpdated(Node[] soln, int idx) {
        printCurrentSolution(soln,idx);
    }

    @Override
    protected void solutionFound(Node[] soln) {
        printCurrentSolution(soln,soln.length-1);
    }


}
