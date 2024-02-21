import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int num;
	Node parent;
	Node connect;
	
	Node () {}
	
	Node (int num, Node parent, Node connect) {
		this.num = num;
		this.parent = parent;
		this.connect = connect;
	}
}

public class BOJ_11725_이윤주 {
	public static void main(String[] args) throws Exception {
		//트리의 부모 찾기
		//10시 28분 시작 12시 32분까지 고민....
		//트리의 루트 = 1
		//트리 -> 배열로 구현? -> 완전 이진트리가 아니니까 다른방식이 좋겠다 -> 노드 클래스 만들기
		//노드의 개수 N <= 10^5
		
		//1. 런타임 에러(NullPointerError) 발생 - 질문게시판에서 반례 확인
		/*
		입력
		10
		1 3
		5 4
		3 2
		2 7
		5 7
		9 10
		5 10
		6 8
		1 6
		출력
		3
		1
		5
		7
		1
		2
		6
		10
		5
		=> 입력된 두 노드가 모두 부모가 지정되지 않은 경우를 생각하지 않음!!
		 */
		
		//2. 모두 부모가 지정되지 않았을 때, 임의로 작은 번호를 부모로 하는 로직? -> 여전히 런타임에러
		//3. Node클래스에 연결되는 상태를 하나 추가해서 
		//나중에 부모랑 연결할 때 연결된게 있으면 부모 연결로 바꿔주는 로직으로 변경
		
		//그래도 여전히 런타임 에러...
		//4. 배열 -> 링크드 리스트로 수정 -> 런타임은 해결..?인데 시간초과
		//5. BFS 사용해야된다는데....공부가 필요함
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//노드의 개수 N
		int n = Integer.parseInt(br.readLine());
		
		//트리 노드 배열 선언
		List<Node> tree = new LinkedList<>();
		//노드 번호로 트리 배열 채워둠
		tree.add(new Node(0,null,null)); //더미
		for(int i = 1; i <= n; i++) {
			tree.add(new Node(i, null, null));
		}
		
		//N - 1 줄 동안 정점 연결 입력받음
		for(int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeNum = Integer.parseInt(st.nextToken());
			int connectNode = Integer.parseInt(st.nextToken());
			
			//노드 번호가 0이면 루트 노드이므로, 부모 연결
			if(nodeNum == 1) {
				tree.get(connectNode).parent = tree.get(1);
				if (tree.get(connectNode).connect != null) {
					//방금 자식으로 연결된 노드에 연결관계 있으면 부모 자식 연결
					tree.get(connectNode).connect.parent = tree.get(connectNode);
				}
			} else if (connectNode == 1) {
				//노드 번호가 1이면 루트 노드이므로, 부모 연결
				tree.get(nodeNum).parent = tree.get(1);
				if (tree.get(nodeNum).connect != null) {
					//방금 자식으로 연결된 노드에 연결관계 있으면 부모 자식 연결
					tree.get(nodeNum).connect.parent = tree.get(nodeNum);
				}
			} else if (tree.get(nodeNum).parent == null && tree.get(connectNode).parent == null) { 
				//둘 다 부모 노드가 없는 경우 -> 연결관계 저장
				tree.get(nodeNum).connect = tree.get(connectNode);
				tree.get(connectNode).connect = tree.get(nodeNum);
			} else if (tree.get(connectNode).parent == null) { 
				//부모 노드가 연결되지 않은 노드에 부모 연결
				tree.get(connectNode).parent = tree.get(nodeNum);
				if (tree.get(connectNode).connect != null) {
					//방금 자식으로 연결된 노드에 연결관계 있으면 부모 자식 연결
					tree.get(connectNode).connect.parent = tree.get(connectNode);
				}
			} else {
				tree.get(nodeNum).parent = tree.get(connectNode);
				if (tree.get(nodeNum).connect != null) {
					//방금 자식으로 연결된 노드에 연결관계 있으면 부모 자식 연결
					tree.get(nodeNum).connect.parent = tree.get(nodeNum);
				}
			}
		}
		
		//부모 노드 번호 출력
		for (int i = 2; i <= n; i++) {
			System.out.println(tree.get(i).parent.num);
		}
	}
}
