public class BinarySearchTree {
    Node root = null;

    public void insert(String word) {
        Node parent = null;
        Node tmp = root;

        while(tmp != null) {
            if (alphabeticallyBigger(word, tmp.word)) {
                parent = tmp;
                tmp = tmp.rightChild;
            }
            else {
                parent = tmp;
                tmp = tmp.leftChild;
            }
        }

        Node inserted = new Node();
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
        }
    }

    public boolean searchFor(String word) {
        return search(root, word) != null;
    }

    private Node search(Node root, String word) {
        if (root == null || root.word.contentEquals(word))
            return root;

        if (alphabeticallyBigger(word, root.word)) {
            return search(root.rightChild, word);
        }

        return search(root.leftChild, word);
    }

    private boolean alphabeticallyBigger(String newWord, String parentWord) {
        return newWord.compareTo(parentWord) >= 0;
    }
}
