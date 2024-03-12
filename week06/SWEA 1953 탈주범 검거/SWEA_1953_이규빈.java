package week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_이규빈 {
	/*
	* Q. 탈주범 검거
	* # 풀이시간 : 2시간
	* # 메모리 / 실행시간 : 25872kb / 139ms
	* # 문제 해석
	*  - 탈주범은 시간당 1을 움직인다 = 있을 수 있는 위치가 점점 퍼짐 -> BFS
	*  - 터널 구조물 타입이 제각각 -> 각 방향별로 진행 가능한 구조물일 경우만 진행하도록 조건을 분기한다.
	*  - 탈주범이 위치할 수 있는 장소의 개수를 계산하라. -> 방문 처리해서 방문한 장소 개수 계산
	* # 막힌부분 해결
	*  - bfs 안에서 for문을 q.size()만큼 돌리니 결과 안맞음 
	*    -> 큐 크기가 중간에 변하기 때문임을 알고, 미리 변수에 저장해 사용
	*  - 테케 5번부터 시간초과 
	*    -> 갔던 곳을 또 갈 수 있으므로 bfs에서 !visited 조건이 필요 없다고 생각했었는데, 
	*       이러면 이미 방문한 곳을 빙빙 도는 경우가 생겨 시간초과가 발생하는거 같음
	*    -> !visited 조건을 추가해 시간초과 해결
	*  - 50개 중 5개 오답
	*    -> isPossible 메서드에서 숫자 하나 오타..ㅠ 너무 무식하게 만들어서 더 찾기 힘들었던거 같다.
	*    -> 배열 활용해 깔끔하게 정리해서 다시 풀어보자.
	*/
	
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Info> q;
	
	// 델타배열 - 상하좌우 순서
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// 해당 지점의 정보를 저장하는 클래스
	static class Info {
		int r, c;
		int type; // 터널 구조물 타입
		
		Info(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// ------------------ INPUT END ----------------------
			
			q = new LinkedList<>();
			
			// 입력받은 맨홀지점부터 bfs 시작
			bfs(R, C, map[R][C]);
			
			int res = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (visited[r][c]) res++;
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	private static void bfs(int r, int c, int type) {
		visited[r][c] = true;
		q.offer(new Info(r, c, type));
		
		// L시간 동안 bfs 진행
		for (int i = 1; i < L; i++) { // 1시간쨰는 맨홀지점 한칸만 위치 가능하므로, 1부터 시작
			int size = q.size(); // [막힌 부분 해결] 중간에 큐 크기 변하므로, 변수에 미리 저장
			for (int j = 0; j < size; j++) {
				Info cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					// 진행 조건 = 경계조건 만족 && 방문X && 0 아님 && 구조물 타입 부합
					if (inMap(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0 && isPossible(d, map[cur.r][cur.c], map[nr][nc])) {
						visited[nr][nc] = true;
						q.offer(new Info(nr, nc, map[nr][nc]));
					}
				}
			}
		}
	}

	private static boolean inMap(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < M;
	}
	
	/**
	 * 현재 지점에서 다음 지점으로 진행할 수 있는지 판단하는 메서드
	 * @param d : 델타배열의 인덱스
	 * @param cur : 현재 지점의 구조물 타입
	 * @param nxt : 다음 지점의 구조물 타입
	 * @return : 예컨대 현재 지점의 타입이 4이고(cur == 4), 위쪽 방향으로 갈지 판단하는 중인데(d == 0),  
	 *          다음 지점의 타입이 6인 경우(nxt == 6), 진행 가능하므로 true를 반환
	 */
	private static boolean isPossible(int d, int cur, int nxt) {
		boolean flag = false;
		
		switch(cur) {
		case 1: {
			if (d == 0 && (nxt == 1 || nxt == 2 || nxt == 5 || nxt == 6)) flag = true;
			if (d == 1 && (nxt == 1 || nxt == 2 || nxt == 4 || nxt == 7)) flag = true;
			if (d == 2 && (nxt == 1 || nxt == 3 || nxt == 4 || nxt == 5)) flag = true;
			if (d == 3 && (nxt == 1 || nxt == 3 || nxt == 6 || nxt == 7)) flag = true;
			break;
		}
		case 2: {
			if (d == 0 && (nxt == 1 || nxt == 2 || nxt == 5 || nxt == 6)) flag = true;
			if (d == 1 && (nxt == 1 || nxt == 2 || nxt == 4 || nxt == 7)) flag = true;
			break;
		}
		case 3: {
			if (d == 2 && (nxt == 1 || nxt == 3 || nxt == 4 || nxt == 5)) flag = true;
			if (d == 3 && (nxt == 1 || nxt == 3 || nxt == 6 || nxt == 7)) flag = true;
			break;
		}
		case 4: {
			if (d == 0 && (nxt == 1 || nxt == 2 || nxt == 5 || nxt == 6)) flag = true;
			if (d == 3 && (nxt == 1 || nxt == 3 || nxt == 6 || nxt == 7)) flag = true;
			break;
		}
		case 5: {
			if (d == 1 && (nxt == 1 || nxt == 2 || nxt == 4 || nxt == 7)) flag = true;
			if (d == 3 && (nxt == 1 || nxt == 3 || nxt == 6 || nxt == 7)) flag = true;
			break;
		}
		case 6: {
			if (d == 1 && (nxt == 1 || nxt == 2 || nxt == 4 || nxt == 7)) flag = true;
			if (d == 2 && (nxt == 1 || nxt == 3 || nxt == 4 || nxt == 5)) flag = true;
			break;
		}
		case 7: {
			if (d == 0 && (nxt == 1 || nxt == 2 || nxt == 5 || nxt == 6)) flag = true;
			if (d == 2 && (nxt == 1 || nxt == 3 || nxt == 4 || nxt == 5)) flag = true;
			break;
		}
		}
		return flag;
	}
}
