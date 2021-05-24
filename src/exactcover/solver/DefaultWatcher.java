package exactcover.solver;

public class DefaultWatcher extends DLXWatcher {
    @Override
    protected void solutionUpdated(Node[] soln, int idx) {
        System.out.println(outputCurrentSolution(soln, idx));
    }

    @Override
    protected void solutionFound(Node[] soln) {
        System.out.println(outputCurrentSolution(soln, soln.length - 1));
    }


}
