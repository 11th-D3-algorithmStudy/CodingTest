
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1504_이윤주 {
	// 특정한 최단 경로
	// 최단경로 -> 다익스트라

	static class Node implements Comparable<Node> {
		int end;
		int dist;

		public Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}

		// pq 정렬을 위해 compareTo 정의
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	static int N, E, v1, v2;
	static List<Node>[] adjList; // 인접리스트
	static final int INF = 200000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 개수 2~800
		E = Integer.parseInt(st.nextToken()); // 간선 개수 0~200000
		adjList = new ArrayList[N + 1]; // 정점 번호 1~N

		// 리스트 초기화해주기
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		// 간선입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken()); // 거리 1~1000

			// 양방향 간선
			adjList[A].add(new Node(B, dist));
			adjList[B].add(new Node(A, dist));
		} // 간선입력 끝
			// 경유해야되는 정점 입력
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		int result1 = 0;
		int result2 = 0;

		// 1->v1->v2->N 으로 가는 경우
		result1 += dijkstra(1, v1);
		result1 += dijkstra(v1, v2);
		result1 += dijkstra(v2, N);

		// 1->v2->v1->N으로 가는 경우
		result2 += dijkstra(1, v2);
		result2 += dijkstra(v2, v1);
		result2 += dijkstra(v1, N);

		// 결과가 둘 다 INF인 경우면 경로를 찾지 못했으므로 -1 출력
		int answer = (result1 >= INF && result2 >= INF) ? -1 : Math.min(result1, result2);

		// 최단 경로 길이 출력
		System.out.println(answer);
	}

	// start에서 end까지 가는 최단 경로 반환
	private static int dijkstra(int start, int end) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>(); //가중치 오름차순으로 정렬하는 pq
//		boolean[] visited = new boolean[N + 1]; //방문체크 -> 이동했던 간선 다시 이동 가능...없어도 됨?
		int[] dist = new int[N + 1]; //거리배열
		Arrays.fill(dist, INF); //거리 배열 INF값으로 초기화
		
		dist[start] = 0; //시작->시작은 거리가 0
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll(); //가중치 가장 작은 노드 뽑기
			int nowNum = node.end;
			int distance = node.dist;
			
//			if(visited[nowNum]) continue; //이미 방문했으면 비용을 알고 있다는 뜻
//			visited[nowNum] = true; //방문체크
			
			//현재 노드까지의 거리가 기록된 거리보다 길면 해당 경로는 버림
			if(dist[nowNum] < distance) continue;
			
			//현재 노드에 연결된 모든 노드 탐색
			for(Node nextNode : adjList[nowNum]) {
				int nextNum = nextNode.end;
				int nextDist = nextNode.dist;
				
				//현재 노드 거쳐서 가는게 더 짧으면 갱신
				if(distance + nextDist < dist[nextNum]) {
					dist[nextNum] = distance + nextDist;
					pq.add(new Node(nextNum, dist[nextNum]));
				}
			}
		}//dist 배열 갱신 완료
		
		return dist[end]; //end까지 가는 최소 거리 반환
	}

}
