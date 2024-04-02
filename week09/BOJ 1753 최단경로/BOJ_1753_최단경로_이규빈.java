package week09;

import java.io.*;
import java.util.*;

public class BOJ_1753_최단경로_이규빈 {  // 다익스트라 학습
	static final int INF = Integer.MAX_VALUE;
	static int V, E, K;
	static List<Node>[] adjList;  // 인접 리스트
	static int[] dist;  // 시작 정점에서 해당 정점까지의 최단경로
	
	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;  // 도착 정점
			this.w = w;  // 해당 정점과 도착 정점 사이 간선의 가중치
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		K = Integer.parseInt(br.readLine());  // 시작 정점 번호
		
		// 최단경로를 매우 큰 수로 초기화
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		// 인접리스트에 입력
		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[u].add(new Node(v, w));
		}
		
		// ------------------ INPUT END ----------------------
		
		dijstra();
		
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) {
				sb.append("INF\n");
			} else {
				sb.append(dist[i] + "\n");				
			}
		}
		System.out.println(sb);
	}

	private static void dijstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		dist[K] = 0; // 시작 정점의 최단경로는 0
		
		pq.add(new Node(K, 0));  
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll(); // 미방문 정점 중 경로 합 최소인 정점
			
			// 다음 정점(v)이 이미 방문했었다면 패쓰
			if (visited[cur.v])  continue;
			
			// 그렇지 않다면 방문
			visited[cur.v] = true;
			
			for (Node nxt : adjList[cur.v]) {
				// 갱신조건 : 미방문 정점 && 최단경로 더 작게 갱신 가능
				if (!visited[nxt.v] && dist[nxt.v] > dist[cur.v] + nxt.w) {
					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Node(nxt.v, dist[nxt.v]));  // 클래스 내부에서 Override했기 때문에 w를 기준으로 오름차순 정렬됨
					
				}
			}
		}
	}
}
