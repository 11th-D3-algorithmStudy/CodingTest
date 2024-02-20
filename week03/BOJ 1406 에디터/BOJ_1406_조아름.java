
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    char data;
    Node prev;
    Node next;

    Node() {

    }

    Node(char data) {
        this.data = data;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;
    Node cursor;
    int size = 0;

    DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        cursor = tail;
        head.next = tail;
        tail.prev = head;
    }

    void moveLeft() {
        if (cursor == head) {
            return;
            // cursor = head;
        } else {
            cursor = cursor.prev;
        }
    }

    void moveRight() {
        if (cursor == tail) {
            return;
            // cursor = tail;
        } else {
            cursor = cursor.next;
        }
    }

    void deleteLeft() {
        if (cursor == head) {
            return;
        } else {
            Node removeNode = cursor.prev;
            cursor.prev = removeNode.prev;
            removeNode.prev.next = cursor;
        }
        size--;
    }

    void insert(char s) {
        Node newNode = new Node(s);
        if (cursor == head) {
            return;
        } else {
            Node prevNode = cursor.prev;

            cursor.prev = newNode;
            prevNode.next = newNode;
            
            newNode.prev = prevNode;
            newNode.next = cursor;
        }
        size++;
    }

    void addFirst(char s) {
        Node newNode = new Node(s);
        newNode.next = head;

        if (head != null)
            head.prev = newNode;

        head = newNode;
        size++;

        if (head.next == null)
            tail = head;
    }

    void addLast(char s) {
        Node newNode = new Node(s);

        if (size == 0) {
            addFirst(s);
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
    }

    void print() {
        Node p = head.next;
        while (p != null) {
            System.out.print(p.data);
            p = p.next;
        }
    }
}

public class BOJ_1406_조아름 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        int l = text.length();

        DoublyLinkedList dl = new DoublyLinkedList();

        for (int i = 0; i < l; i++) {
            if (i == 0) {
                dl.addFirst(text.charAt(i));
            } else {
                dl.addLast(text.charAt(i));
            }
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String op = br.readLine();

            if (op.equals("L")) {
                dl.moveLeft();
            } else if (op.equals("D")) {
                dl.moveRight();
            } else if (op.equals("B")) {
                dl.deleteLeft();
            } else if (op.charAt(0) == 'P') {
                dl.insert(op.charAt(2));
            }
        }
        
        dl.print();

    }
}
