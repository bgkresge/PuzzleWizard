package exactcover.solver;

public class Node {
    Node left, right, up, down;
    ListHeader column, row;

    public void remove() {
        this.down.up = this.up;
        this.up.down = this.down;
    }

    public void reinsert() {
        this.down.up = this;
        this.up.down = this;
    }
}
