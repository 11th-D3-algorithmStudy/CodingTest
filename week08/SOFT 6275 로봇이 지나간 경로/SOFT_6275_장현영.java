package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SOFT_6275_장현영 {
	// 처참하게 실패한 코드 ㅠ
	// 가장 어려웠던 부분 : 역으로 command 최소값 찾기
	// L : 왼쪽 90도, r: 오른쪽 90도 a 바라보는 방향으로 두칸 전진(불가능하다면 수행 x)
	// 같은 칸 2번 이상 방문 안하도록 명령
	// 방문한 곳은 지도에 표시
	// 처음 위치와 방문한 곳을 보고 내가할 것
	// 1. 처음 로봇 위치와 방향
	// 2. 로봇의 명령 어떤 것이었나?
	// 방문한 곳이면 # 방문 안했으면 .
	// 즉, #으로만 갈 수 있고, 명령어 붙였을 때 길이 가장 적은 것 출력하기
	// 북동남서
	// 정방향 r, 역방향 l
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static char[] direction = { '^', '>', 'v', '<' }; // 첫 로봇
	static int n;
	static int m;
	static char[][] arr;
	static List<int[]> startList;
	static int cnt; // # 전체 개수
	static int minCommand = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n + 1][m + 1];
		startList = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= m; j++) {
				arr[i][j] = tmp.charAt(j - 1);
				// #이 시작 가능 위치
				if (arr[i][j] == '#') {
					cnt++;
					int[] spot = new int[] { i, j,0,0 };
					if(getFirst(spot)[0] != -1) { // false면 -1로 설정
						startList.add(getFirst(spot));
					}
				}
			}
		}
		for(int[] arr : startList) {
			System.out.println(Arrays.toString(arr));
		}
		String answer = "";
		int minCommand = Integer.MAX_VALUE;
		for(int[] each : startList) {
			String res = bfs(each);
			System.out.println(res);
			if(res.length() > 0) {
				answer = res;
			}
		}
		System.out.println(answer);
	}

	// #로 이루어진 길 한 줄 긋기 가능한가 판단하기
	// 명령어는 좌회전, 우회전, 직진!
	// 역추적..
	// 한 줄 긋기라 못가면 queue에서 뽑아내는 bfs가 아님
	
	// 핵심 start 지점 잡기
	// start 조건 a 진행시 단방향으로 이동이 가능한 곳인가를 판단.
	// 1. 갈 수 있어야함
	// 2. 가는데 두 갈래로 가는 곳이면 안됨
	
	public static int[] getFirst(int[] node) {
		int[] temp = {-1,-1,-1,-1};
		for(int i=0;i<4;i++) {
			int r = node[0];
			int c = node[1];
			int nr = r + dr[i];
			int nr2 = nr + dr[i];
			int nc = c + dc[i];
			int nc2 = nc + dc[i];
			if (isMap(nr, nc) && arr[nr][nc] == '#' && arr[nr2][nc2] == '#' && temp[0] == -1) {
				// 방향이랑 좌표 저장
				temp = new int[] {r,c,i,0};
			}
		}
		return temp;
	}
	
	
	public static String bfs(int[] start) {
		String temp = start[0] + " " + start[1] + "\n"+direction[start[2]]+"\n";
		String command = "";
//		StringBuilder command = new StringBuilder();
		boolean[][] visited = new boolean[n + 1][m + 1];
		visited[start[0]][start[1]] = true;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
		int rCnt=0; // r을 4번돌면 제자리
		int v=0;
		int count=0;
		while (!queue.isEmpty()) {
			if(rCnt >3) {//못감
				System.out.println(temp);
				System.out.println(command);
				return "hi";
			}
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int idx = curr[2]; // 방향 index
			count = curr[3]; // 지금까지 command 개수
			int nr = r + dr[idx];
			int nr2 = nr + dr[idx];
			int nc = c + dc[idx];
			int nc2 = nc + dc[idx];
			// A 갈 수 있나?
			if (isMap(nr, nc) && isMap(nr2,nc2) && arr[nr][nc] == '#' && arr[nr2][nc2] == '#' && !visited[nr][nc] && !visited[nr2][nc2]) {
				queue.offer(new int[] {nr2,nc2,idx,count+2});
				visited[nr][nc] = true;
				visited[nr2][nc2] = true;
				command+="A";
				rCnt = 0;
				v+=2;
			}else {
				queue.offer(new int[] {r,c,(idx+1)%4,count});
				command+="R";
				rCnt++;
			}
		}
		// 전체 # 방문처리 못하면 빡구
		command= command.replace("RRR","L");
		if(count == v && minCommand > command.length()) {
			minCommand = command.length();
			return temp+command;
		} else {//# 다 돌지 못했다면
			return "no";
		}
		}
			
	public static boolean isMap(int nr, int nc) {
		return (0 < nr && nr <= n && 0 < nc && nc <= m);
	}
}
