package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1197_조아름 {
	// 크루스칼 사용, 가중치가 음수일 수 있음. 
	static class dot implements Comparable<dot> {
		int a, b, w;

		public dot(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(dot o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}

	}

	static int[] p; // 대표 저장 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()); // 정점
		int E = Integer.parseInt(st.nextToken()); // 간선

		dot[] dots = new dot[E]; // 1부터 시작하므로

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			dots[i] = new dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(dots); // 1부터 값을 넣었으므로 정렬도 1부터 V까지만 함

		p = new int[V + 1]; // 대표 저장 배열
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}

		int ans = 0; // 최소비용 합
		int pick = 0; // 간선 수
		
		for(int i=0;i<E;i++) {
			if(dots[i]==null) {
				continue;
			}
			
			int px = findset(dots[i].a);
			int py = findset(dots[i].b);
			
			if(px != py) {
				union(px, py);
				ans += dots[i].w;
				pick++;
			}
			
			if (pick == (V - 1)) break;
		}
		
		System.out.println(ans);
		
	}

	 static void union(int x, int y) {
		 p[y] = x;
	}

	static int findset(int x) { // 부모찾기
		if(x!=p[x])
			p[x] = findset(p[x]);
		return p[x];
	}

}
