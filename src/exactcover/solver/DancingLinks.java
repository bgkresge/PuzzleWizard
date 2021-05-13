package exactcover.solver;

public class DancingLinks {
    ListHeader requiredConstraintsRoot, optionalConstraintsRoot, entriesRoot;

    public void coverColumn(ListHeader columnHeader) {
        columnHeader.right.left = columnHeader.left;
        columnHeader.left.right = columnHeader.right;
        columnHeader.root.size--;
        Node n = columnHeader.down;
        while (n != columnHeader) {
            coverRow(n);
            n = n.down;
        }
    }

    private void coverRow(Node n) {

    }

}
