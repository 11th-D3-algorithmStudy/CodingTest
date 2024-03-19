package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5650_핀볼게임_이규빈_FAIL {
	/*
	* 3시간 넘게 고민했지만 실패.. (혹시 제 코드 잘못된 부분 알겠으면 지적해주세요)
	*/
	static int N;
	static int[][] map;
	static Wormhole[] holes; // 웜홀 좌표 저장
	static int startR, startC, dir, bump;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int[][] dirChange= { null, // 0번 사용하지 않음
			{1, 3, 0, 2}, // 공이 1번 블록에 부딪힌 경우 바뀌는 공의 방향 (델타배열 인덱스 기준)
			{3, 0, 1, 2},
			{2, 0, 3, 1},
			{1, 2, 3, 0},
			{1, 0, 3, 2}
	};
	
	static class Wormhole {
		int r, c;
		int otherR, otherC; // 연결되는 웜홀의 좌표
		
		Wormhole(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Ball {
		int r, c;
		int dir; // 진행 방향
		
		Ball(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			map = new int[N][N];
			holes = new Wormhole[11]; // 0~5번은 사용하지 않음
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int c = 0; c < N; c++) {
					int data = Integer.parseInt(st.nextToken());
					map[r][c] = data;
					
					if (6 <= data && data <= 10) {
						// 아직 방문하지 않은 웜홀 번호
						if (holes[data] == null) {
							holes[data] = new Wormhole(r, c);
						}
						// 방문했던 웜홀 번호 - 기존에 저장했던 웜홀과 연결
						else {
							holes[data].otherR = r;
							holes[data].otherC = c;
						}
					}
				}
			}
			
			// ----------------- INPUT END -------------------
			
			int res = Integer.MIN_VALUE;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int dir = 0; dir < 4; dir++) {
						if (map[r][c] == 0) {
							// 출발 위치, 충돌횟수 초기화
							startR = r;
							startC = c;
							bump = 0;
							
							solve(r, c, dir);
							
							res = Math.max(res, bump);
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + res);
		} // End of test-case
	} // End of main

	private static void solve(int r, int c, int dir) {
		Ball ball = new Ball(r, c, dir);
		
		while(true) {
			ball.r += dr[ball.dir];
			ball.c += dc[ball.dir];
//			// 틀린 코드
//			int nr = r + dr[ball.dir];
//			int nc = c + dr[ball.dir];
			
			// 벽 = map 나간 경우
			// - 네모 블록(5번) 만난 경우와 같이 정반대로 방향 변경
			if (!inMap(ball.r, ball.c)) {
				bump++;
				ball.dir = dirChange[5][dir];
				continue;
			}
			
			int data = map[ball.r][ball.c];
			
			// 종료조건 - 시작점에 돌아왔거나 블랙홀 만나면 게임 종료
			if ((ball.r == startR && ball.c == startC) || data == -1)  break;
			
			
			// 세모,네모 블록
			if (1 <= data && data <= 5) {
				bump++;
				ball.dir = dirChange[data][dir]; // 방향 변경
			}
			// 웜홀 - 같은 번호의 웜홀로 위치변경
			else if (6 <= data && data <= 10) {
				if (ball.r == holes[data].r && ball.c == holes[data].c) {
					ball.r = holes[data].otherR;
					ball.c = holes[data].otherC;
				}
				else {
					ball.r = holes[data].r;
					ball.c = holes[data].c;
				}
			}
		}
	}

	private static boolean inMap(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
