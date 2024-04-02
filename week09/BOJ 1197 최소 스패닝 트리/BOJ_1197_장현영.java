package week09;

import java.io.*;
import java.util.*;

public class BOJ_1197_장현영 {
	// 배웠던 크루스칼 알고리즘으로 최소 스패닝트리 구하자
	static int N,E;
	static int[] p; // 최종보스들 저장
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		p = new int[N+1];
		
		// 보스 처음에 자기 자신으로 초기화
		for(int i=1;i<=N;i++) {
			p[i] = i;
		}
		
		int[][] edge = new int[E][3];
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			
			// a-b (가중치 w)
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edge[i][0] =a;
			edge[i][1] =b;
			edge[i][2] =w;
		}
		
		// 크루스칼 -> 가중치 기준 오름차순 정렬
		Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2];
			}
		});
		int ans = 0;
		int cnt = 0;
		for(int i=0;i<E;i++) {
			int x = edge[i][0];
			int y = edge[i][1];
			
			// 보스가 다르다면 합병해주기
			if(find(x) != find(y)) {
				union(x,y);
				ans += edge[i][2]; // 현재 가중치 더하기
			}
			
		}
		System.out.println(ans);
		
	}
	
	public static void union(int a, int b) {
		p[find(b)] = find(a); // b의 최종 보스를 a의 최종 보스로 바꿔주기   
	}
	
	public static int find(int x) {
		if(x != p[x]) {
			p[x] = find(p[x]);
		}
		return p[x];
	}
	
}
