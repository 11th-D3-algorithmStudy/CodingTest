package week13;

import java.io.*;
import java.util.*;

public class BOJ_17144_미세먼지_이규빈 {
	/*
	 * # 풀이시간 : 2시간
	 * # 메모리/실행시간 : 38528 KB / 288 ms
	 * # 막힌 부분
	 *   미세먼지 확산 과정에서 "특정 시간에 새로 생긴 칸"이 해당 시간에 확산되는 문제 발생
	 *   -> 임시 맵(tmpMap)을 만들어 해결
	 */
	static int R, C, T;
	static int topR, bottomR;  // 각각 청정기의 위쪽 r좌표, 아래쪽 r좌표
	static int[][] map, tmpMap;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		tmpMap = new int[R+1][C+1];
		topR = 0;
		bottomR = 0;
		
		for (int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				// 공기청정기 좌표 찾기
				if (topR == 0 && map[r][c] == -1) {
					topR = r;
					bottomR = r + 1;
				}
			}
		}
		
		// ----- 입력 끝 -----
		
		for (int t = 1; t <= T; t++) {
			// 1. 미세먼지 확산
			for (int r = 1; r <= R; r++) {  
				tmpMap[r] = map[r].clone();  // 깊은 복사
			}
			
			for (int r = 1; r <= R; r++) {
				for (int c = 1; c <= C; c++) {
					if (map[r][c] >= 5) {  // 미세먼지 양이 5 이상일 때만 확산됨
						diffusion(r, c);
					}
				}
			}
			
			for (int r = 1; r <= R; r++) {  
				map[r] = tmpMap[r].clone();  // 확산 결과 복사하기
			}
			
//			// 테스트
//			for (int r = 1; r <= R; r++) {
//				System.out.println(Arrays.toString(map[r]));			
//			}
//			System.out.println();
			
			
			// 2. 청정기 작동
			clean();
		}
		
//		// 테스트
//		for (int r = 1; r <= R; r++) {
//			System.out.println(Arrays.toString(map[r]));			
//		}
		
		// 이 구역 들어오면 T초 경과
		
		int res = 0;
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (map[r][c] > 0) {
					res += map[r][c];
				}
			}
		}
		
		System.out.println(res);
	} // 메인 메서드 종료

	
	// [주의] "값 참조"할 때는 map, "확산과정 기록"할 때는 tmpMap을 사용하기!
	static void diffusion(int r, int c) {
		int amount = map[r][c] / 5;  // 주변 1칸당 확산되는 양
		int cnt = 0;                 // 확산된 방향의 개수 세기
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (!inMap(nr, nc) || map[nr][nc] == -1)  continue;
			
			// 이 구역 들어오면 확산 가능
			tmpMap[nr][nc] += amount;
			cnt++;
		} // 확산 끝
		
		// 남은 양 계산 - 확산된 횟수만큼 뺴주기
		tmpMap[r][c] -= amount * cnt;
	}

	
	// "청정기와 가까운 칸"부터 갱신해야, 중복해서 덮어쓰는 상황을 막을 수 있다.
	static void clean() {
		// (a) 1열 - 청정기 쪽으로 1행씩 이동
		for (int r = topR - 2; r >= 1; r--) {     // 청정기 윗 부분
			map[r + 1][1] = map[r][1];
		}
		for (int r = bottomR + 2; r <= R; r++) {  // 청정기 아랫 부분
			map[r - 1][1] = map[r][1];
		}
		
		// (b) 1행, R행 - 왼쪽으로 1열씩 이동
		for (int c = 2; c <= C; c++) {
			map[1][c - 1] = map[1][c];
			map[R][c - 1] = map[R][c];
		}
		
		// (c) C열 - 청정기와 먼 쪽으로 1행씩 이동
		for (int r = 2; r <= topR; r++) {         // 청정기 윗 부분
			map[r - 1][C] = map[r][C];
		}
		for (int r = R - 1; r >= bottomR; r--) {  // 청정기 아랫 부분
			map[r + 1][C] = map[r][C];
		}
		
		// (d) 청정기와 같은 행 - 오른쪽으로 1열씩 이동
		for (int c = C - 1; c >= 2; c--) {
			map[topR][c + 1] = map[topR][c];
			map[bottomR][c + 1] = map[bottomR][c];
		}
		
		// [추가] 청정기의 바로 오른쪽 좌표의 값을 갱신하기
		map[topR][2] = 0;
		map[bottomR][2] = 0;
	}
	
	
	static boolean inMap(int r, int c) {
		return 1 <= r && r <= R && 1 <= c && c <= C;
	}
}
