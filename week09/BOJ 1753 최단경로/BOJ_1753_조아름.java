package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_조아름 { // 다익스트라 사용, 특정 시작점이 있고 가중치가 있기 때문에, 또한 음의 가중치가 없음
	static class Node implements Comparable<Node> { // 가중치 정렬을 포함한 Node 클래스 생성
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node n) {
			// TODO Auto-generated method stub
			return this.w - n.w;
		}
	}

	static int V;
	static List<Node>[] adjList;
	static int[] dist; // 거리 나타내는 배열
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호

		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		dist = new int[V + 1]; // 정점들이 1부터 시작하므로 1부터 저장.
		Arrays.fill(dist, INF);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			adjList[Integer.parseInt(st.nextToken())]
					.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		dijkstra(K);

		for (int i = 1; i <= V; i++) { // 출력도 1번부터, 0번부터 하면 다른 값이 나옴
			if (dist[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}

	}

	static void dijkstra(int k) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		dist[k] = 0; // 시작 노드 거리는 0으로 초기화

		pq.add(new Node(k, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (visited[curr.v])
				continue;
			visited[curr.v] = true;

			for (Node node : adjList[curr.v]) {
				if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					// 방문하지 않았고 node.v의 거리가 curr.v의 거리+node.w의 가중치보다 길면 더 짧은 것으로 갱신
					dist[node.v] = dist[curr.v] + node.w;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}

	}

}