package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16173_정다운 {
	
	static int N;
	static int[][] arr;
	static boolean[][] visit;
	
	// 오른쪽, 아래 탐색 델타배열
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	static String res = "Hing";
	
	public static void main(String[] args) throws Exception {
		// 쩰리가 무사히 (N-1, N-1)에 갈 수 있는지
		// 쩰리는 오른쪽, 아래로 발판에 적힌 숫자만큼 이동할 수 있음
		// 점프하는 동안은 방향 전환 불가
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		visit = new boolean[N][N];
		
		for (int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		jelly(0,0);
		
		System.out.println(res);
		
	}

	static void jelly(int r, int c) {
		// N-1, N-1이 되면 return
		if ((r == N-1) && (c == N-1)) {
			res = "HaruHaru";
			return;
		}
		
		// 밟고있는 칸이 0일 수도 있다 = 제자리 탐색
		// -> 방문처리 필요
		visit[r][c] = true;
		
		// 밟고있는 칸 만큼 오른쪽 또는 아래로 이동
		for (int i=0; i<2; i++) {
			int nr = r + (dr[i]*arr[r][c]);
			int nc = c + (dc[i]*arr[r][c]);
			
			if (nr >= N || nc >= N || visit[nr][nc]) { // 범위를 벗어나거나 방문했던 곳이면
				continue;
			} else {
				jelly(nr, nc);				
			}
		}
	}
}
