import java.util.ArrayList;
import java.util.Stack;

public class BST<E extends Comparable<E>> implements Iterator<E> {
    Node<E> root;
    int sumNodes;
    Stack<Node<E>> nodeStack;
    String printString;

    public BST() {
        //initializes BST
        root = null;
        sumNodes = 0;
        printString = "";
    }

    public Node<E> getRoot() {
        //returns root of BST
        return this.root;
    }

    public String inOrderIterator() {
        //initializes iterator for Inorder Traversal
        printString = ""; //resets print string from previous print
        if (this.root != null) {
            nodeStack = new Stack<>();
            goLeftFrom(this.root);
        }
        return printString;
    }

    @Override
    public void next() {
        //obtains next node to traverse left from and adds to the printString
        while (!nodeStack.isEmpty()) {
            Node<E> currNode = nodeStack.peek();
            printString = printString.concat(currNode.value() + " ");
            nodeStack.pop();

            if (currNode.right() != null) goLeftFrom(currNode.right());

        }
    }

    @Override
    public void goLeftFrom(Node<E> currNode) {
        //traverses left until a leaf node is reached
        while (currNode != null) {
            nodeStack.push(currNode);
            currNode = currNode.left();
        }
        next();
    }

    public void insert(E val) {
        //inserts a new node in the BST
        Node<E> newNode = new Node<>(val);
        if (sumNodes == 0) { //case 1: no existing nodes, new node is assigned as the root
            this.root = newNode;
        } else { //case 2: if nodes exist find where to place the new node
            Node<E> currNode = this.root;
            while (currNode != null) {
                if (currNode.value().compareTo(val) == 0) break;
                else if (currNode.value().compareTo(val) < 0) {
                    if (currNode.right() != null) currNode = currNode.right();
                    else {
                        currNode.setRight(newNode);
                        break;
                    }
                } else {
                    if (currNode.left() != null) currNode = currNode.left();
                    else {
                        currNode.setLeft(newNode);
                        break;
                    }
                }
            }

        }
        sumNodes++;
    }

    public boolean remove(E val) {
        //removes a node from the BST with a given value
        ArrayList<Node<E>> removeHelp = searchToRemove(val, this.root, null);
        if (removeHelp == null) return false;

        sumNodes--;
        Node<E> nodeToRemove = removeHelp.get(0);
        Node<E> nodeParent = removeHelp.get(1);

        boolean isRight = nodeToRemove.compareTo(nodeParent) > 0;

        //case 1: node is a leaf
        if (nodeToRemove.left() == null && nodeToRemove.right() == null) {
            if (isRight) nodeParent.setRight(null);
            else nodeParent.setLeft(null);
            return true;
        }

        //case 2: node has two children
        if (nodeToRemove.left() != null && nodeToRemove.right() != null) {
            Node<E> successor = getSuccessor(nodeToRemove);
            remove(successor.value());
            nodeToRemove.setValue(successor.value());
            return true;
        }

        //case 3: node has one child
        if (isRight) {
            if (nodeToRemove.left() != null) nodeParent.setRight(nodeToRemove.left());
            else nodeParent.setRight(nodeToRemove.right());
        } else {
            if (nodeToRemove.left() != null) nodeParent.setLeft(nodeToRemove.left());
            else nodeParent.setLeft(nodeToRemove.right());
        }
        return true;
    }

    public boolean isEmpty() {
        //returns whether or not tree is empty
        return sumNodes == 0;
    }

    public ArrayList<Node<E>> searchToRemove(E key, Node<E> rt, Node<E> parent) {
        //helper function that returns the desired node to remove with its parent
        if (rt == null) return null;
        if (rt.val.compareTo(key) > 0) return searchToRemove(key, rt.left(), rt);
        if (rt.val.compareTo(key) == 0) {
            ArrayList<Node<E>> list = new ArrayList<>();
            list.add(rt);
            list.add(parent);
            return list;
        } else {
            return searchToRemove(key, rt.right(), rt);
        }
    }

    public Node<E> getSuccessor(Node<E> rt) {
        //obtains successor, used when removing a node with two children
        while (rt.left() != null) rt = rt.left();
        return rt;
    }

    public Node<E> search(E key, Node<E> rt) {
        //searches tree for a given key
        if (rt == null) return null;
        if (rt.val.compareTo(key) > 0) {
            return search(key, rt.left());
        } if (rt.val.compareTo(key) == 0) {
            return rt;
        } else {
            return search(key, rt.right());
        }
    }

    public void clear() {
        this.root = null;
        this.sumNodes = 0;
    }
}
