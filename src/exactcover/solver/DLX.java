package exactcover.solver;

import java.util.StringJoiner;

public class DLX {
    DancingLinks dancingLinks;
    Node[] solution;
    DLXWatcher watcher = new DefaultWatcher();


    public DLX(DancingLinks dl) {
        this.dancingLinks = dl;
    }

    public DLX(DancingLinks dl, DLXWatcher watcher) {
        this.watcher = watcher;
        this.dancingLinks = dl;
    }

    public void searchForAll(int size) {
        solution = new Node[size];
        searchAll(0);
    }

    private void searchAll(int k) {
        if (k > solution.length) {
            return;
        }
        if (dancingLinks.constraintsNeeded() == 0) {
            watcher.solutionFound(solution);
            return;
        }
        ListHeader chosenConstraint = chooseConstraint();
        if (chosenConstraint.size == 0) {
            return;
        }
        dancingLinks.coverColumn(chosenConstraint);
        Node selected = chosenConstraint.down;
        while (selected != chosenConstraint) {
            solution[k] = selected;
            watcher.solutionUpdated(solution, k);
            Node rowMate = selected.right;
            while (rowMate != selected) {
                dancingLinks.coverColumn(rowMate.column);
                rowMate = rowMate.right;
            }
            searchAll(k + 1);
            Node toUncover = selected.left;
            while (toUncover != selected) {
                dancingLinks.uncoverColumn(toUncover.column);
                toUncover = toUncover.left;
            }
            selected = selected.down;
        }
        dancingLinks.uncoverColumn(chosenConstraint);
    }

    private void printSolution() {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (Node n : solution) {
            sj.add(n.row.title);
        }
        System.out.println(sj);
    }

    private ListHeader chooseConstraint() {
        ListHeader chosen = (ListHeader) dancingLinks.requiredConstraintsRoot.right;
        ListHeader current = (ListHeader) chosen.right;
        while (current != dancingLinks.requiredConstraintsRoot) {
            if (current.size < chosen.size) {
                chosen = current;
            }
            current = (ListHeader) current.right;
        }
        return chosen;
    }
}
