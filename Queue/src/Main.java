import java.util.ArrayList;
import java.util.List;

//interface IQueuable<T> {
//    // Adds a value to the queue and returns the new queue.
//    void enqueue(T value);
//
//    // Removes an item from the queue and returns the item removed.
//    T dequeue();
//
//    // Returns a list of all the items in the queue.
//    List<T> getQueue();
//
//    // Returns the number of items in the queue.
//    int size();
//}
//
//class FIFOQueue<T> implements IQueuable<T> {
//    private List<T> queue;
//
//    public FIFOQueue() {
//        queue = new ArrayList<>();
//    }
//
//    @Override
//    public void enqueue(T value) {
//        queue.add(value);
//    }
//
//    @Override
//    public T dequeue() {
//        if (isEmpty()) {
//            throw new IllegalStateException("Queue is empty.");
//        }
//        return queue.remove(0);
//    }
//
//    @Override
//    public List<T> getQueue() {
//        return new ArrayList<>(queue);
//    }
//
//    @Override
//    public int size() {
//        return queue.size();
//    }
//
//    public boolean isEmpty() {
//        return queue.isEmpty();
//    }
//}
//
//class LIFOQueue<T> implements IQueuable<T> {
//    private List<T> queue;
//
//    public LIFOQueue() {
//        queue = new ArrayList<>();
//    }
//
//    @Override
//    public void enqueue(T value) {
//        queue.add(value);
//    }
//
//    @Override
//    public T dequeue() {
//        if (isEmpty()) {
//            throw new IllegalStateException("Queue is empty.");
//        }
//        int lastIndex = queue.size() - 1;
//        return queue.remove(lastIndex);
//    }
//
//    @Override
//    public List<T> getQueue() {
//        return new ArrayList<>(queue);
//    }
//
//    @Override
//    public int size() {
//        return queue.size();
//    }
//
//    public boolean isEmpty() {
//        return queue.isEmpty();
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        // Your main program logic goes here
//    	// Create a FIFO queue
////        IQueuable<String> fifoQueue = new FIFOQueue<>();
////
////        // Add items to the FIFO queue
////        fifoQueue.enqueue("Item 1");
////        fifoQueue.enqueue("Item 2");
////        fifoQueue.enqueue("Item 3");
////
////        // Dequeue items from the FIFO queue
////        String dequeuedItem = fifoQueue.dequeue();
////        System.out.println("Dequeued item: " + dequeuedItem);
////
////        // Get the current state of the FIFO queue
////        List<String> fifoQueueState = fifoQueue.getQueue();
////        System.out.println("FIFO Queue: " + fifoQueueState);
////        System.out.println("FIFO Queue Size: " + fifoQueue.size());
////        fifoQueue.enqueue("Item 4");
////        System.out.println(fifoQueue.getQueue());
//        
//        // Create a LIFO queue
//        IQueuable<String> lifoQueue = new LIFOQueue<>();
//
//        // Add items to the LIFO queue
//        lifoQueue.enqueue("Item A");
//        lifoQueue.enqueue("Item B");
//        lifoQueue.enqueue("Item C");
//
//        // Dequeue items from the LIFO queue
//        String poppedItem = lifoQueue.dequeue();
//        System.out.println("Popped item: " + poppedItem);
//
//        // Get the current state of the LIFO queue
//        List<String> lifoQueueState = lifoQueue.getQueue();
//        System.out.println("LIFO Queue: " + lifoQueueState);
//        System.out.println("LIFO Queue Size: " + lifoQueue.size());
//    }
//}


// b)
//interface IQueuable {
//    void enqueue(String value);
//    String dequeue();
//    String[] getQueue();
//    int size();
//}
//
//class FIFOQueue implements IQueuable {
//    private String[] queue;
//    private int front;
//    private int rear;
//    private int capacity;
//
//    public FIFOQueue() {
//        this.capacity = 10; // Initial capacity (you can adjust it)
//        this.queue = new String[capacity];
//        this.front = 0;
//        this.rear = -1;
//    }
//
//    @Override
//    public void enqueue(String value) {
//        if (rear == capacity - 1) {
//            // Double the capacity if the queue is full
//            capacity *= 2;
//            String[] newQueue = new String[capacity];
//            for (int i = front, j = 0; i <= rear; i++, j++) {
//                newQueue[j] = queue[i];
//            }
//            queue = newQueue;
//            rear = size() - 1;
//            front = 0;
//        }
//        queue[++rear] = value;
//    }
//
//    @Override
//    public String dequeue() {
//        if (isEmpty()) {
//            throw new IllegalStateException("Queue is empty");
//        }
//        String removedItem = queue[front];
//        queue[front++] = null;
//        return removedItem;
//    }
//
//    @Override
//    public String[] getQueue() {
//        String[] result = new String[size()];
//        for (int i = front, j = 0; i <= rear; i++, j++) {
//            result[j] = queue[i];
//        }
//        return result;
//    }
//
//    @Override
//    public int size() {
//        return rear - front + 1;
//    }
//
//    private boolean isEmpty() {
//        return front > rear;
//    }
//}
//
//class LIFOQueue implements IQueuable {
//    private String[] queue;
//    private int top;
//    private int capacity;
//
//    public LIFOQueue() {
//        this.capacity = 10; // Initial capacity (you can adjust it)
//        this.queue = new String[capacity];
//        this.top = -1;
//    }
//
//    @Override
//    public void enqueue(String value) {
//        if (top == capacity - 1) {
//            // Double the capacity if the queue is full
//            capacity *= 2;
//            String[] newQueue = new String[capacity];
//            for (int i = 0; i <= top; i++) {
//                newQueue[i] = queue[i];
//            }
//            queue = newQueue;
//        }
//        queue[++top] = value;
//    }
//
//    @Override
//    public String dequeue() {
//        if (isEmpty()) {
//            throw new IllegalStateException("Queue is empty");
//        }
//        String removedItem = queue[top];
//        queue[top--] = null;
//        return removedItem;
//    }
//
//    @Override
//    public String[] getQueue() {
//        String[] result = new String[size()];
//        for (int i = 0; i <= top; i++) {
//            result[i] = queue[i];
//        }
//        return result;
//    }
//
//    @Override
//    public int size() {
//        return top + 1;
//    }
//
//    private boolean isEmpty() {
//        return top == -1;
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        IQueuable fifoQueue = new FIFOQueue();
//        fifoQueue.enqueue("A");
//        fifoQueue.enqueue("B");
//        fifoQueue.enqueue("C");
//        System.out.println("FIFO Queue: " + String.join(", ", fifoQueue.getQueue()));
//
//        IQueuable lifoQueue = new LIFOQueue();
//        lifoQueue.enqueue("X");
//        lifoQueue.enqueue("Y");
//        lifoQueue.enqueue("Z");
//        System.out.println("LIFO Queue: " + String.join(", ", lifoQueue.getQueue()));
//    }
//}

// c
//import java.util.LinkedList;
//import java.util.List;
//
//interface IQueuable<T> {
//    // Adds a value to the queue and returns the new queue.
//    void enqueue(T value);
//
//    // Removes an item from the queue and returns the item removed.
//    T dequeue();
//
//    // Returns a list of all the items in the queue.
//    List<T> getQueue();
//
//    // Returns the number of items in the queue.
//    int size();
//}
//
//class Queue<T> implements IQueuable<T> {
//    private LinkedList<T> queue;
//
//    public Queue() {
//        queue = new LinkedList<>();
//    }
//
//    @Override
//    public void enqueue(T value) {
//        queue.add(value);
//    }
//
//    @Override
//    public T dequeue() {
//        if (isEmpty()) {
//            throw new IllegalStateException("Queue is empty.");
//        }
//        return queue.remove();
//    }
//
//    @Override
//    public List<T> getQueue() {
//        return new LinkedList<>(queue);
//    }
//
//    @Override
//    public int size() {
//        return queue.size();
//    }
//
//    public boolean isEmpty() {
//        return queue.isEmpty();
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        IQueuable<String> queue = new Queue<>();
//        queue.enqueue("A");
//        queue.enqueue("B");
//        queue.enqueue("C");
//
//        System.out.println("Queue: " + queue.getQueue());
//        System.out.println("Dequeue: " + queue.dequeue());
//        System.out.println("Queue after dequeue: " + queue.getQueue());
//    }
//}
//
//


import java.util.LinkedList;
import java.util.List;
interface IStackable<T> {
    // Pushes a value onto the stack.
    void push(T value);

    // Pops and returns the top element from the stack.
    T pop();

    // Returns a list of all the elements in the stack.
    List<T> getStack();

    // Returns the number of elements in the stack.
    int size();
}

class Stack<T> implements IStackable<T> {
    private LinkedList<T> stack;

    public Stack() {
        stack = new LinkedList<>();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return stack.pop();
    }

    @Override
    public List<T> getStack() {
        return new LinkedList<>(stack);
    }

    @Override
    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        IStackable<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Stack: " + stack.getStack());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack after pop: " + stack.getStack());
    }
}

// linkedlist more efficient for large code base
// can resized itself if need more elements
// no need to array copying
// efficients for both stacks and queues