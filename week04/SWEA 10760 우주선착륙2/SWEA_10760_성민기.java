package another;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760 {
	public static void main(String[] args) throws IOException {
		
		/*
		 * 풀이시간 : 240227 00:11 ~ 00:36
		 * 메인접근법 
		 *     - 델타배열을 사용해 인덱스마다 조건에 충족하는지 탐색
		 *     - 카운트값이 4이상이면 결과값인 result를 +1해준다.
		 *     
		 * 막힌 부분
		 *     - 처음에는 1~N-1, 1~M-1까지만 반복해서 해도 해결될 줄 알았음
		 *     - 델타배열을 구현하는 것보다 인덱스만 따져서 팔방탐색해도 괜찮을 줄 알았음
		 * 
		 * 해결방법
		 *     - 모든 인덱스를 탐색해야 하는 것을 확인
		 *     - 델타배열을 추가 공부 후 적용
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] star = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					star[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 상 하 좌 우 좌상 우상 좌하 우하
			int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
			int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
			
			int result = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					// 카운트값 초기화
					int count = 0;
					for(int d=0; d<8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						// 경계값 조건
						if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
							// 기준보다 낮은 곳 체크-> count++
							if(star[r][c] > star[nr][nc]) count++;
						}
					}
					// 4이상이라면 result++;
					if(count >= 4) result++;
				}
			}
			System.out.println("#" + t + " " + result);
		}
		
	
	}
}
