package exactcover.solver;

public class Node {
    protected Node left = this, right = this, up = this, down = this;
    protected ListHeader column, row;

    public void remove() {
        this.down.up = this.up;
        this.up.down = this.down;
    }

    public void reinsert() {
        this.down.up = this;
        this.up.down = this;
    }

    public void setColumn(ListHeader column) {
        this.column = column;
    }

    public void setRow(ListHeader row) {
        this.row = row;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public ListHeader getColumn() {
        return column;
    }

    public ListHeader getRow() {
        return row;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getUp() {
        return up;
    }

    public Node getDown() {
        return down;
    }
}
