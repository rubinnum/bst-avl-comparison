import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.text.RandomStringGenerator;

public class Main {

    public static final BinarySearchTree randomBinarySearchTree = new BinarySearchTree();
    public static final AVLTree randomAVLTree = new AVLTree();
    public static final BinarySearchTree orderedBinarySearchTree = new BinarySearchTree();
    public static final AVLTree orderedAVLTree = new AVLTree();

    private static final int ORDERED_WORD_LENGTH = 10;
    private static char currentChar = 'a';

    public static void main(String[] args) throws IOException {
        File csvFile = new File("utilities/data.csv");
        PrintWriter printWriter = new PrintWriter(csvFile);
        printWriter.println("InputSize RandomBSTInsert RandomAVLInsert OrderedBSTInsert OrderedAVLInsert " +
                "RandomBSTSearch RandomAVLSearch OrderedBSTSearch OrderedAVLSearch " +
                "RandomBSTNegativeSearch RandomAVLNegativeSearch OrderedBSTNegativeSearch OrderedAVLNegativeSearch");

        final int executionsNumber = 100;
        for (int power = 1; power < 10_000; power *= 10) {
            for (int multiplier = 1; multiplier < 10; multiplier++) {
                int size = multiplier * power;
                String[] array = new String[size];

                long randomBSTInsertTime = 0, randomAVLInsertTime = 0, orderedBSTInsertTime = 0, orderedAVLInsertTime = 0;
                long randomBSTSearchTime = 0, randomAVLSearchTime = 0, orderedBSTSearchTime = 0, orderedAVLSearchTime = 0;
                long randomBSTNegativeSearchTime = 0, randomAVLNegativeSearchTime = 0, orderedBSTNegativeSearchTime = 0, orderedAVLNegativeSearchTime = 0;

                for (int time = 0; time < executionsNumber; time++) {
                    //Random values
                    for (int i = 0; i < size; i++) {
                        array[i] = generateRandomWord();
                        randomBinarySearchTree.insert(array[i]);
                        randomAVLTree.insert(array[i]);
                    }
                    String randomWord = generateRandomWord();
                    randomBSTInsertTime += getTimeOfExecuting("RandomBSTInsert", randomWord);
                    randomAVLInsertTime += getTimeOfExecuting("RandomAVLInsert", randomWord);

                    int randomIndex = (int) (Math.random() * size);
                    randomBSTSearchTime += getTimeOfExecuting("RandomBSTSearch", array[randomIndex]);
                    randomAVLSearchTime += getTimeOfExecuting("RandomAVLSearch", array[randomIndex]);

                    String negativeWord = generateRandomWord();
                    for (int i = 0; i < size; i++) {
                        if (negativeWord.equals(array[i])) {
                            negativeWord = generateRandomWord();
                        }
                    }
                    randomBSTNegativeSearchTime += getTimeOfExecuting("RandomBSTSearch", negativeWord);
                    randomAVLNegativeSearchTime += getTimeOfExecuting("RandomAVLSearch", negativeWord);

                    //Ordered values
                    for (int i = 0; i < size; i++) {
                        array[i] = generateOrderedWord();
                        orderedBinarySearchTree.insert(array[i]);
                        orderedAVLTree.insert(array[i]);
                    }
                    randomWord = generateRandomWord();
                    orderedBSTInsertTime += getTimeOfExecuting("OrderedBSTInsert", randomWord);
                    orderedAVLInsertTime += getTimeOfExecuting("OrderedAVLInsert", randomWord);

                    randomIndex = (int) (Math.random() * size);
                    orderedBSTSearchTime += getTimeOfExecuting("OrderedBSTSearch", array[randomIndex]);
                    orderedAVLSearchTime += getTimeOfExecuting("OrderedAVLSearch", array[randomIndex]);

                    negativeWord = generateRandomWord();
                    for (int i = 0; i < size; i++) {
                        if (negativeWord.equals(array[i])) {
                            negativeWord = generateRandomWord();
                        }
                    }
                    orderedBSTNegativeSearchTime += getTimeOfExecuting("OrderedBSTSearch", negativeWord);
                    orderedAVLNegativeSearchTime += getTimeOfExecuting("OrderedAVLSearch", negativeWord);
                }
                String newLine = String.format("%d %d %d %d %d %d %d %d %d %d %d %d %d", size, randomBSTInsertTime / executionsNumber,
                        randomAVLInsertTime / executionsNumber, orderedBSTInsertTime / executionsNumber, orderedAVLInsertTime / executionsNumber,
                        randomBSTSearchTime / executionsNumber, randomAVLSearchTime / executionsNumber, orderedBSTSearchTime / executionsNumber,
                        orderedAVLSearchTime / executionsNumber, randomBSTNegativeSearchTime / executionsNumber, randomAVLNegativeSearchTime / executionsNumber,
                        orderedBSTNegativeSearchTime / executionsNumber, orderedAVLNegativeSearchTime / executionsNumber);
                printWriter.println(newLine);
            }
        }
        printWriter.close();
    }

    private static long getTimeOfExecuting(String treeFunction, String word) {
        long startTime = 0, endTime = 0;
        switch (treeFunction) {
            case "RandomBSTInsert":
                startTime = System.nanoTime();
                randomBinarySearchTree.insert(word);
                endTime = System.nanoTime();
                break;
            case "RandomAVLInsert":
                startTime = System.nanoTime();
                randomAVLTree.insert(word);
                endTime = System.nanoTime();
                break;
            case "RandomBSTSearch":
                startTime = System.nanoTime();
                randomBinarySearchTree.searchFor(word);
                endTime = System.nanoTime();
                break;
            case "RandomAVLSearch":
                startTime = System.nanoTime();
                randomAVLTree.searchFor(word);
                endTime = System.nanoTime();
                break;
            case "OrderedBSTInsert":
                startTime = System.nanoTime();
                orderedBinarySearchTree.insert(word);
                endTime = System.nanoTime();
                break;
            case "OrderedAVLInsert":
                startTime = System.nanoTime();
                orderedAVLTree.insert(word);
                endTime = System.nanoTime();
                break;
            case "OrderedBSTSearch":
                startTime = System.nanoTime();
                orderedBinarySearchTree.searchFor(word);
                endTime = System.nanoTime();
                break;
            case "OrderedAVLSearch":
                startTime = System.nanoTime();
                orderedAVLTree.searchFor(word);
                endTime = System.nanoTime();
                break;
            default:
                System.out.println("Wrong call of a function");
        }
        return endTime - startTime;
    }

    private static String generateRandomWord() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();

        return generator.generate(5 + (int) (Math.random() * 15));
    }

    public static String generateOrderedWord() {
        String generatedString = String.valueOf(currentChar);

        currentChar++;
        if (currentChar > 'z') {
            currentChar = 'a';
        }

        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();

        generatedString += generator.generate(ORDERED_WORD_LENGTH - 1);
        return generatedString;
    }

}

// That's the test cases for BinarySearchTree. I have been debugging them checking the children of root and drawing hierarchy manually cuz it's easier
//    binarySearchTree.insert("aaa");
//    binarySearchTree.insert("aaa");
//    binarySearchTree.insert("zzz");
//    binarySearchTree.insert("ggg");

// That's the test cases for AVLTree
//        avlTree.insert("aaa");
//        avlTree.insert("fff");;
//        avlTree.insert("zzz");
//        avlTree.insert("bbb");
//        avlTree.insert("ggg");
//        avlTree.insert("eee");
//        avlTree.insert("ccc");
//        avlTree.insert("ddd");