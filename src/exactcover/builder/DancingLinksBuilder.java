package exactcover.builder;

import exactcover.solver.DancingLinks;
import exactcover.solver.ListHeader;
import exactcover.solver.Node;

import java.util.Collection;
import java.util.HashMap;


public class DancingLinksBuilder {
    DancingLinks dl;
    HashMap<String, ListHeader> allConstraints;
    HashMap<String, ListHeader> allEntries;

    public DancingLinksBuilder() {
        initializeDL();
    }

    private void initializeDL() {
        dl = new DancingLinks();
        allConstraints = new HashMap<>();
        allEntries=new HashMap<>();
        dl.setRequiredConstraintsRoot(new ListHeader("Required Constraints"));
        dl.setOptionalConstraintsRoot(new ListHeader("Optional Constraints"));
        dl.setEntriesRoot(new ListHeader("Entries"));
    }

    public boolean addRequiredConstraint(String title) {
        return addConstraint(title, true);
    }

    public boolean addOptionalConstraint(String title) {
        return addConstraint(title, false);
    }

    private boolean addConstraint(String title, boolean required) {
        if (allConstraints.containsKey(title)) {
            return false;
        }
        ListHeader columnHeader = new ListHeader(title);
        if (required) {
            columnHeader.setRow(dl.getRequiredConstraintsRoot());
        } else {
            columnHeader.setRow(dl.getOptionalConstraintsRoot());
        }
        addToRow(columnHeader);
        allConstraints.put(title, columnHeader);
        return true;
    }

    public boolean addEntry(String title, Collection<String> constraintsFilled) {
        if (allEntries.containsKey(title)) {
            return false;
        }
        ListHeader rowHeader = new ListHeader(title);
        rowHeader.setColumn(dl.getEntriesRoot());
        addToColumn(rowHeader);
        allEntries.put(title, rowHeader);
        for (String constraint : constraintsFilled) {
            if (!allConstraints.containsKey(constraint)) {
                return false;
            } else {
                Node n = new Node();
                n.setRow(rowHeader);
                addToRow(n);
                n.setColumn(allConstraints.get(constraint));
                addToColumn(n);
            }
        }
        return true;
    }

    private void addToRow(Node n) {
        ListHeader rowHeader = n.getRow();
        Node last = rowHeader.getLeft();
        n.setLeft(last);
        n.setRight(rowHeader);
        last.setRight(n);
        rowHeader.setLeft(n);
        rowHeader.incrementSize();
    }

    private void addToColumn(Node n) {
        ListHeader columnHeader = n.getColumn();
        Node last = columnHeader.getUp();
        n.setUp(last);
        n.setDown(columnHeader);
        last.setDown(n);
        columnHeader.setUp(n);
        columnHeader.incrementSize();
    }

    public DancingLinks getDancingLinks() {
        return dl;
    }

    public boolean lock(String entry) {
        if (!allEntries.containsKey(entry)) {
            return false;
        }
        ListHeader rowHeader = allEntries.get(entry);
        Node n= rowHeader.getRight();
        while (n!=rowHeader){
            dl.coverColumn(n.getColumn());
            n=n.getRight();
        }
        return true;
    }
}
