package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 항체인식 {
	
	static int N, M; 
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static int[][] oriArr, vacArr; // 원본, 백신 배열
	static int R, C, oriNum, vacNum; // 달라진 칸 좌표, 숫자
	static boolean flag; // 달라진 칸 있는지 없는지 확인 flag
	static boolean[][] visit; // 방문처리 필요?
	
	public static void main(String[] args) throws Exception {
		// 원본배열과 촬영결과 비교해서 달라진칸 일단 하나 찾아서 dfs
		// 만약 달라진 칸이 없다? 바로 YES 출력
		// 달라진 칸 1개에서 백신 투여 시작 (원본배열 사용)
		// 투여하고 나서 촬영 결과와 비교해서 일치하면 YES, 아니면 NO
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		oriArr = new int[N][M];
		vacArr = new int[N][M];
		visit = new boolean[N][M];
		
		for (int r=0; r<N; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				oriArr[r][c] = Integer.parseInt(st2.nextToken());
			}
		}
		
		for (int r=0; r<N; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				vacArr[r][c] = Integer.parseInt(st2.nextToken());
			}
		}
		
		label:
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (oriArr[r][c] != vacArr[r][c]) {
					R = r;
					C = c;
					oriNum = oriArr[r][c];
					vacNum = vacArr[r][c];
					flag = true;
					break label;
				}
			}
		}
		
		label2:
		if (!flag) { // 달라진칸 없으면 바로 YES
			System.out.println("YES");
		} else {
			dfs(R, C);
			// 백신처리한 oriArr와 vacArr가 같은지 확인!
			for (int r=0; r<N; r++) {
				for (int c=0; c<M; c++) {
					if (oriArr[r][c] != vacArr[r][c]) { // 만약 하나라도 다른게 나오면 바로 NO
						System.out.println("NO");
						break label2;
					}
				}
			}
			// 무사히 for문 탈출시 YES
			System.out.println("YES");
			
		}

	}

	static void dfs(int r, int c) {
		// 갈수있는 모든 곳 방문했으면 리턴
		if (visit[r][c]) {
			return;
		}
		visit[r][c] = true; // 방문처리
		oriArr[r][c] = vacNum; // 백신 항체 생성
		
		// 상하좌우로 이동 (같은 번호일때만)
		for (int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && // 경계조건
				oriArr[nr][nc]==oriNum	) {
				dfs(nr, nc);
			}
		}
		
		
	}
}
