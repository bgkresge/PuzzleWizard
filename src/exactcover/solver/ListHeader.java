package exactcover.solver;

public class ListHeader extends Node {
    String title;
    int size;
    ListHeader root;

    public ListHeader(String title) {
        this.title = title;
        size = 0;
    }
}
