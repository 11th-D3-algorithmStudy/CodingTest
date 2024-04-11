package week10;

import java.io.*;
import java.util.*;

public class BOJ_2638_장현영 {

	// 치즈
	// 외부 공기를 bfs로 구하고 그 공기 이용해서 치즈 녹이는 방식
	// 외부 공기, 내부 공기 나눠주기!
	// 2면이상 외부 공기 닿은 곳 list에 추가
	// 2면이상 좌표뽑기는 성공하고 접근했는데, 계속 무한루프발생
	// 종료조건에서 1이 되는 순간 지우는 방식으로 재접근
	
	// 풀이 참고 : https://suhyeokeee.tistory.com/211

	static int n, m;
	static int[][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static List<int[]> data = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		while (true) {
			data.clear();
			if (isOver())
				break;// 종료조건 확실하게 (1이 다 없어지는 경우)
			// 1. bfs로 공기 구하기
			bfs(0, 0);
			// 2. 치즈중 외부치즈와 2면이상 맞닿은거 체크
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] == 1 && airCount(i, j) >= 2) {
						data.add(new int[] { i, j });
					}
				}
			}

			// 3. 녹아야 할 예정인 치즈 녹이기

			for (int[] each : data) {
				arr[each[0]][each[1]] = 0;
			}

			// 4. 외부치즈들 다시 0으로 바꿔줌
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] == 2) {
						arr[i][j] = 0;
					}
				}
			}
			ans++;

		}

		System.out.println(ans);
	}
	
	 public static boolean isOver() {
	        for(int i=0; i<n; i++) {
	            for(int j=0; j<m; j++) {
	                if(arr[i][j]==1) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	// 주변 공기 확인하기
	public static int airCount(int x, int y) {
		int airCnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dr[i];
			int ny = y + dc[i];

			if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == 2) {
				airCnt++;
			}
		}
		return airCnt;
	}

	public static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[n][m];
		queue.add(new int[] { x, y });
		visited[x][y] = true;
		arr[x][y] = 2; // 새롭게 2로 설정하기

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr >= 0 && nc >= 0 && nr < n && nc < m && !visited[nr][nc] && arr[nr][nc] == 0) {
					queue.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
					arr[nr][nc] = 2;
				}
			}
		}
	}

}
