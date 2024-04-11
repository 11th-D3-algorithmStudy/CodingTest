package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_9205_성민기 {
	
	/* 풀이시간 : 240329
	 * 메인접근법 :
	 * 		- BFS 사용
	 *      - 주어진 순서대로 방문하는 것이 아님(순서대로 방문한다는 보장 X)
	 *      - 가까운 편의점 가면된다
	 *   
	 * 막힌 부분 
	 *      - 순서대로 방문하는 것으로 하였다가 계속 틀림
	 *         -> 가까운 거리가 있을 때 그곳으로 방문할 수도 있다는 것을 처리함
	 *         
	 * 메모리: 14880 KB / 시간 : 152 ms
	 */
	
	static int N, beer;
	static int[][] map;
	static boolean flag; // 축제 갈 수 있는지 확인용
	static boolean[] visited; // 방문배열
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// 입력값 및 배열크기 선언
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][2];
			visited = new boolean[N+2];
			
			flag = false;
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<2; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 맥주의 시작 갯수
			beer = 20;
			BFS(0, 0);
			
			// 왜 마지막 방문처리의 결과에 따라서 정해지지?
			// 마지막이 페스티벌 위치이니까 방문했을 때(가능할 때)만 true가 되는 것 같음
			if(visited[N+1]) sb.append("happy" + "\n");
			else sb.append("sad"+"\n");
		}
		System.out.println(sb);
	}
	
	public static void BFS(int R, int C) {
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int[] {R, C});
		visited[R] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
		
			for(int i=1; i<N+2; i++) {
				// 방문하지 않았다면
				if(!visited[i]) {
					// 방문하지 않은 위치와 거리 비교
					int resultX = Math.abs(map[i][0] - map[x][y]);
					int resultY = Math.abs(map[i][1] - map[x][y+1]);
					int result = resultX + resultY;
					
					// 거리 1000 초과되면 방문 불가
					if(result <= 1000) {
						visited[i] = true;
						queue.add(new int[] {i, 0});
					} 
				}
			}
		}
	}
}
