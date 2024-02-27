package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2567_정다운 {
	public static void main(String[] args) throws Exception {
		// 100*100 크기의 0 평면
		// 색종이가 덮인 영역은 1

		// 1이 있는 영역의 4방탐색 후
		// 0이 있다면 그 수만큼 길이 +1

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 색종이 개수
		int T = Integer.parseInt(br.readLine());

		// 2차원 평면 배열
		// 평면 끝에 붙더라도 사방탐색 가능하도록 & 경계조건도 필요 없도록 평면의 상하좌우 길이 +1
		int[][] arr = new int[102][102]; 

		// 평면에 색종이 얹기 (10*10 크기)
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// 시작 행 좌표
			int sr = Integer.parseInt(st.nextToken());
			// 시작 열 좌표
			int sc = Integer.parseInt(st.nextToken());

			// 색종이 면적에 1 채우기
			for (int r = sr; r < sr + 10; r++) {
				for (int c = sc; c < sc + 10; c++) {
					arr[r + 1][c + 1] = 1;
				}
			}
		}

		// 1이 쓰여진 칸의 사방탐색
		int[] drArr = { -1, 1, 0, 0 }; // 상 하 좌 우
		int[] dcArr = { 0, 0, -1, 1 };

		// 길이
		int p = 0;

		// 색종이는 (1,1) ~ (100, 100) 까지
		for (int r = 1; r < 101; r++) {
			for (int c = 1; c < 101; c++) {
				// 1일때
				if (arr[r][c] == 1) {
					// 사방탐색
					for (int d = 0; d < 4; d++) {
						int nr = r + drArr[d];
						int nc = c + dcArr[d];

						if (arr[nr][nc] == 0) {
							p++;
						}

					}
				}

			}
		}

		System.out.println(p);

	}
}
