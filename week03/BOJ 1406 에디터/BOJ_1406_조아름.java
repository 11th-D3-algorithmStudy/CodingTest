
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
        if (cursor == head.next) {
            return;
        } else {
            cursor = cursor.prev;
        }
    }

    void moveRight() {
        if (cursor == tail) {
            return;
        } else {
            cursor = cursor.next;
        }
    }

    void deleteLeft() {
        if (cursor == head.next) {
            return;
        } else {
            Node removeNode = cursor.prev;
            cursor.prev = removeNode.prev;
            removeNode.prev.next = cursor;
        }
        size--;
    }

    void insertLeft(char s) {
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

    
    
    void add(char s) {
    	Node newNode = new Node(s);
    	
    	
    		newNode.next = tail;
    		newNode.prev = tail.prev;
    		
    		newNode.next.prev = newNode;
    		newNode.prev.next = newNode;
    		
    		size++;
    		
    	
    }
    

    void print() {
    	StringBuilder sb = new StringBuilder();
        Node p = head.next;
        while (p != tail) {
        	sb.append(p.data);
            p = p.next;
        }
        System.out.println(sb);
    }
}

public class BOJ_1406_조아름 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        int l = text.length();

        DoublyLinkedList dl = new DoublyLinkedList();

        
        for (int i = 0; i < l; i++) {
            dl.add(text.charAt(i));
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
                dl.insertLeft(op.charAt(2));
            }
        }
        
        dl.print();

    }
}
