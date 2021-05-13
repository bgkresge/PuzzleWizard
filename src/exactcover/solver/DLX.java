package exactcover.solver;

import exactcover.builder.DancingLinksBuilder;

import java.util.StringJoiner;

public class DLX {
    DancingLinks dancingLinks;
    Node[] solution;


    public DLX(DancingLinks dl) {
        this.dancingLinks = dl;
    }

    public void searchForAll(int size) {
        solution = new Node[size];
        searchAll(0);
    }

    private void searchAll(int k) {
       // System.out.println("search depth "+k);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (!(k <= solution.length)) {
            //System.out.println("back: length");
            return;
        }
        if (dancingLinks.constraintsNeeded() == 0) {
            printSolution();
            //System.out.println("back: found");
            return;
        }
        ListHeader chosenConstraint = chooseConstraint();
        if (chosenConstraint.size == 0) {
           // System.out.println("back: constraint " + chosenConstraint.title + " is impossible");
            return;
        }
        //System.out.println("chose constraint " + chosenConstraint.title + " with size " + chosenConstraint.size);
        dancingLinks.coverColumn(chosenConstraint);
        Node selected = chosenConstraint.down;
        while (selected != chosenConstraint) {
          //  System.out.println("selected " + selected.row.title);
            solution[k] = selected;
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
        StringJoiner sj = new StringJoiner(", ","{","}");
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
