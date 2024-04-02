package week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1976_정다운 {
	
	static int N, M; // 도시의 개수, 여행 계획에 속한 도시 수
	static int[] p; // 대표 저장 배열
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws Exception {
		// 간선 2차원배열 이용해서 집합 연결하고
		// 마지막에 나온 도시들이 같은 집합인지 확인하기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		p = new int[N+1];
		
		//makeset
		for (int i=1; i<=N; i++) {
			p[i] = i;
		}
		
		// 간선정보 입력받으면서 union
		// 도시 수 만큼 반복
		for (int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				int load = Integer.parseInt(st.nextToken());
				if (load == 1) { // 두 도시가 연결되었다 ? union ㄱㄱ 
					int pr = findset(r+1);
					int pc = findset(c+1);
					
					// union
					if (pr != pc) {
						p[pr] = pc;
					}
				}
			}
		}
		
		// 혹시나 findset으로 안부른 노드가 있을수도 있으니까 모든 노드 findset 호출ㄱㄱ
		for (int i=1; i<=N; i++) {
			int PC = findset(i); 
		}
		
		// 여행할 도시 번호
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		for (int i=0; i<M; i++) {
			int city = Integer.parseInt(st2.nextToken());
			set.add(p[city]);
		}
		
		if (set.size() == 1) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
		
	}
	
	static int findset(int x) {
		if (x != p[x]) {
			p[x] = findset(p[x]);
		}
		return p[x];
	}
}
