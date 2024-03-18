package algo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2606_조아름 {

	static int E;
	static int[][] Matrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		E = Integer.parseInt(br.readLine());// 컴퓨터
		int V = Integer.parseInt(br.readLine()); // 간선
		
		Matrix = new int[E][E];
		
		for(int i=0;i<V;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 값이 1부터 시작하기 때문에 배열에 0부터 넣어주기 위해서 1을 빼준다.
			int b = Integer.parseInt(st.nextToken()) - 1;
			Matrix[a][b] = Matrix[b][a] = 1;
		}
		
		System.out.println(bfs()-1); // 시작점은 세지 않아야 하기 때문에 1을 빼준다.
	}
	
	public static int bfs() {
		boolean[] visited = new boolean[E];
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll(); // 시작점
			
			for(int i=0;i<E;i++) {
				if(Matrix[now][i]==1 && !visited[i]) { // 방문하지 않았고 연결된 배열일 경우
					visited[i] = true; // 방문처리 해주고
					q.add(i); // 큐에 저장해 줌.
				}
			}
			
		}
		
		int count = 0;
		
		for(int i=0;i<E;i++) {
			if(visited[i]) { // 방문한 곳 개수세기
				count++;
			}
		}
		
		return count;
	}

}
