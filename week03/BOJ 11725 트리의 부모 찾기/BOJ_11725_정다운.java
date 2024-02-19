package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int num;
	Node parent;
	boolean visit; // 방문여부
	
	Node() {
		
	}
	
	Node(int num) {
		this.num = num;
	}
}

public class BOJ_11725_정다운 {
	
	// 노드 연결 정보 저장 배열
	static List<Integer>[] connInfo;
	// 노드 저장 배열
	static Node[] nodes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 노드 개수
		int N = Integer.parseInt(br.readLine());
		
		// 노드 저장할 배열
		nodes = new Node[N+1]; // idx 1부터 사용
		
		for (int i=1; i<=N; i++) {
			nodes[i] = new Node();
			nodes[i].num = i;
		}
		
		// 연결 정보 저장 배열 길이 설정 및 원소 초기화
		connInfo = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			connInfo[i] = new ArrayList<>();
		}
		
		// N-1개의 노드 연결 정보 저장
		for (int i=1; i<=N-1; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 1번노드 idx
			int a = Integer.parseInt(st.nextToken());
			// 2번노드 idx
			int b = Integer.parseInt(st.nextToken());
			
			// 노드별 연결 정보 저장
			connInfo[a].add(b);
			connInfo[b].add(a);
		}
		
		// 루트는 1
		connectNode(1);
		
		// 2번 노드부터 부모 노드 번호 출력
		for (int i=2; i<=N; i++) {
			if (nodes[i].parent != null) {
				System.out.println(nodes[i].parent.num);
			}
		}
		
	}
	
	// https://codesign.tistory.com/122
	static void connectNode(int idx) {
		nodes[idx].visit = true; // 방문한 노드 표시
		
		for (int i: connInfo[idx]) {
			if (!nodes[i].visit) { // 방문한 노드는 이미 부모가 연결된 노드 ?
				nodes[i].parent = nodes[idx];
				connectNode(i);
			}
		}
		
	}
}
