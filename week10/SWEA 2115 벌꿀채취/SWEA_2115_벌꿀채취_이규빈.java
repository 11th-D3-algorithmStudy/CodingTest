package week10;

import java.io.*;
import java.util.*;

public class SWEA_2115_벌꿀채취_이규빈 {
	static int N, M, C, res;
	static int[][] map;
	static boolean[][] selected;
	static int cost1, cost2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			selected = new boolean[N][N];
			res = 0; // 최대 수익 저장
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			select();
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	// 벌통영역 2개 선택
	private static void select() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				for (int k = 0; k < M; k++) {
					selected[i][j + k] = true;
				}
				
				// 첫번째 영역을 정한 후, 두번째 영역을 정해야 한다.
				// - 따라서 4중 for문 사용
				for (int a = 0; a < N; a++) {
					next :
					for (int b = 0; b <= N - M; b++) {
						for (int c = 0; c < M; c++) {
							if(selected[a][b + c])  continue next;
						}
						
						// 이 구역에 들어오면 두 영역 모두 선택 완료
						// - 선택한 좌표를 넘겨주기
						check(i, j, a, b, 0);
                        res = Math.max(res, cost1 + cost2);
                        
                        cost1 = 0;
                        cost2 = 0;
					}
				}
				
				// 첫번째 영역 방문처리 원상복구
				for (int k = 0; k < M; k++) {
					selected[k][j + k] = false;
				}
			}
		}
	}
	
	// 넘겨받은 좌표를 이용해, 비트마스킹을 이용한 조합으로 최대수익을 찾기
	private static void check(int i, int j, int a, int b, int depth) {
		for (int bit = 0; bit < (1 << M); bit++) {
			int cnt1 = 0, cnt2 = 0; // 꿀 양 합산 -> C 이하인지 판단
			int tmp1 = 0, tmp2 = 0; // 각 칸의 제곱의 합 임시저장 -> 최댓값 갱신되는지 판단
			
			for (int k = 0; k < M; k++) {
				if ((bit & (1 << k)) > 0) {
					cnt1 += map[i][j + k];
					tmp1 += (int) Math.pow(map[i][j + k], 2);
					
                    cnt2 += map[a][b + k];
                    tmp2 += (int) Math.pow(map[a][b + k], 2);
				}
			}
			
			// 각 벌통의 꿀 양 합이 C를 넘지 않는 선에서 최댓값이 저장됨
			// - 따라서 이 두 값을 더하면 원하는 값을 얻을 수 있음
			if (cnt1 <= C)  cost1 = Math.max(cost1, tmp1);
            if (cnt2 <= C)  cost2 = Math.max(cost2, tmp2);
		}
	}

}