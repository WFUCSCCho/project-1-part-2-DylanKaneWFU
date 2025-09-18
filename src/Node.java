public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    T val;
    Node<T> left;
    Node<T> right;

    public Node() {
        this.val = null;
        this.left = null;
        this.right = null;
    }

    public Node(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public Node(T val, Node<T> left, Node<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setValue(T val) {
        this.val = val;
    }

    public T value() {
        return this.val;
    }

    public Node<T> left() {
        return this.left;
    }

    public Node<T> right() {
        return this.right;
    }

    @Override
    public int compareTo(Node<T> n) {
        return this.val.compareTo(n.val);
    }

    public boolean isLeaf() { return this.left == null && this.right == null; };

}
