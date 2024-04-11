package week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1197_정다운 {

	static int V, E;
	static int[] p; // 집합 대표 저장 배열
	
	public static void main(String[] args) throws Exception {
		// 크루스칼~
		// 수업에서 사용한 코드 참고
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		p = new int[V+1];
		int[][] edges = new int[E][3]; // [0] : 시작정점, [1] : 끝정점, [2] : 가중치
		
		// V개 makeset
		for (int i=1; i<=V; i++) {
			p[i] = i;
		}
		
		for (int i=0; i<E; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st2.nextToken());
			int B = Integer.parseInt(st2.nextToken());
			int C = Integer.parseInt(st2.nextToken());
			
			edges[i][0] = A;
			edges[i][1] = B;
			edges[i][2] = C;
		}
		
		Arrays.sort(edges, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2];
			}
			
		}) ;
		
		int ans = 0; // 최소비용의 합
		int pick = 0;
		
		for (int i=0; i<E; i++) {
			int px = findset(edges[i][0]);
			int py = findset(edges[i][1]);
			
			// A & B union (부모가 다른경우에만)
			if (px != py) {
				p[py] = px;
				ans += edges[i][2];
				pick++;
			}
			
			if (pick == V-1) {
				break;
			}
		}
		
		System.out.println(ans);
		
	}

	static int findset(int i) {
		if (p[i] != i) {
			p[i] = findset(p[i]);
		}
		return p[i];
	}
}
