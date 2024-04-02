package week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_정다운 {
	
	static class Point implements Comparable<Point>{
		int v,w;

		public Point(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static final int INF = 987654321;
	static int V, E; // 정점, 간선 개수
	static List<Point>[] connInfo;
	static int[] dist; // 최소비용 저장해둘 배열
	static boolean[] visit;
	static Point start;
	
	public static void main(String[] args) throws Exception {
		// 다익스트라~ 
		// 수업에서 사용한 코드 참고
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		connInfo = new ArrayList[V+1]; // V는 1부터
		for (int i=0; i<V+1; i++) {
			connInfo[i] = new ArrayList<>();
		}
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		visit = new boolean[V+1];
		start = new Point(Integer.parseInt(br.readLine()), 0);
		
		for (int i=0; i<E; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st2.nextToken());
			int B = Integer.parseInt(st2.nextToken());
			int C = Integer.parseInt(st2.nextToken());
			
			connInfo[A].add(new Point(B,C)); // A->B
		}
		
		bfs();
		// dist에 최단경로 저장 완료 
		
		for (int i=1; i<=V; i++) {
			if (dist[i] == INF) { // 경로가 업데이트 되지 않았다면
				sb.append("INF\n");
			} else {
				sb.append(dist[i]+"\n");				
			}
		}
		
		System.out.println(sb);

	}

	static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		pq.add(new Point(start.v, start.w));
		
		while(!pq.isEmpty()) {
			Point next = pq.poll();
			
			if (visit[next.v]) continue;
			visit[next.v] = true;
			dist[next.v] = next.w;
			
			for (Point p : connInfo[next.v]) {
				if (!visit[p.v] && dist[p.v] > dist[next.v]+p.w) {
					dist[p.v] = dist[next.v]+p.w;
					pq.add(new Point(p.v, dist[p.v]));
				}
			}
		}
	}
}
