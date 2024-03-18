package week06;

import java.io.*;
import java.util.*;

public class BOJ_13903_장현영 {
	// 출근
	// delta를 주면 그 것대로 움직일 수 있는 최솟값을 구한다.
	// 0행부터 시작해서 n-1행에 도달하면 완료
	// bfs로 접근하기
	// 1로만 갈 수 있음 0 불가
	// 시간초과 포인트
	// 1. start 값 구할 때 입력받는 이중 for문에서 처리하다보니 너무 늦어짐 -> 1차원 축소
	// 풀이시간 매우 길고, 도움 받아서 해결
	

	static int[][] arr;
	static int[] dr;
	static int[] dc;
	static int R;
	static int C;
	static int N;
	static int answer = Integer.MAX_VALUE;;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 나눠서 받기 입력받을 때 건드리면 error 확률 증가
		
		for (int j = 0; j < C; j++) {
			if (arr[0][j] == 1) { // 0행 출발점 저장
				int[] temp = { 0, j, 0 }; // 걸음 수까지
				list.add(temp);
			}
		}
		N = Integer.parseInt(br.readLine()); // N방탐색
		dr = new int[N];
		dc = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dr[i] = Integer.parseInt(st.nextToken());
			dc[i] = Integer.parseInt(st.nextToken());
		}
		if (!bfs(list))
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	public static boolean bfs(List<int[]> list) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		for (int[] start : list) {
			queue.offer(start);
			visited[start[0]][start[1]] = true;
		}
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int cnt = curr[2];
			if (r == R - 1) {// 최종 지점
				answer = Math.min(answer, cnt);
				return true;
			}
			for (int idx = 0; idx < N; idx++) {
				int nr = r + dr[idx];
				int nc = c + dc[idx];
				if (0 <= nr && nr < R && 0 <= nc && nc < C && arr[nr][nc] == 1 && !visited[nr][nc]) {
					// 갈 수 있음
					visited[nr][nc] = true;
					int[] next = { nr, nc, cnt + 1 };
					queue.offer(next);
				}
			}
		}
		return false; // 최종지점에 도달 못하고 끝나버린 경우
	}
}