public class Main {

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert("aaa");
        binarySearchTree.insert("Aaa");
        binarySearchTree.insert("zzz");
        binarySearchTree.insert("ggg");

        System.out.println(binarySearchTree.root.word);
        System.out.println(binarySearchTree.root.leftChild.word);
        System.out.println(binarySearchTree.root.rightChild.word);
        System.out.println(binarySearchTree.searchFor("Aaa"));
    }

}
