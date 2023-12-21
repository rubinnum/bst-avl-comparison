public class AVLTree {
    AVLNode root = null;

    public void insert (String word) {
        AVLNode parent = null;
        AVLNode grandparent = null;
        AVLNode tmp = root;

        while(tmp != null) {
            grandparent = parent;
            parent = tmp;

            if (alphabeticallyBigger(word, tmp.word)) {
                tmp = tmp.rightChild;
            }
            else {
                tmp = tmp.leftChild;
            }
        }

        AVLNode inserted = new AVLNode();
        inserted.word = word;

        if (parent == null) {
            root = inserted;
        }
        else {
            if (alphabeticallyBigger(word, parent.word)) {
                parent.rightChild = inserted;
            }
            else {
                parent.leftChild = inserted;
            }
            updateNodeHeight(parent);
            if (grandparent != null) {
                updateNodeHeight(grandparent);
                int balance = getBalance(grandparent);
                root = applyRotation(balance, grandparent);
            }


//            updateNodeHeight(parent);
//            int balance = getBalance(parent);
//            applyRotation(balance, parent);
        }
    }

    private AVLNode applyRotation(int balance, AVLNode avlNode) {
        if (balance > 1) {
            if (alphabeticallyBigger(avlNode.word, avlNode.leftChild.word)) {
                return rotateRight(avlNode);
            }
            else {
                rotateLeft(avlNode.leftChild);
                return rotateRight(avlNode);
            }
        }

        if (balance < -1) {
            if (alphabeticallyBigger(avlNode.rightChild.word, avlNode.word)) {
                return rotateLeft(avlNode);
            }
            else {
                rotateRight(avlNode.rightChild);
                return rotateLeft(avlNode);
            }
        }
        return avlNode;
    }

    private AVLNode rotateLeft(AVLNode avlNode) {
        AVLNode rightNode = avlNode.rightChild;
        AVLNode centerNode = rightNode.leftChild;

        rightNode.leftChild = avlNode;
        avlNode.rightChild = centerNode;

        updateNodeHeight(avlNode);
        updateNodeHeight(rightNode);
        return rightNode;
    }

    private AVLNode rotateRight(AVLNode avlNode) {
        AVLNode leftNode = avlNode.leftChild;
        AVLNode centerNode = leftNode.rightChild;

        leftNode.rightChild = avlNode;
        avlNode.leftChild = centerNode;

        updateNodeHeight(avlNode);
        updateNodeHeight(leftNode);
        return leftNode;
    }

    private int getBalance(AVLNode avlNode) {
        return getNodeHeight(avlNode.leftChild) - getNodeHeight(avlNode.rightChild);
    }

    private int getNodeHeight(AVLNode avlNode) {
        return avlNode != null ? avlNode.height : 0;
    }

    private void updateNodeHeight(AVLNode avlNode) {
        avlNode.height = 1 + max(getNodeHeight(avlNode.leftChild), getNodeHeight(avlNode.rightChild));
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private boolean alphabeticallyBigger(String newWord, String parentWord) {
        return newWord.compareTo(parentWord) >= 0;
    }
}
