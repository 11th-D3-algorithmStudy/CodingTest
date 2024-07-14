package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라 문제라지만, BFS 문제 같다,,,
// BFS 다익스트라 섞음
public class BOJ_4485_조아름 {
	static boolean[][] visited;
	static int[][] arr, size;
	static int N;
	static int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node>{ // 다익스트라 적용해야 하니까 Comparable 넣어줌
		int x;
		int y;
		int rup;
		
		public Node(int x, int y, int rup) {
			super();
			this.x = x;
			this.y = y;
			this.rup = rup;
		}

		@Override
		public int compareTo(Node o) {
			return this.rup - o.rup;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = 1; // tc 개수
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N==0) {
				break;
			}
			arr = new int[N][N];
			size = new int[N][N];
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					size[i][j] = INF;
				}
			}
			
			visited = new boolean[N][N];
			bfs(0,0, arr[0][0]);
			System.out.println("Problem "+cnt+": "+size[N-1][N-1]);
			cnt++;
		}
	}

	private static void bfs(int x, int y, int rup) {
		int[] dr = {-1,1,0,0}; // 상하좌우
		int[] dc = {0,0,-1,1}; // 상하좌우
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		visited[x][y] = true;
		q.offer(new Node(x,y,rup));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for(int i=0;i<4;i++) {
				int xx = node.x+dr[i];
				int yy = node.y+dc[i];
				
				if(inMap(xx,yy) && !visited[xx][yy]  && size[xx][yy] > (arr[xx][yy]+node.rup)) {
					size[xx][yy] = arr[xx][yy]+node.rup; // 다익스트라 적용
					visited[xx][yy] = true;
					q.offer(new Node(xx,yy,size[xx][yy]));
				}
			}
		}
	}
	
	public static boolean inMap(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}

}
/*
 * 00 01 02
 * 10 11 12
 * 20 21 22
 * 
 * */
	
