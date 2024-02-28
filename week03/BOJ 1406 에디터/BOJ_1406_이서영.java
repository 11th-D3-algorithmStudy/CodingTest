
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Node {
	Node prev;
	Node next;
	char data;

	public Node() {
	}

	public Node(char data) {
		this.data = data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

}

class myList {
	Node head;
	Node tail;
	Node curr;

	public myList() {
		Node head = new Node();
		Node tail = new Node();
		head.setNext(tail);
		tail.setPrev(head);
		curr = tail;
		this.head = head;
		this.tail = tail;
	}

	void add(Node n) {
		n.setNext(curr);
		n.setPrev(curr.prev);
		curr.getPrev().setNext(n);
		curr.setPrev(n);
	}

	void remove() {
		if (curr.getPrev().getPrev() != null) {
			curr = curr.getPrev();
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
			moveRight();
		}
	}

	void moveLeft() {
		if (curr.getPrev() != head) {
			curr = curr.getPrev();
		}
	}

	void moveRight() {
		if (curr.getNext() != null) {
			curr = curr.getNext();
		}
	}

	void print() {
		StringBuilder sb = new StringBuilder();
		curr = head.getNext();
		while (curr.getNext() != null) {
			sb.append(curr.getData());
			curr = curr.getNext();
		}
		System.out.println(sb);
	}
}

public class BOJ_1406_이서영 {
	// 처음에는 LinkedList 컬렉션을 사용해서 풀었는데 add(i, data), remove(i) 를 사용하다보니
	// 각각의 시간복잡도가 리스트를 순회해야 해 O(n)이라 리스트 직접 구현
	// 리스트에 curr로 커서를 구현해 앞뒤로 이동 가능하게 만듦
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] lineRead = br.readLine().toCharArray();
		myList text = new myList();
		for (char c : lineRead) {
			text.add(new Node(c));
		}
		int numOfOp = Integer.parseInt(br.readLine());
		for (int i = 0; i < numOfOp; i++) {
			String op = br.readLine();
			if (op.equals("L")) {
				text.moveLeft();
			} else if (op.equals("D")) {
				text.moveRight();
			} else if (op.equals("B")) {
				text.remove();
			} else if (op.charAt(0) == 'P') {
				text.add(new Node(op.charAt(2)));
			}
		}

		text.print();
	}

}
