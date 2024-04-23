package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_정다운 {
	
	static class Country {
		int r,c,p; // 좌표, 인구수

		public Country(int r, int c, int p) {
			this.r = r;
			this.c = c;
			this.p = p;
		}		
	}
	
	static int N, L, R;
	static int[][] map;
	static boolean[][] visit;
	
	static Queue<Country> queue;
	
	static int day = 0; // 결과
	static boolean flag;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		/* 
		 * 모든 칸에서 시작하는 bfs 진행
		 * 사방에 인접한 나라 중 L<= <=R 조건을 만족하는 나라 queue에 넣기
		 * queue에 들어간 나라 개수 & 인구 수 합 확인 필요
		 * 
		 * bfs 진행하면서 인구 분배를 동시에 진행.....할 수 없다
		 * 어디까지 국경이 열려있을지 모르니까 ㅠ~
		 * => 큐에 들어갔었던 노드를 모두 리스트에 넣고
		 * while문이 끝나면 리스트에 있는 노드 인구수 분배하자
		 * 
		 * bfs를 NxN 까지 모두 마치면 하루 끝
		 * 
		 * ++ 인구이동이 끝나는 조건? 
		 * 모든 인접한 칸의 차이가 L<= <=R 을 만족하지 않을때
		 * flag 세워서 국경개방을 하는 경우가 한번이라도 있으면 true
		 * 하루가 끝났는데 !flag 일때(국경개방 한번도 x) 인구이동 끝내기 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int r=0; r<N; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st2.nextToken());
			}
		}
		
		// bfs
		label:
		while(true) {
			flag = false;
			
			queue = new ArrayDeque<>();
			visit = new boolean[N][N];			
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					if (visit[r][c]) {
						continue;
					}
					
					Country input = new Country(r,c,map[r][c]);
					
					queue.add(input);
					
					List<Country> list = new ArrayList<>();
					int cnt = 0 ; // 나라 수
					int pCnt = 0 ; // 인구 수
					
					while (!queue.isEmpty()) {
						Country next = queue.poll();
						visit[next.r][next.c] = true;

						list.add(next);
						
						cnt++;
						pCnt += next.p;
						
						for (int d=0; d<4; d++) {
							int nr = next.r+dr[d];
							int nc = next.c+dc[d];
							
							if (nr>=0 && nr<N && nc>=0 && nc<N && !visit[nr][nc]) {
								if (Math.abs(next.p-map[nr][nc]) >= L 
										&& Math.abs(next.p-map[nr][nc]) <= R) {
									// 국경 개방
									visit[nr][nc] = true;
									flag = true;
									queue.add(new Country(nr, nc, map[nr][nc]));
								}
							}
						}
					}
					
					int finalP = pCnt / list.size();
					
					// 인구분배
					for (Country C : list) {
						map[C.r][C.c] = finalP;
					}
				}
			}
			
			if (!flag) {
				break label; // 무한반복 while문 탈출
			}
			
			day++;
		}
		
		System.out.println(day);

	}
}
