package week09;

import java.io.*;
import java.util.*;

public class BOJ_1976_여행가자_이규빈 {
	/*
	 *  여행이 가능한지? = 도시들이 연결되어 있는지? = UNION FIND
	 */
	static int[] p, rank;
	static int[][] edge;
	static List<Integer> plan;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 전체 도시의 수
		int M = Integer.parseInt(br.readLine()); // 여행계획에 속한 도시의 수
		
		edge = new int[N + 1][N + 1]; // 연결 정보
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				edge[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		plan = new ArrayList<>(); // 여행 계획
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			plan.add(Integer.parseInt(st.nextToken()));
		}
		
		// ------------------- INPUT END --------------------
		
		// MAKE-SET
		p = new int[N + 1]; // 노드의 부모 저장 배열
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			rank[i] = 0;
		}
		
		// UNION
		// - 연결된 도시들을 union해서 같은 집합으로 통합
		// - j 시작 인덱스 : 대각선을 기준으로 우상과 좌하가 대칭이기 때문에 오른쪽 위 삼각형만 탐색하면 되므로, i+1부터 시작한다.
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (edge[i][j] == 1) {
					int px = findSet(i);
					int py = findSet(j);
					
					union(px, py);
				}
			}
		}
		
		// 결과 출력
		// - 연결되지 않은 도시를 하나라도 만난 경우, 여행 불가능
		for (int i = 1; i < M; i++) {
			if (findSet(plan.get(0)) != findSet(plan.get(i))) {
				System.out.println("NO");
				return; // 메인 메서드 종료
			}
		}
		// 이 구역에 도달한 경우, 여행 가능
		System.out.println("YES");
	}


	private static int findSet(int x) {
		// Path Compression 적용
		if (x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}

	private static void union(int px, int py) {
		if (px != py) {
			if (rank[px] > rank[py]) {
				p[py] = px;
			}
			else {
				p[px] = py;
				if (rank[px] == rank[py])  rank[py]++;
			}
		}
	}
}
