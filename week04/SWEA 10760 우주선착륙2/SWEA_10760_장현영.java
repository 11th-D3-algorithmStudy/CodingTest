package week04;

import java.io.*;
import java.util.*;

public class SWEA_10760_장현영 {
	// 8방탐색
	// 착륙할 수 있는 곳 다 세기
	// 범위만 주의해서 코드 짜기
	// 풀이시간 20분
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
			int[] dr = {0,0,1,-1,1,1,-1,-1};
			int[] dc = {1,-1,0,0,1,-1,-1,1};
			int cnt;
			int answer= 0;
			for(int i=0; i<n;i++) {
				for(int j=0;j<m;j++) {
					cnt = 0;
					for(int idx=0; idx<8;idx++) {
						int ni = i + dr[idx];
						int nj = j + dc[idx];
						if (!((ni<n && 0<=ni && nj<m && 0<=nj))) { // 범위를 벗어난 경우
							continue;
						}
						if(arr[i][j] > arr[ni][nj]) // 조건에 해당하면 cnt++
							cnt++;
						if(cnt==4) { // 8방 탐색 중간 종료 조건(기준 충족)
							answer++;
							break;
						}
					}
				}
			}
			System.out.println("#"+t+" "+answer);
		}
	}
}
