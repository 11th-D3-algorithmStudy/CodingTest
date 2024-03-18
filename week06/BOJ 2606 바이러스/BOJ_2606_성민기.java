package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2606_성민기 {
	
	/* 풀이시간 : 240308 19:54 ~ 20:14
	 * 메인접근법
	 *     - DFS라고 생각하고 들어감 
	 *     - 연결된 것만 찾아가기 때문에 방문 처리 떄마다만 count++해줌
	 * 막힌 부분
	 *     - count를 방문할 때마다 더해주었다가 마지막 
	 *       다른 것 방문 시에도 count되는 듯 하여 배치를 다르게함
	 *       
	 * 메모리 : 14212 kb
	 * 시간 : 124 ms
	 */
	
	static boolean[] visited; // 방문처리 할 배열
	static ArrayList<ArrayList<Integer>> ListDFS; // DFS 활용할 리스트
	static int count = 0; // 감염 횟수 카운트
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		// 입력값을 통해 사이즈 설정
		visited = new boolean[N+1];
		ListDFS = new ArrayList<>();
		// 왜 i<=N+1 이지?
		for(int i=1; i<=N+1; i++) {
			ListDFS.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 서로 간 연결
			ListDFS.get(a).add(b);
			ListDFS.get(b).add(a);
		}
		DFS(1);
		System.out.println(count);
	}
	
	static void DFS(int node) {
		
		visited[node] = true;
		
		for(int next : ListDFS.get(node)) {
			if(!visited[next]) {
				// 방문할 때마다 카운트
				// 여기에다가 count++해야 맞았음
				count++;
				DFS(next);
			}
		}
		
	}
}
