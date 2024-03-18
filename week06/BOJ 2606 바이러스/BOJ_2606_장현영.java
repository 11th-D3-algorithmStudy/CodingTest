package week06;

import java.io.*;
import java.util.*;

public class BOJ_2606_장현영 {
	/*
	 * 바이러스 연결되어있는 노드들 찾기 bfs/ dfs 모두 접근
	 */
	static int[][] arr;
	static boolean[] visited;
	static int node;
	static int dfsCnt = 0; // dfs에서 사용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		node = Integer.parseInt(br.readLine());
		int edge = Integer.parseInt(br.readLine());
		arr = new int[node + 1][node + 1]; // 0번 사용 x
		visited = new boolean[node + 1];
		for (int temp = 0; temp < edge; temp++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			// 그래프 양방향
			arr[i][j] = 1;
			arr[j][i] = 1;
		}
		//System.out.println(bfs());
		dfs(1);
		System.out.println(dfsCnt);
	}

	public static int bfs() {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		visited[1] = true; // 1번 노드 방문처리
		queue.offer(1);
		while (!queue.isEmpty()) {
			int nextNode = queue.poll();
			for (int i = 1; i <= node; i++) {
				if (!visited[i] && arr[nextNode][i] == 1) { // 첫방문이면서 갈수있는 노드
					queue.offer(i);
					visited[i] = true;
					cnt++;
				}

			}
		}
		return cnt;

	}

	// dfs로도 접근해보기
	public static void dfs(int nextNode) {
		visited[nextNode] = true; // 노드 방문처리
		for (int i = 1; i <= node; i++) {
			if (!visited[i] && arr[nextNode][i] == 1) { // 첫방문이면서 갈수있는 노드
				dfsCnt++;
				dfs(i);
			}
		}
	}

}
