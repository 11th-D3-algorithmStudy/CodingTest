package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SOFT_6246_정다운 {
	
	static int n, m;
	static int[][] arr; // 격자
	static boolean[][] visit; 
	static List<int[]> list; // 방문지점 좌표 저장 리스트
	static int cnt = 0; // 경우의 수
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		// 모든 지점을 순서대로 방문하는 경우의 수
		// 방문했던 좌표 재방문 불가
		// 나중에 방문해야 할 지점 먼저 밟아서도 안됨....
		
		// 방문해야하는 지점 모두 리스트에 저장해두고
		// 지점에 도착했는지 & 몇번째 지점까지 왔는지 확인하기 
		
		// dfs 들어갔다가 나올때 방문처리 되돌려 놓는것 잊지말기 ~
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		visit = new boolean[n][n];
		list = new ArrayList<>();
		
		// 격자 정보 저장
		for (int r=0; r<n; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<n; c++) {
				arr[r][c] = Integer.parseInt(st2.nextToken());
			}
		}
		
		// 방문해야하는 좌표 저장
		for (int i=0; i<m; i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st3.nextToken());
			int c = Integer.parseInt(st3.nextToken());
			int[] point = {r-1, c-1}; // 좌표 1씩 낮춰서 사용해야함
			list.add(point);
		}
		
		// 첫번째 위치부터 가자~....
		dfs(list.get(0)[0],list.get(0)[1], 0); 
		
		System.out.println(cnt);
		
	}

	// r, c : 현재 좌표 / x : 지금 몇번째 방문지점까지 갔는지?
	static void dfs(int r, int c, int x) {
		// 마지막 방문지점 도달하면 경우의 수 추가하고 리턴
		if (x == m-1) { // list의 idx와 맞추기 위해 -1
			cnt++;
			return;
		}
		visit[r][c] = true; // 방문처리
		
		for (int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (nr >= 0 && nr < n && nc >= 0 && nc < n // 경계조건
					&& !visit[nr][nc] // 방문하지 않았고
					&& arr[nr][nc] != 1) { // 벽이 아니라면
				if (nr == list.get(x+1)[0] &&
						nc == list.get(x+1)[1]) { // 다음 방문지점이다?
					dfs(nr, nc, x+1); // 방문지점 개수 +1
					visit[nr][nc] = false; // 방문처리 되돌려놓기.... 빼먹지 말기......
				} else { // 방문지점 아니다
					dfs(nr, nc, x); // 방문지점 개수 그대로
					visit[nr][nc] = false;
				}
			}
		}
		
		
	}
}
