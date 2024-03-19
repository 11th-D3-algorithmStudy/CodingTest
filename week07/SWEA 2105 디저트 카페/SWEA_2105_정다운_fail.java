package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2105_정다운_fail {
	
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1}; // 우하 좌하 좌상 우상
	static int N; // 지역 한 변의 길이
	static int[][] arr; // 지역 배열
	static boolean[][] visit;
	static List<Integer> list; // 먹은 디저트 종류 저장 리스트
	static int max = 0;
	static int R, C; // 사각형 초기 좌표
	static int[] dArr = new int[4]; // 델타배열 사용 횟수(각 변의 길이) 배열
	
	public static void main(String[] args) throws Exception {
		
		// 실패 ~~ ㅠ
		// 방향전환을 잘못해주고 있는것 같다
		// https://velog.io/@dhsdb02/SWEA-%EB%94%94%EC%A0%80%ED%8A%B8-%EC%B9%B4%ED%8E%98
		// 참고해서 다시 풀어보기
		
		
		
		
		
		// 대각선 4방 델타배열..... 선언한 순서대로 진행하면 
		// 0, N-1 열 & N-1행 에서 시작시 대각선 사각형 생성 불가
		
		// 먹은 디저트 종류는 list에 보관해서 contains로 중복확인
		
		// dfs의 시작위치? -> for문 돌면서 모든 위치에서 dfs?
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visit = new boolean[N][N];
			list = new ArrayList<>();
			for (int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c=0; c<N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1 ~ N-2 열 & 0 ~ N-2 행 까지 탐색하면서 dfs 고고
			for (int r=0; r<N-1; r++) {
				for (int c=1; c<N-1; c++) {
					R = r;
					C = c; // 초기좌표 저장
					dfs(r, c, 0);
				}
			}
			
			sb.append("#"+t+" "+max+"\n");
		}
		
		System.out.println(sb);
		
	}

	// r, c : 현재위치 / x : 델타배열 방향
	static void dfs(int r, int c, int x) {
		// 초기좌표로 돌아오면 & 델타배열의 마지막 방향까지 갔으면 
		// 리스트에 들어있는 디저트 종류 확인하고 max값 업데이트 후 리턴
		// + 리스트 초기화
		if (r == R && c == C && x == 3) {
			max = Math.max(max, list.size());
			list.clear();
			return;
		}
		visit[r][c] = true; // 방문처리
		list.add(arr[r][c]); // 디저트 종류 저장 ~  
		
		// 지금 방향으로 고고
		int nr = r+dr[x];
		int nc = c+dc[x];
		
		if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			if (!visit[nr][nc] && !list.contains(arr[nr][nc])) { // 아직 안먹은 종류의 디저트?
				// 지금 방향으로 가자~~~
				dArr[x]++; // 지금 방향의 길이 1 늘려주기
				dfs(nr, nc, x);
				visit[nr][nc] = false; // 나오면 방문처리 초기화
			} else {
				if (dArr[x] > 0) { // 지금 진행방향으로 적어도 한번은 왔었으면 방향전환 고고
					nr = nr-dr[x]+dr[x+1];
					nc = nc-dc[x]+dc[x+1]; // 방향전환~
					if (!visit[nr][nc] && !list.contains(arr[nr][nc])) {
						dArr[x+1]++;
						dfs(nr, nc, x+1);
						visit[nr][nc] = false;
					}
				}
			}
		} 
		
		
	}
}
