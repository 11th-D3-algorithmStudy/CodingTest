package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_13023_성민기 {
	
	/* 풀이시간 : 240308 20:23 ~ 22:30 / 240309 14:30 ~ 15:10
	 * 메인접근법 
	 *     - 깊이가 총 5개가 되는 경우가 존재하면 친구 관계 파악완료
	 *     - DFS(1)처럼 했다가 1이 루트노드라는 것이 보장되지 않으니
	 *       모든 경우의 수를 살펴봄 
	 *     - DFS 메소드에 level++이 아닌 level+1을 통해
	 *       다시 부모로 올라가는 과정에서도 level의 값이 유지되도록 하였음
	 *       (깊이 탐색 횟수를 카운트 하는 것이 아니기 때문에 level++ 하지 않음)
	 * 
	 * 막힌 부분 
	 *     - 모든 경우의 DFS를 확인하였을 때 방문처리가 되어있어서 초기화 해야하는 문제 발생
	 *       └> 해결방안 : DFS 메소드는 방문한 노드를 다시 방문하지 않도록 방문 기록 유지
	 *                  이 부분에서 방문기록을 초기화하여 재방문 가능하도록 해주어야 했음
	 *                  따라서 DFS 메소드 마지막 부분에 현재 노드 방문 기록 제거
	 *                  초기화를 위한 visited[node] = false를 통한 백트래킹 해줌
	 *                  -> 결과 : 다시 해당 노드 재방문 가능
	 *       
	 *       -> 여기서 알게 된 새로운 사실
	 *          - Arrays.fill() : 배열 일괄 초기화 메소드가 존재
	 *            배열의 모든 값을 지정한 값으로 초기화하는 메서드
	 *            ex) Arrays.fill(visited, false);
	 *         
	 * 메모리 : 22052 kb
	 * 시간 : 288 ms
	 */
	
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> ListDFS;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		ListDFS = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			ListDFS.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			ListDFS.get(a).add(b);
			ListDFS.get(b).add(a);
		}
		result = 0;
		// 모든 노드에 대해 DFS 호출
        for (int i = 0; i < N; i++) {
//        	Arrays.fill(visited, false); // 방문 기록 초기화
//        	visited = new boolean[N+1];
        	DFS(i, 0);
            if (result == 1) break; 
        }
		System.out.println(result);
	}
	
	
	static void DFS(int node, int level) {
		visited[node] = true; // 방문 기록

		// 깊이가 4가 되면 종료
		if(level==4) {
			result = 1;
			return;
		}

		for(int next : ListDFS.get(node)) {
			if(!visited[next]) {
				DFS(next, level+1);
				// result 값이 1이면 반복문, 메소드 종료
				if(result==1) return;
			}
		}
		
		visited[node] = false; // 방문기록 초기화
	}
}
