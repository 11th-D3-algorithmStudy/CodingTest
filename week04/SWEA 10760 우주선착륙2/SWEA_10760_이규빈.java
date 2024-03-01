import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760_이규빈 {
	/*
	 *  Q.우주선착륙2
	 *	# 문제 요약
	 *		팔방탐색 -> 8방향 중 착륙점보다 높이가 낮은 지점이 4방향 이상인 지점을 찾아라.
	 *	# 주의
	 *		가장자리(= 옆 지역이 높이 정보가 없는 지역)도 조건만 만족하면 포함한다.
	 *    -> 높이 정보가 없는 지역을 탐색하려는 경우 continue로 해결
	 *	# 풀이시간 : 30분
	 *	# 메인 접근법 : 팔방탐색
	 */
	
	// 델타 배열 - 상/하/좌/우/좌상/좌하/우상/우하
	static int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int[] dc = {0, 0, -1, 1, -1, -1, 1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][M];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			// ------------------- Input End -----------------------
			
			// 배열 순회
			int res = 0; // 후보지 개수
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					int cnt = 0;
					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr == -1 || nr == N || nc == -1 || nc == M) { 
							continue; // 탐색지점이 범위를 벗어나는 경우, 다른 방향 탐색으로 넘어감
						}
						if (arr[nr][nc] < arr[r][c])  cnt++;
					}
					// 특정 지점에서 팔방탐색 종료시, 조건을 만족하면 후보지 개수 +1
					if (cnt >= 4) res++;
				}
			} // 순회 종료
			
			// 결과 출력
			System.out.println("#" + t + " " + res);
		} // 테스트케이스 종료
	}
}