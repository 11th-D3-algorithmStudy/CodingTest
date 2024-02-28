/*이 문제 또한 어떻게 풀지 고민이 많았는데 서영이가 doublyLinkedList로 풀었다 해서 풀어봤는데 어려웠다.*/
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
} // 노드 클래스 생성

class DoublyLinkedList { //더블리링크드리스트 생성
    Node head;
    Node tail;
    Node cursor;
    int size = 0;

    DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        cursor = tail; // head, tail 을 생성하고 cursor가 tail을 가리키게 함
        head.next = tail;
        tail.prev = head;
    }

    void moveLeft() {
        if (cursor == head.next) { // == head 가 아닌 이유는 삭제해야할 값이 왼쪽 값이기 때문에 그 값으로 가기 전에 막아줘야 한다.
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
        if (cursor == head.next) { // moveLeft와 마찬가지
            return;
        } else {
            Node removeNode = cursor.prev; // 삭제할 노드를 removeNode로 잡아주고 처리
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
            Node prevNode = cursor.prev; // 넣어줄 노드를 preNode로 설정하고 앞뒤 연결

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
        while (p != tail) { // 여기서 에러가 나는 걸 못 잡았는데 p!=null 일때까지 돌리면 tail일 때까지 돌아가서 오류가 난다.
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
            dl.add(text.charAt(i)); // dl 에 값 저장
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
