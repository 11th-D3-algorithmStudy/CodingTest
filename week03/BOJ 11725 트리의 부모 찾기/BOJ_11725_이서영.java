import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


class Node{
	int data;
	List<Node> neighbor = new LinkedList<>();
	Node parent;
	
	public Node() {}
	
	public Node(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public List<Node> getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(List<Node> neighbor) {
		this.neighbor = neighbor;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}

public class BOJ_11725_이서영 {
	// 처음에는 노드 크기 + 1의 배열을 만들어 상응하는 인덱스에 부모의 정수값을 넣어놓으려고 함
	// -> 이렇게 하려면 엣지의 두 수가 주어질때 어느쪽이 부모인지 알아야 함
	// 엣지가 순서대로 주어지면 상관없지만 반례가 있어 다른 방법 고민
	// 완전검색, dfs 찍먹 내 마음대로 개량해봄
	// 시간이 굉장히 오래걸렸다.. 나중에 dfs bfs 배우면 보완점이 보일듯
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numNodes = Integer.parseInt(br.readLine());
		Node[] tree = new Node[numNodes + 1];
		
		for (int i = 1; i <= numNodes; i++) {  // 각 인덱스 값을 가진 노드로 배열 초기화
			tree[i] = new Node(i);
		}
		
		for (int i = 1; i < numNodes; i++) {
			String[] edge = br.readLine().split(" ");
			int node1 = Integer.parseInt(edge[0]);
			int node2 = Integer.parseInt(edge[1]);
			tree[node1].getNeighbor().add(tree[node2]); // 엣지의 각 노드를 서로의 이웃으로 등록
			tree[node2].getNeighbor().add(tree[node1]);
		}
		
		boolean[] visited = new boolean[numNodes + 1];
		setParent(tree, visited, 1);
		
		for (int i = 2; i <= numNodes; i++) {
			System.out.println(tree[i].getParent().getData());
		}
	}
	
	static void setParent(Node[] arr, boolean[] visited, int index) {
		visited[index] = true; // 방문한 노드로 등록
		if (arr[index].getNeighbor().isEmpty()) { // 리프노드이면 리턴
			return;
		}
		List<Node> neighbors = arr[index].getNeighbor();
		for (int i = 0; i < neighbors.size(); i++) { // 해당 노드의 각 이웃 노드마다
			Node node = neighbors.get(i);
			if (!visited[node.getData()]) { // 이웃노드를 아직 방문하지 않은 상태면
				arr[node.getData()].setParent(arr[index]); // 이웃노드의 부모로 현재 노드를 등록
				setParent(arr, visited, node.getData()); // 재귀
			}
		}
	}
}
