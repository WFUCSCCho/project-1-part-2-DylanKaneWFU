public interface Iterator<T extends Comparable<T>> {
    void next();
    void goLeftFrom(Node<T> currNode);
}

