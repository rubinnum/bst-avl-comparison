public class AVLNodeStack {
    int capacity;
    AVLNode[] mem;
    int elements;

    AVLNodeStack() {
        capacity = 10;
        mem = new AVLNode[capacity];
        elements = 0;
    }

    public AVLNode peek(){
        if (isEmpty())
            throw new IllegalStateException("The stack is empty");
        return mem[elements - 1];
    }

    public void pop(){
        if (isEmpty())
            throw new IllegalStateException("The stack is empty");
        elements--;
    }

    public void push(AVLNode node) {
        if (elements == capacity) {
            AVLNode[] temp = new AVLNode[capacity * 2];
            System.arraycopy(mem, 0, temp, 0, elements);
            capacity *= 2;
            mem = temp;
        }
        mem[elements++] = node;
    }

    public boolean isEmpty() {
        return elements == 0;
    }
}
