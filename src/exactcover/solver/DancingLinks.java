package exactcover.solver;

public class DancingLinks {
    protected ListHeader requiredConstraintsRoot, optionalConstraintsRoot, entriesRoot;

    public void coverColumn(ListHeader columnHeader) {
        if (columnHeader == entriesRoot) { //handles edge case that happens every entry pick
            return;
        }
        columnHeader.right.left = columnHeader.left;
        columnHeader.left.right = columnHeader.right;
        columnHeader.row.size--;//this will be the appropriate constraints root
        Node chosen = columnHeader.down;
        while (chosen != columnHeader) {
            coverRow(chosen);
            chosen = chosen.down;
        }
    }

    private void coverRow(Node chosen) {
        Node n = chosen.right;
        while (n != chosen) {
            n.remove();
            n.column.size--;
            n = n.right;
        }
    }

    public void uncoverColumn(ListHeader columnHeader) {
        if (columnHeader == entriesRoot) {
            return;
        }
        Node current = columnHeader.up;
        while (current != columnHeader) {
            uncoverRow(current);
            current = current.up;
        }
        columnHeader.right.left = columnHeader;
        columnHeader.left.right = columnHeader;
        columnHeader.row.size++;
    }

    private void uncoverRow(Node current) {
        Node n = current.left;
        while (n != current) {
            n.reinsert();
            n.column.size++;
            n = n.left;
        }
    }

    public void lock(String entry) {
        boolean found = false;
        ListHeader h = (ListHeader) entriesRoot.down;
        while (h != entriesRoot && !found) {
            if (h.title.equals(entry)) {
                found = true;
                resolveLock(h);
            }
            h = (ListHeader) h.down;
        }
    }

    private void resolveLock(ListHeader rowHeader) {
        Node n = rowHeader.right;
        while (n != rowHeader) {
            coverColumn(n.column);
            n = n.right;
        }
    }

    public int constraintsNeeded() {
        return requiredConstraintsRoot.size;
    }

    public void setRequiredConstraintsRoot(ListHeader requiredConstraintsRoot) {
        this.requiredConstraintsRoot = requiredConstraintsRoot;
    }

    public void setOptionalConstraintsRoot(ListHeader optionalConstraintsRoot) {
        this.optionalConstraintsRoot = optionalConstraintsRoot;
    }

    public void setEntriesRoot(ListHeader entriesRoot) {
        this.entriesRoot = entriesRoot;
    }

    public ListHeader getRequiredConstraintsRoot() {
        return requiredConstraintsRoot;
    }

    public ListHeader getOptionalConstraintsRoot() {
        return optionalConstraintsRoot;
    }

    public ListHeader getEntriesRoot() {
        return entriesRoot;
    }
}
