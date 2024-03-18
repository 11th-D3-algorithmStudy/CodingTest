package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_16174_성민기 {
	
	/* 풀이시간 : 240309 15:44 ~ 16:30
	 * 메인접근법
	 *      - 델타 배열을 사용하여 이동 + 발판의 수만큼 점프(곱하기)해줌
	 *      - DFS를 활용해서 방문여부를 통한 접근가능 여부 확인
	 *      
	 * 막힌 부분
	 *      1. 델타배열을 통한 이동할 때 점프를 어떻게 해주어야하는지 생각 시간 걸림
	 *         -> 델타값 nr을 구할 때 dr[d]*map[r][c]를 통해 발판만큼 점프하도록 함
	 *         
	 *      2. 방문처리 안하고 DFS 실행하니 메모리 초과 -> BFS는 괜찮나봄
	 *         -> 방문처리 배열 만들어서 방문 여부 체크를 통해 들어가니 해결됨.
	 *         
	 * 메모리 : 14604 KB
	 * 시간 : 136 ms
	 */
	
	static int[][] map;
	static int N;
	static int result;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력값 받기
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		
		// 맵 생성
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Jump(0,0);
		
		// 결과 출력
		if(result==-1) System.out.println("HaruHaru");
		else System.out.println("Hing");
		
	}
	
	static void Jump(int r, int c) {
		visited[r][c] = true; // 방문 처리
		
		// -1인 곳이면 종료
		if(map[r][c]==-1) {
			result = -1;
			return;
		}
		
		// 맵을 벗어나도 종료
		if(r>=N || c>=N) return;
		
		// 우 하
		int[] dr = {0, 1};
		int[] dc = {1, 0};
		
		for(int d=0; d<2; d++) {
			int a = map[r][c];
			// 발판만큼 점프하도록 곱해줌
			int nr = r + dr[d]*a;
			int nc = c + dc[d]*a;
			
			// 방문처리여부 확인을 통한 진행
			if(nr >= 0 && nc >= 0
					&& nr < N && nc < N && !visited[nr][nc]) {
				Jump(nr, nc);
				if(result==-1) return;
			}
		}
		// 여기서 드는 의문 : dfs메소드 안의 여러 return중 몇개 없어도 작동되는지 궁금
		
	}
}
