package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_1976_성민기 {
	
	static int N, M;
	static int[][] map;
	static int[] parent;
	static int[] trip;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N][N]; 
		parent = new int[N];
		trip = new int[M];
		// 처음에는 다 연결이 안되어있으니까 자기자신이 다 부모다
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				// 여기서 MAP 배열로 받지 않고 INT 형으로 받아서 변수로 판단해도 괜찮다.
				// -> 메모리 절약 가능 
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1 && i < j) {
					union(i,j);
				}
			}
		}
        
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			trip[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for(int i=0; i<M-1; i++) {
			if(find(trip[i]) != find(trip[i+1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	public static int find(int x) {
		if(x==parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		// 그냥 연결하는 것이 아니라 find(x)로 해줘서 부모끼리 연결을 시켜줘야 된다.
		parent[find(y)] = find(x);
	}
	
}

