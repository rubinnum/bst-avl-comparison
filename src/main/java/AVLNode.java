public class AVLNode {
    String word;
    int height;
    AVLNode leftChild;
    AVLNode rightChild;

    AVLNode() {
        this.height = 1;
        this.leftChild = this.rightChild = null;
    }
}
