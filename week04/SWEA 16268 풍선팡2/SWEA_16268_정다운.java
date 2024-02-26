package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16268_정다운 {
	public static void main(String[] args) throws Exception {
		// 사방탐색
		// 꽃가루 최대값 구하기
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
			
			// 델타 배열 (상, 우, 하, 좌}
			int[] dr = {0, 1, 0, -1};
			int[] dc = {-1, 0, 1, 0};
			
			int max = 0;
			
			for (int r=0; r<M; r++) {
				for (int c=0; c<N; c++) {
					// 현재 위치
					int curr = arr[r][c];
					
					int sum = curr;
					
					// 사방탐색
					for (int d=0; d<4; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						
						if (nr >= 0 && nc >= 0 && nr < M && nc < N) {
							sum += arr[nr][nc];
						}
					}
					
					if (sum > max) {
						max = sum;
					}
				}
			}
			
			sb.append("#"+t+" "+max+"\n");
			
		}
		
		System.out.println(sb);
	}
}
