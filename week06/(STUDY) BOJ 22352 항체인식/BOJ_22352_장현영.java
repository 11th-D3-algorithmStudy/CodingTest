package week06;

import java.io.*;
import java.util.*;

public class BOJ_22352_장현영 {

	// 반례 확인
	// 초기 풀이 : 다른 지점 개수 cnt해서 그 cnt만큼의 영역을 첫 arr(투약 전 원본)에서 접근하면 반례 발생
	// arr1과 arr2을 덮어씌우면서 가능한지로 판단해야 함.
	
	
	static int n;
	static int m;
	static int[] dr = { 1, 0, 0, -1 };
	static int[] dc = { 0, 1, -1, 0 };
	static int[][] arr1;
	static int[][] arr2;
	static int num1 = Integer.MAX_VALUE;
	static int num2 = Integer.MAX_VALUE;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr1 = new int[n][m];
		arr2 = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr1[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr2[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int sr = -1;
		int sc = -1;
		
		out:
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr1[i][j] != arr2[i][j]) {
					sr = i;
					sc = j;
					num1 = arr1[i][j];
					num2 = arr2[i][j];
					break out;
				}
			}
		}
		// sr이 update안되어있으면 같은 배열
		if(sr == -1) {
			System.out.println("YES");
			return;
		}
		bfs(sr,sc);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr1[i][j] != arr2[i][j]) {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");
		
	}
	
	// 다른 지점의 영역을 찾아라!
	// 원본만 일단 bfs 접근하는데, 방문하는 곳은 두 번쨰 투약 후 배열로 덮어씌우기
	// bfs로 접근해서 update 후, update가 다 된 후에도 다른 곳이 발생했다면 No가 맞음
	
	
	public static void bfs( int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[n][m];
		visited[sr][sc] = true;
		queue.offer(new int[] { sr, sc });
		arr1[sr][sc] = num2; // 원본 배열에 덮어씌우기
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			arr1[r][c] = num2;
			for (int idx = 0; idx < 4; idx++) {
				int nr = r + dr[idx];
				int nc = c + dc[idx];
				if (0 <= nr && nr < n && 0 <= nc && nc < m && arr1[nr][nc] == num1 && !visited[nr][nc]) {
					// 갈 수 있음
					arr1[nr][nc] = num2;
					visited[nr][nc] = true;
					int[] next = { nr, nc };
					queue.offer(next);
				}
			}
		}
	}
}
