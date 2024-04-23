package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10026_성민기 {
	
	static char[][] redgreen;
	static char[][] normal;
	static boolean[][] visited;
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		redgreen = new char[N][N];
		normal = new char[N][N];
		visited = new boolean[N][N];
		
		// 입력값
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			redgreen[i] = line.toCharArray();
			normal[i] = line.toCharArray();
		}
		
		// 적록색약인 사람의 경우를 위해 G -> R로 변경
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(redgreen[i][j]=='G') {
					redgreen[i][j] = 'R';
				}
			}
		}

		int countRed = 0;
		int countNormal = 0;
		
		// 적록색약인 사람의 구역 구분
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 방문처리가 되어있지 않다면 구역탐색
				if(!visited[i][j]) {
					DFS(i, j, redgreen);
					// DFS가 끝날때마다 카운트
					countRed++;
				}
			}
		}
		// 방문배열 초기화
		visited = new boolean[N][N];
		
		// 일반사람 구역 구분
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					DFS(i, j, normal);
					countNormal++;
				}
			}
		}
		System.out.println(countNormal + " "+ countRed);
	}
	
	public static void DFS(int r, int c, char[][] arr) {
		if(visited[r][c]) return;
		
		visited[r][c] = true;
		
		// 델타배열 상 하 좌 우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < N
					&& nc >= 0 && nc < N
					&& arr[r][c] == arr[nr][nc]) {
				DFS(nr, nc, arr);
			}
		}
	}
}
