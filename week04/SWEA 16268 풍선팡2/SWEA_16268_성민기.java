package another;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16268_풍선팡2 {
	public static void main(String[] args) throws IOException {
		
		/*
		 * 풀이시간 : 240227 20:10 ~ 20:30
		 * 메인접근법 : 델타배열로 기준값의 상하좌우만 살피면 된다.
		 * 막힌부분 : 런타임에러가 발생했었던 이슈
		 *          - if(0 <= nr && nr < N && 0 <= nc && nc < N) 이 부분에서
		 *            nc의 범위를 M미만으로 해야하는데 다음과 같이 N까지로 함
		 * 해결방법 : N->M으로 수정 후 해결
		 * 
		 * 메모리: 25,056 kb
		 * 시간 : 137 ms
		 */
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] balloon = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					balloon[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 상 하 좌 우
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			
			int max = Integer.MIN_VALUE;
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					int sum = 0;
					for(int d=0; d<4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						// 경계값 기준 상하좌우의 값을 모두 합해준다.
						if(0 <= nr && nr < N && 0 <= nc && nc < M) {
							sum += balloon[nr][nc];
						}
					}
					// 최종적으로 기준값도 sum에 더해준다.
					sum += balloon[r][c];
					
					// 최대값 가리기
					max = Math.max(max, sum);
				}
			}
			sb.append("#" + t + " " + max).append("\n");
		}
		System.out.println(sb);
	}
}
