package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_1753_최단경로 {
	
	/* 풀이시간 : 240330 00:02 ~ 13:10 + 16:15 ~ 16:55
	 * 메인접근법
	 *     - 수업시간에 배운 다익스트라를 사용
	 *     - 배열과 우선순위큐를 사용하는 법 모두 사용해보았음
	 *     - 우선순위큐를 사용할 때는 Comparable<Node>를 통해 정렬? 가능
	 *     - 정점의 수를 -1씩 해주어서 기존 배열의 인덱스값에 맞춤
	 *     - start도 -1 해주었음
	 *     
	 * 1. 우선순위큐 사용 시
	 *     메모리 : 109728 KB / 시간 : 972 ms
	 * 
	 * 2. 배열 사용 시
	 *     메모리 : 107840 KB / 시간 : 2184 ms
	 */
	
	static List<Node>[] adjList; // 인접리스트
	static int[] dist;   // 거리 계산
	static int V, E, start;
	static int INF = Integer.MAX_VALUE;
	
	// 가중치 계산 및 노드 연걸을 위한 클래스 생성 
	static class Node implements Comparable<Node>{
		int v;
		int w;
		
		Node(int v, int w){
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			// 자연 순서를 기준으로 정렬
			// 작으면 음수, 같으면 0, 크면 양수를 반환
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		start = Integer.parseInt(br.readLine()); // 시작 위치
		dist = new int[V];
		Arrays.fill(dist, INF); // 거리값 모두 최대값으로 설정
		
		adjList = new ArrayList[V];
		for(int i=0; i<V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken())-1;
			int u = Integer.parseInt(st.nextToken());
			// 시작정점, 도착정점, 가중치 순으로 입력
			adjList[v].add(new Node(w, u));
		}
		// 인덱스와 숫자를 맞춰야 하기 떄문에 -1 해줌
		dijkstra(start-1);
//		System.out.println(Arrays.toString(dist));
		for(int i=0; i<V; i++) {
			if(dist[i] == INF) {
				System.out.println("INF");
				// 이 부분 때문에 계속 틀림
				// 연결 되어있지 않으면 종료라는 조건은 딱히 없었음
//				return;
			} else System.out.println(dist[i]);
		}
	}
	
//	public static void dijkstra(int start) {
//		boolean[] visited = new boolean[V];
//		dist[start] = 0;
//		
//		for(int i=0; i<V-1; i++) {
//			int min = INF;
//			int idx = -1;
//			
//			for(int j=0; j<V; j++) {
//				if(!visited[j] && min > dist[j]) {
//					min = dist[j];
//					idx = j;
//				}
//			}
//			if(idx == -1) break;
//			
//			visited[idx] = true;
//			
//			for(Node node : adjList[idx]) {
//				if(!visited[node.v] &&
//						dist[node.v] > dist[idx]+node.w) {
//					dist[node.v] = dist[idx]+node.w;
//				}
//			}
//		}
//	}
	
	public static void dijkstra(int start) {
		// 람다식으로 표현 가능
//        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curV = cur.v;
            int curW = cur.w;
            
            // 최단 거리가 이미 갱신되었는지 확인
            // 이미 짧게 계산되어 있다면 continue;
            if (dist[curV] < curW) continue;
            
            for(Node next : adjList[curV]) {
                int nextV = next.v;
                int nextW = curW + next.w;
                
                // 계산한 가중치가 더 작다면 그 값으로 교환
                if (dist[nextV] > nextW) {
                    dist[nextV] = nextW;
                    pq.offer(new Node(nextV, nextW));
                }
            }
        }
    }
}
