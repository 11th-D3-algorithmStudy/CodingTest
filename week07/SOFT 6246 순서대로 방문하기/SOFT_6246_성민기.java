package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class SOFT_6246_성민기 {
	
	/* 풀이시간 : 240316 15:50 ~ 17:30 
	 * 메인접근법 
	 *      - DFS와 백트래킹을 활용
	 *      - 문제 조건을 위해 델타배열을 사용
	 *      - 깊이 레벨과 반드시 거쳐야 하는 곳의 체크를 활용
	 *      
	 * 막힌부분 
	 *      1. 스택오버플로우 -> DFS 조건에서 visited[nr][nr]로 해버림
	 *         -> nr을 nc로 수정하여 해결
	 *      2. 거쳐야하는 지점의 인덱스 때문에 인덱스 에러 발생
	 *         -> 입력값을 -1씩 해줘서 해결
	 *      3. 모든 문제 해결 후에도 다시 인덱스 에러 발생
	 *         -> DFS 내에서 locS의 위치도 백트래킹을 해줘야 한다는 것을 확인
	 *         -> locS--; 를 통해 백트래킹으로 해결
	 *      
	 * 메모리 : 10.33 MB
	 * 시간 : 70 ms
	 */
	
	static boolean[][] visited; // 방문처리
	static int[][] map;         // 맵
	static int[][] location;    // 꼭 거쳐야 하는 지점
	static int N, M;            // 입력값
	static int count = 0;       // 도착 시나리오 수를 카운트
	static int locS = 1;        // location의 위치
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		// 입력값 및 배열 크기 설정
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][N];
		map = new int[N][N];
		location = new int[M][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) {
				location[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}	
		
		DFS(location[0][0], location[0][1], 1);
		System.out.println(count);
	}
	
	public static void DFS(int r, int c, int level) {
		// 기저조건
		if(level==M) {
			count++;
			return;
		}
		
		// 방문처리
		visited[r][c] = true;
		
		// 상 하 좌 우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위 벗어나지 않으면서 1(벽)이 아니고 방문처리가 되지 않은 곳일 때
			if(nr >= 0 && nr < N && nc >= 0 && nc < N
					&& !visited[nr][nc]
					&& map[nr][nc] != 1) {
				// nr, nc가 거쳐야하는 지점과 같다면
				if(nr==location[locS][0] && nc==location[locS][1]) {
					locS++;
					// 방문처리
					visited[nr][nc] = true;
					
					// 깊이 레벨++
					DFS(nr, nc, level+1);
					
					// 방문처리 백트래킹
					visited[nr][nc] = false;
					
					// 이거 안해줘서 계속 ArrayIndexOutOfBoundsException 발생했음
					// 인덱스를 백트래킹 할 생각은 못했네
					locS--; // 백트래킹
				} else {
					visited[nr][nc] = true;
					
					// 꼭 거쳐야하는 곳을 지나지 않았으므로 level은 가만히 둠
					DFS(nr, nc, level);
					visited[nr][nc] = false;
				}
			}
		}
		// 여기서 해도 백트래킹 되나봄
		// 해당 위치 방문 후 백트래킹
//		visited[r][c] = false;
	}
}
