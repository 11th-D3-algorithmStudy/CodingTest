package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class SWEA_2115_성민기 {
	
	/* 풀이시간 : 240410 
	 * 메인접근법 : 도저히 접근하지 못하겠어서 정답코드를 보고 분석함
	 * 막힌 부분 : 부분집합 + 조합이라는 것에 대한 생각을 하지 못했었음(복습 필요)
	 * 		    추가 내용은 주석으로 작성	
	 */
	
	static int N, M, C;
	static int[][] map;
	static int maxNum = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + t + " " + combination());
		}
	}
	
	public static int combination() {
		int result = 0;
		int max1 = 0; // 일꾼1의 최대 이익
		int max2 = 0; // 일꾼2의 최대 이익
		for(int i=0; i<N; i++) {
			for(int j=0; j<=N-M; j++) { // N-M만큼 돌리는 것으로 조합을 실행
				maxNum = 0; // 최대 이익 초기화
				getMaxHoney(i, j, 0, 0, 0);
				// 구한 maxNum으로 일꾼1의 최대치로 넣어준다
				max1 = maxNum;
				
				// 일꾼2의 최대치를 구하기 위해서 0으로 초기화
				maxNum = 0;
				max2 =0;
				
				// 일꾼1이 선택한 것 다음부터 행한다.
				for(int j2 = j+M; j2<=N-M; j2++) {
					getMaxHoney(i, j2, 0, 0, 0);
					max2 = Math.max(max2, maxNum);
				}
				
				// 일꾼1이 선택한 다음 행부터 순환하며 체크
				for(int i2 = i+1; i2 < N; i2++) {
					// 조합
					for(int j2 = 0; j2 <= N-M; j2++) {
						getMaxHoney(i2, j2, 0, 0, 0);
						max2 = Math.max(max2, maxNum);
					}
				}
				// 일꾼1이 벌통을 새로 선택했을 때마다 전체 이익 최댓값으로 갱신
				result = Math.max(result, max1+max2);
			}
		}
		return result;
	}
	
	// 현재 좌표 i j, 선택한 벌통 수, 꿀의 양, 이익
	public static void getMaxHoney(int i, int j, int cnt, int sum, int benefit) {
		// 채취한 꿀의 양이 C를 넘었으면 return
		if(sum > C) return;
		
		// M개 선택했다면
		if(cnt==M) {
			// 최대 이익 갱신
			if(maxNum < benefit) maxNum = benefit;
			return;
		}
		
		// 선택한 경우
		getMaxHoney(i, j+1, cnt+1, sum+map[i][j], benefit + map[i][j] * map[i][j]);
	
		// 비선택
		getMaxHoney(i, j+1, cnt+1, sum, benefit);
	}
}
