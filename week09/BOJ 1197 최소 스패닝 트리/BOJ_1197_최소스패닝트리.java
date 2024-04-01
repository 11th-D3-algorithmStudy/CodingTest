package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_1197_최소스패닝트리 {
	
	/* 풀이시간 : 240329 20:10 ~ 21:40
	 * 메인접근법 
	 *     - 수업시간에 배운 크루스칼 알고리즘 그대로 적용
	 *     - 가중치에 따른 오름차순 정렬 해주기 위한 Comparator 사용
	 *     
	 * 막힌 부분 : 수업 내용 복습 후 따라치는 것이 아닌 스스로 타이핑해서 구상함
	 * 메모리 : 49396 KB
	 * 시간 : 576 ms
	 */
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		int[][] edges = new int[E][3];
		parent = new int[V];
		
		for(int i=0; i<V; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken())-1;
			edges[i][1] = Integer.parseInt(st.nextToken())-1;
			edges[i][2] = Integer.parseInt(st.nextToken());
		} // 간선 정보 입력
		
		Arrays.sort(edges, new Comparator<int []>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2];
			}
		});
		
		int ans = 0;  // 최소비용의 합
		int pick = 0; // 간선의 수 체크하기 위한 것
		for(int i=0; i<E; i++) {
			int x = edges[i][0];
			int y = edges[i][1];
			
			// 같은 집합으로 묶인 것이 아니라면
			if(findSet(x) != findSet(y)) {
				// 합친 후 최소비용의 합 더해준
				union(x, y);
				// 현재 간선을 선택한 것이니 가중치를 추가
				ans += edges[i][2];
				pick++;
			}
			// 반복문 횟수 줄이기
			if(pick == (V-1)) break; 
		}
		
		System.out.println(ans);
	}

	// parent[y] = x; 보다 확실하게 parent[findSet(y)]를 해줌
	private static void union(int x, int y) {
		parent[findSet(y)] = findSet(x);
	}

	private static int findSet(int x) {
		if(x == parent[x]) return x;
		// findSet(parent[x])의 값을 parent[x]에 넣어주면
		// 그만큼 반복되는 횟수를 줄일 수 있다? -> 영호 피셜
		return parent[x] = findSet(parent[x]);
	}
}
