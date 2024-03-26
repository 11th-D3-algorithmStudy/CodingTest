package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SOFT_6246_순서방문_이규빈 {
	/*
	* # 풀이시간 : 1시간 40분
	* # 메모리 / 실행시간 : 10.23MB / 70ms
	* # 접근법
	*  경로 찾기 -> DFS
	*  방문해야 하는 지점이 여러 개
	*   -> 방문해야 할 지점이긴 하지만 이번 차례는 아닌 경우 방문하지 않도록 하는 조건을 추가해야 한다.
	*   -> 따라서 map에 방문 순번을 덮어씌우고, 현재 방문해야할 순번이 아닌 경우 진행하지 않도록 한다.
	*/
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int startR, startC, endR, endC;
	static int res;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n + 1][n + 1]; // 0행, 0열은 사용하지 않음
		visited = new boolean[n + 1][n + 1];
		
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				// 벽인 경우, 방문처리 해놓기
				if (map[r][c] == 1)  visited[r][c] = true;
			}
		}
		
		startR = -1;
		startC = -1;
		endR = -1;
		endC = -1;
		
		// "현재 방문할 순서인지" 판단하기 위해 map에다 방문 순번을 덮어씌운다.
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = i;
			
			// 시작 지점 저장하기
			if (i == 1) {
				startR = r;  
				startC = c;
			}
			
			// 종료 지점 저장하기
			if (i == m) {
				endR = r;  
				endC = c;
			}
		}
		
		// ------------------- INPUT END ----------------------
		
		res = 0;
		
		// 시작지점의 값도 1이지만, 미리 방문처리 해놓으므로 벽과 헷갈리지 않는다.
		// 시작지점부터 시작하므로, 다음으로 방문해야 할 순번에 2를 넣는다.
		visited[startR][startC] = true;
		dfs(startR, startC, 2);
		
		System.out.println(res);
	} // End of main
	
	
	/**
	 * @param turn: 현재 방문해야할 지점의 순번
	 */
	private static void dfs(int r, int c, int turn) {
		// base case - 마지막 지점에 도착
		if (r == endR && c == endC) {
			res++;
			return;
		}
		
		// recursive case
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 진행조건 = 경계조건 && 방문 X
			if (inMap(nr, nc) && !visited[nr][nc]) {
				// 현재 방문할 순번이 아닌 경우, 패스
				if (map[nr][nc] != 0 && map[nr][nc] != turn) {
					continue;
				} 
				// 현재 방문할 순번인 경우, 진행하고 순번 하나 올리기
				else if (map[nr][nc] != 0 && map[nr][nc] == turn) {
					visited[nr][nc] = true;
					dfs(nr, nc, turn + 1);
					visited[nr][nc] = false; // Backtracking
				}
				// 그외 0인 경우
				else {
					visited[nr][nc] = true;
					dfs(nr, nc, turn);
					visited[nr][nc] = false; // Backtracking
				}
			}
		}
	} // End of dfs

	
	private static boolean inMap(int r, int c) {
		return 1 <= r && r <= n && 1 <= c && c <= n;
	}
}
