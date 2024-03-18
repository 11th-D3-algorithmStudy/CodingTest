package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2606_이규빈 {
	/*
	* Q. 바이러스
	* # 풀이시간 : 25분
	* # 메모리 / 실행시간 : 11600kb / 76ms
	* # 문제해석
	*  - 바이러스에 걸린 컴퓨터를 전부 탐색해야 함 -> DFS
	*  - 연결정보가 주어짐 -> 간선 정보를 2차원배열에 저장하는 방식으로 그래프 구현
	*/
	static int N; // 노드의 수
	static int M; // 간선의 수
	static boolean[][] edge; // 연결정보 저장
	static boolean[] visited; 
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edge = new boolean[N + 1][N + 1]; // 0행, 0열은 쓰지 않음
		visited = new boolean[N + 1]; // 0번은 쓰지 않음
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프이므로 순서 바꿔서도 저장
			edge[node1][node2] = true;
			edge[node2][node1] = true;
		}
		
		// ----------- INPUT END -------------
		
		res = 0;
		
		visited[1] = true; // 1번은 미리 방문 처리
		dfs(1);
		
		System.out.println(res);
	}

	private static void dfs(int cur) {
		for (int nxt = 1; nxt <= N; nxt++) {
			// 진행 조건 = 연결O && 방문X
			if(edge[cur][nxt] && !visited[nxt]) {
				visited[nxt] = true;
				res++;
				dfs(nxt);
			}
		}
	}

}
