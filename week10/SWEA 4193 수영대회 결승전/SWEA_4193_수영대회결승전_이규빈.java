package week10;

import java.io.*;
import java.util.*;

public class SWEA_4193_수영대회결승전_이규빈 {
	static int N, A, B, C, D, res;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			// ----------------------- INPUT END ------------------------
			
			res = Integer.MAX_VALUE;
			
			visited[A][B] = true;
			dfs(A, B, 0);
			
			// 값이 갱신되지 않은 경우 = 도착할 수 없는 경우
			if (res == Integer.MAX_VALUE)  res = -1;
			
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	static void dfs(int r, int c, int time) {
		// backtracking
		if (time >= res)  return;
		
		// base case
		if (r == C && c == D) {
			res = Math.min(res, time);
			return;
		}
		
		// recursive case
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (!inMap(nr, nc) || visited[nr][nc])  continue;
			
			visited[nr][nc] = true; // 방문처리
			
			if (map[nr][nc] == 0) {
				dfs(nr, nc, time + 1);
			}
			else if(map[nr][nc] == 2) {
				dfs(nr, nc, time + 3-(time % 3));
			}
			
			visited[nr][nc] = false; // 방문처리 원상복구
		}
	}

	static boolean inMap(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
