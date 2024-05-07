package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16234_성민기 {
	
	/* 풀이시간 : 240419 20:30 ~ 22:00 + 240420 19:00 ~ 20:00
	 * 메인접근법 : 
	 *      - BFS 활용 + 조건 추가
	 *      - 인구 이동일 때의 메소드 등을 추가로 만듦
	 *      - 무한 반복을 통해서 인구 이동을 구현함(안에서 매번 방문배열 초기화)
	 *      - flag를 세워서 인구 이동이 없을 때 종료시킴
	 *      
	 * 막힌 부분 :
	 *      - 진짜 하루 종일해도 모르겠어서(기존 코드는 메모리초과 뜸) 결국 정답코드 봤음
	 * 
	 * 메모리 : 297308 KB / 시간 : 560ms 
	 */
	
	public static class Node{
		int x;
		int y;
		
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int N, L, R;
	static List<Node> list;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료
		
		// 인구 이동!!
		int result = move();
		System.out.println(result);
	}
	
	public static int BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<>();
		// 연합의 수를 구하기 위한 LIST
		list = new ArrayList<>();
		
		queue.add(new Node(r, c));
		list.add(new Node(r, c));
		visited[r][c] = true;
		
		// 인구수 총합을 구하기 위해 int형 정수 선언
		int sum = map[r][c];
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			// 델타배열 움직임
			for(int d=0; d<4; d++) {
				int nr = curr.x + dr[d];
				int nc = curr.y + dc[d];
				
				// 경계값 조건
				if(nr >= 0 && nr < N && nc >= 0 && nc < N
						&& !visited[nr][nc]) {
					// 인구 차이 계산
					int gap = Math.abs(map[curr.x][curr.y] - map[nr][nc]);
					if(L <= gap && gap <= R) {
						visited[nr][nc] = true;
						queue.add(new Node(nr, nc));
						// 연합의 위치값들 list에 삽입
						list.add(new Node(nr, nc));
						// 인구수도 구한다.
						sum += map[nr][nc];
					}
				}
			}
		}
		// 계산 끝나면 인구수 반환
		return sum;
	}
	
	public static int move() {
		int count = 0; // 인구이동 카운트
		
		// 무한 반복(끝날때까지)
		while(true) {
			// 반복 끝내기 위한 flag 사용
			boolean flag = false;
			
			// 반복마다 방문배열 새롭게 활용
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						int sum = BFS(i, j);
						if(list.size() > 1) {
							movingPerson(sum);
							flag = true;
						}
					}
				}
			}
			// 배열의 모든 값 살펴본 후 이동이 없었으면 while문 종료
			if(!flag) break;
			
			// 아니면 인구이동 수 카운트 증가
			count++;
		}
		return count;
	}
	
	// 인구 이동 후 인구 수 계산
	// (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
	public static void movingPerson(int sum) {
		int avg = sum / list.size();
		for(Node curr : list) {
			map[curr.x][curr.y] = avg;
		}
	}
}
