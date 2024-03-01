import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16268_이규빈 {
	/*
	 *  Q.풍선팡2
	 *	# 풀이시간 : 15분
	 *	# 메인 접근법 : 사방탐색
	 */
	static int N, M, res, cnt, nr, nc;
	static int[][] arr;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// INPUT
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		
			arr = new int[N + 2][M + 2]; // 경계조건 회피를 위해, 2만큼 크게 만든다.
			for (int r = 1; r <= N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= M; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			// SOLVE
			res = 0; // 최대값 저장 변수
			
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					cnt = arr[r][c]; // 꽃가루 세는 변수
					for (int d = 0; d < 4; d++) {
						nr = r + dr[d];
						nc = c + dc[d];
						cnt += arr[nr][nc];
					}
					if (res < cnt)  res = cnt;
				}
			}
			
			// OUTPUT
			sb.append("#" + t + " " + res + "\n");
		}
		System.out.println(sb);
	}
}