public class Queue<T> {
    // Node class for custom singly linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front;  
    private Node<T> rear;   
    private int size;       

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear == null) front = rear = newNode;
        
        else
        {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        T value = front.data;
        front = front.next;
        if (front == null) rear = null;
        
        size--;
        return value;
    }


    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot peek.");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    // Get current size of the queue
    public int size() {
        return size;
    }
}
