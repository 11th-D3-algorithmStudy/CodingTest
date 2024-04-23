package week12;

import java.io.*;
import java.util.*;

/*
 *  다익스트라 잘 몰라서 학습하면서 풀이함
 *  -> 그냥 궁금증 : 풀다보니 느낀건데, 다익스트라는 "BFS + 그리디"라고 보면 되는건가??
 */
public class BOJ_4485_젤다_이규빈 {
	static int N;
	static int[][] map, dist;
	static boolean[][] visited;
	static int tcNum = 1;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Node implements Comparable<Node> {
		int r,c, data;

		public Node(int r, int c, int data) {
			this.r = r;
			this.c = c;
			this.data = data;
		}
		
		// PQ 내에서 Node 객체들이 "data"를 기준으로 오름차순 정렬되도록 재정의
		@Override
		public int compareTo(Node o) {
			return this.data - o.data;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)  break;  // N=0이면 전체 입력 종료
			
			map = new int[N][N];   // 맵 정보
			dist = new int[N][N];  // 시작점으로부터의 최솟값을 저장할 배열
			visited = new boolean[N][N];
			
			// 맵 정보 입력
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					dist[r][c] = Integer.MAX_VALUE;  // 매우 큰 수로 초기화
				}
			}
			
			// ----- 입력 끝 -----
			
			dijkstra();			
			int res = dist[N-1][N-1];
			sb.append("Problem " + (tcNum++) + ": " + res + "\n");
		} // 테스트케이스 끝
		System.out.println(sb);
	}
	
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, map[0][0]));
		dist[0][0] = map[0][0];  // 시작점 데이터 초기화
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (!inMap(nr, nc) || visited[nr][nc])  continue;  // 경계조건
				
				visited[nr][nc] = true;
				
				// 최솟값 갱신
				int newData = cur.data + map[nr][nc];
				if (dist[nr][nc] > newData) {
					dist[nr][nc] = newData;
					pq.offer(new Node(nr, nc, newData));
				}
			}
		}
	}

	static boolean inMap(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
