package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760_조아름 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N][M];

			for (int i = 0; i < N; i++) {// 배열에 삽입
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 }; // 8방 탐색
			int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

			
			int Fcount = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					
					int count = 0; // 위치 변경 --> 초기화를 위해 for문 내부에 설정
					
					for (int l = 0; l < 8; l++) {
						int x = i + dx[l];
						int y = j + dy[l];
						

						if(x>=0 && y>=0 && x<N && y<M) { // 조건 설정 실수
							if(arr[x][y] < arr[i][j]) {
								count++;
							}
						}
					}
					
					if(count>=4) // 위치 변경
						Fcount++;
				}
			}
			
			System.out.println("#" + (c + 1) + " " + Fcount);

		}
	}
}
