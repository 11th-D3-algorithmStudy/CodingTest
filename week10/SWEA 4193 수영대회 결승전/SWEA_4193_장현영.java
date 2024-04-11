package week10;

import java.util.*;
import java.io.*;


public class SWEA_4193_장현영 {
	// 수영결승
	// bfs로 최저값 계속 갱신해주면서 답구하기
	// 제자리 멈춰 조건때문에 고민 시간 길어짐
	// time이 증가하므로 %3을 이용해서 마지막(제자리일 떄)인 경우 확인
	static int pos[][] = new int[2][2];
	// 제자리일때도 확인해야하므로 5방탐색 진행
	static int[][] arr;
	static int[] dr = {-1,1,0,0,0};
	static int[] dc = {-0,0,1,-1,0};
	static Queue<int[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
	
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
            
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 2; j++) {
					pos[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
            //출력
			System.out.println("#" + t + " " + bfs(pos));
		}
	}

	public static int bfs(int[][] pos) {
		
		int res = Integer.MAX_VALUE; //결과
		int k = arr.length;		 //맵 크기
		boolean visited[][] = new boolean[k][k];
		
		queue.offer(new int[] {pos[0][0], pos[0][1], 0, 0});
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int time = curr[2];
			int stay = curr[3];	
			
			//방문 처리
			visited[r][c] = true;
				
			//최저값 갱신 : 목적지 도착
			if(r == pos[1][0] && c == pos[1][1]) {
				res = Math.min(res, time);
				continue;
			}
			
			for (int i = 0; i < 5; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// continue 조전 먼저 걸어주기
				if(nr < 0 || nc < 0 || nr >= k || nc >= k) continue;
				// 1 장애물인 경우
				if(arr[nr][nc] == 1) continue; 								//1. 장애물
				// 2 못가는 소용돌이
				if(i != 4 && arr[nr][nc] == 2 && time % 3 != 2) continue;	
				// 3 제자리 제외하고 다 방문이 된 경우
				if(i != 4 && visited[nr][nc]) continue; 		
				// 4 제자리 3초
				if(stay == 3) continue;						

				
				//제자리 이동 인덱스일 경우 시간 추가, 아닐 경우 시간 초기화
				if(i == 4) 
					queue.offer(new int[] {nr, nc, time + 1, stay + 1});						
				else 
					queue.offer(new int[] {nr, nc, time + 1, 0});
			}
		}
        
		//결과 출력
		if(res == Integer.MAX_VALUE) // 갱신되지 않은 경우
			return -1;
		return res;
	}

}
