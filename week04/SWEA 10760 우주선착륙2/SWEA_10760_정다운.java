package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760_정다운 {
	public static void main(String[] args) throws Exception {
		// 8방탐색
		// 현재 위치보다 낮은 지점이 4개 이상인지 탐색
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[M][N];
			
			// 배열 입력
			for (int r=0; r<M; r++) {
				StringTokenizer stM = new StringTokenizer(br.readLine());
				
				for (int c=0; c<N; c++) {
					arr[r][c] = Integer.parseInt(stM.nextToken());
				}
			}
			
			// 델타 배열 (좌상, 상, 우상, 우, 우하, 하, 좌하, 좌}
			int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
			int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
			
			int candidate = 0;
			
			for (int r=0; r<M; r++) {
				for (int c=0; c<N; c++) {
					// 현재 높이
					int curr = arr[r][c];
					
					int cnt = 0;
					
					// 팔방탐색
					for (int d=0; d<8; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						
						// 경계조건
						if (nr >= 0 && nc >= 0 && nr < M && nc < N) {
							if (arr[nr][nc] < curr) {
								cnt++;
							}
						}
					}
					
					if (cnt >= 4) {
						candidate++;
					}
				}
			}
			
			sb.append("#"+t+" "+candidate+"\n");
			
		}
		
		System.out.println(sb);
	}
}
