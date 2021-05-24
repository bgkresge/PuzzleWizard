package puzzles.sudoku;

import exactcover.solver.DLXWatcher;
import exactcover.solver.Node;

public class SudokuWatcher extends DLXWatcher {

    private SudokuBoard startingBoard;
    private SudokuBoard currentBoard;

    public SudokuWatcher(SudokuBoard sb) {
        this.startingBoard = sb;
    }

    @Override
    protected void solutionUpdated(Node[] soln, int idx) {
        currentBoard=new SudokuBoard(startingBoard);
        for(int i=0;i<=idx;i++){
            String[] data=extractData(soln[i].getRow().getTitle());
            currentBoard.updateLocation(data[0],data[1]);
        }
       // System.out.println(currentBoard.toCondensed());
    }

    private String[] extractData(String entry) {
        return entry.split("V");
    }

    @Override
    protected void solutionFound(Node[] soln) {
        System.out.println(currentBoard.toCondensed());
    }
}
