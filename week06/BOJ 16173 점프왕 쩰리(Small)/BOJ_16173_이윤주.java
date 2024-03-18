package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16173_이윤주 {
	/*
	 * Q. 점프왕 쩰리
	 * #문제요약
	 * 	쩰리는 정사각형 구역 내부에서만 움직일 수 있음
	 * 	출발점 : 가장 왼쪽 가장 위 칸
	 *  이동 가능한 방향 : 오른쪽, 아래
	 *  쩰리가 가장 오른쪽 가장 아래칸에 도달하는 순간 쩰리 승리, 게임 종료
	 *  한번에 이동할 수 있는 칸의 수 : 현재 밟은 칸에 쓰인 수 만큼 (초과, 미만X)
	 *  
	 * #풀이시간 : 1시간
	 * #메모리/시간 : 14252kb/124ms
	 * #메인 접근법
	 * 	1. 성공,실패 표시하는 flag로 출력
	 *  2. 이동할 때 마다 오른쪽 or 아래쪽으로 칸에 쓰인 수 만큼 가야됨
	 *  -> 현재 칸에 연결된 노드로 이동하는 것 같은 동작
	 *  -> 현재 칸 기준으로 두 방향 모두 구역 밖으로 나간다 -> 실패
	 *  -> 현재 칸이 0이다 -> 실패로 하고 이전으로 돌아감
	 *  3. DFS로 풀어보자
	 */
	
	static int N;
	static int[][] board;
	static boolean canWin;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		canWin = false;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		if(canWin) {
			System.out.println("HaruHaru");
		} else {
			System.out.println("Hing");
		}

	}
	//r,c : 현재칸 위치
	public static void dfs(int r, int c) {
		//기저조건
		//범위 밖으로 나가거나, 현재 위치가 0 이면 실패 return
		if(r >= N || c >= N || board[r][c] == 0) {
			return;
		}
			
		//지금 위치가 도착지면 승리 return
		if(board[r][c] == -1) {
			canWin = true;
			return;
		}
		
		//재귀 부분
		//현재칸 숫자
		int move = board[r][c];
		
		dfs(r, c + move); //오른쪽 이동
		dfs(r + move, c); //아래쪽 이동
	}
}
