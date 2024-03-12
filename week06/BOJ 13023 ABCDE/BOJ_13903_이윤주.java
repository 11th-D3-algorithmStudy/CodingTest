package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13903_이윤주 {
	/*
	 * Q.출근 
	 * #문제요약 
	 * 	첫번째 row의 세로블록에서 시작해서 특정 규칙으로 세로블록만 밟고 
	 * 	마지막 row까지 갈 수 있는지 가능 여부, 최소 몇걸음이 걸리는지 출력하라
	 * #풀이시간 : 4시간...
	 * #메모리/시간 : 112424kb/672ms
	 * #메인접근법 
	 * 	1. 마지막 row까지 최소 걸음 -> 최단 거리 찾기 -> BFS
	 * 	2. 메모리 초과....-> 방문처리 꼭 할것!!!!
	 */
	static int[][] road; // 보도블럭
	static boolean[][] visited; //방문처리
	static int R, C; // 세로, 가로
	static int N; // 규칙의 개수
	static int[] dr, dc; // N개의 규칙 r, c
	static boolean possible;// 출근가능한지 표시하는 flag
	static int min; // 최소 걸음
	static int count; // 걸음수 카운트
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		road = new int[R][C];
		possible = false;
		min = Integer.MAX_VALUE;

		// 보도블럭 입력
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 규칙개수
		N = Integer.parseInt(br.readLine());

		dr = new int[N];
		dc = new int[N];

		// 규칙입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dr[i] = Integer.parseInt(st.nextToken());
			dc[i] = Integer.parseInt(st.nextToken());
		}
		
		queue = new LinkedList<>();
		// 첫줄의 세로블럭 모두 확인 -> 큐에 넣기
		for (int c = 0; c < C; c++) {
			if (road[0][c] == 1) { //첫줄 블록 = 루트 노드
				queue.offer(new int[] {0, c});
			}
		}

		visited = new boolean[R][C];
		bfs();
		
		// 모두 확인하고 나서 가능여부에 따라 출력
		if (possible) {
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	}

	// row, col : 현재 row,col
	private static void bfs() {
		
		int size = 0, level = 0; 
		
		//큐가 비어있지 않은 동안
		while(!queue.isEmpty()) {
			//큐 사이즈 : 동일 레벨인 탐색대상 노드의 개수
			size = queue.size();
			
			while(size-- > 0) {
				//제일 첫번째 원소 뽑기
				int[] current = queue.poll(); //0 : r, 1 : c
				int r = current[0];
				int c = current[1];
				visited[r][c] = true; //방문처리
				
				//현재 노드에서 처리할 로직
				if(r == R - 1) { //마지막 열에 도착한 경우
					min = Math.min(min, level);
					possible = true;
				}
				
				//첫번째 원소에 연결된 모든 위치 큐에 넣기
				for(int i = 0; i < N; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					//좌표가 범위 밖이면 스킵
					if(nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;
					
					//세로블록이면 자식 블록 큐에 넣기
					if(!visited[nr][nc] && road[nr][nc] == 1) {
						visited[nr][nc] = true; //방문처리!!!!!!!!!!!!
						queue.offer(new int[] {nr, nc});
					}
				}
			}
			level++; //지금 레벨에 있는 모든 원소 확인 -> 레벨 증가!!			
		}
	}
}
