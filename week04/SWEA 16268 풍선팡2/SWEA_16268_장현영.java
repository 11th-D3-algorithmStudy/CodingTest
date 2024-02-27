package week04;

import java.io.*;
import java.util.*;

public class SWEA_16268_장현영 {
		// 4방탐색
		// 본인 포함 4방(최대 5개) 덧셈만 해주기
		// 풀이시간 10분

		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			int T = Integer.parseInt(br.readLine());
			for(int t=1; t<=T; t++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int[][] arr = new int[n][m];
				for(int i=0; i<n;i++) {
					st = new StringTokenizer(br.readLine());
					for(int j=0;j<m;j++) {
						arr[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				int[] dr = {0,0,1,-1};
				int[] dc = {1,-1,0,0};
				int maxCnt= 0;
				int cnt;
				for(int i=0; i<n;i++) {
					for(int j=0;j<m;j++) {
						cnt = arr[i][j]; // 자기 자신 값으로 cnt 초기화
						for(int idx=0; idx<4;idx++) {
							int ni = i + dr[idx];
							int nj = j + dc[idx];
							if (!((ni<n && 0<=ni && nj<m && 0<=nj))) { // 범위를 벗어난 경우
								continue;
							}
							// 범위 내에 있는 상태
							cnt += arr[ni][nj];
						}
						maxCnt = Math.max(maxCnt, cnt);
					}
				}
				System.out.println("#"+t+" "+maxCnt);
			}
		}
}
