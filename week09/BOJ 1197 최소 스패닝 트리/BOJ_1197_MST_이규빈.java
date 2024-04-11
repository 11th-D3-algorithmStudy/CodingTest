package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197_MST_이규빈 {
	// 간선 객체를 생성하기 위한 클래스
	// - 정렬 기준 재정의를 위해 Comparable 인터페이스를 구현
	static class Edge implements Comparable<Edge> {
		int st, ed, w;
		
		public Edge(int st, int ed, int w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
		
		// "w를 기준으로" Edge 객체를 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	static int[] p;  // 인덱스 = 정점 번호, 값 = 해당 정점이 속한 집합의 대표자
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());  // 정점 개수
		int E = Integer.parseInt(st.nextToken());  // 간선 개수
		
		// 배열에 간선 정보 저장
		Edge[] edges = new Edge[E + 1];
		edges[0] = new Edge(0, 0, -1000001);  // 0번은 사용 X (정렬 후에도 항상 0번에 있도록 만들기)
		
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());  // 정점 A, B 사이 간선의 가중치
			
			edges[i] = new Edge(A, B, C);
		}
		
		// ------------------------ INPUT END ------------------------------
		
		// 1. "w를 기준으로" 간선을 오름차순 저장
		// - 클래스 내부에 정렬기준을 설정해놨으므로, 이렇게만 적어도 "w를 기준으로" 오름차순 정렬됨
		Arrays.sort(edges);
				
		// 2. Make-Set
		p = new int[V + 1];  // 0번 미사용
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
		
		// 3. Find-Set
		int res = 0;  // 최소비용 저장
		int pick = 0;  // 이미 뽑은 간선 개수 저장
		
		for (int i = 1; i <= E; i++) {
			int px = findSet(edges[i].st);  // x가 속한 집합의 대표
			int py = findSet(edges[i].ed);  // y가 속한 집합의 대표
			
			// "사이클이 만들어지지 않을 때만(= 대표가 다를때만)" MST로 연결
			if (px != py) {
				union(px, py);
				res += edges[i].w;
				pick++;
			}
			
			// 간선 V-1개 뽑아 MST 완성되면 Union Find 종료
			if (pick == V - 1)  break;
		}
		
		System.out.println(res);
	}

	
	// 집합 통합
	// - 애초에 넘겨주는 매개변수가 대표이므로, 여기서는 findSet 해줄 필요 없다.
	private static void union(int px, int py) {
		p[py] = px;
	}

	
	// 대표자 찾기
	private static int findSet(int x) {
//		if (p[x] == x)  return x;
//		return findSet(p[x]);
		
		// Path Compression
		if (x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
}