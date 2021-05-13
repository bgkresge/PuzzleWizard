package exactcover.solver;

public class ListHeader extends Node {
    protected String title;
    protected int size;

    public ListHeader(String title) {
        this.title = title;
        this.size = 0;
    }

    public void incrementSize() {
        size++;
    }
}
