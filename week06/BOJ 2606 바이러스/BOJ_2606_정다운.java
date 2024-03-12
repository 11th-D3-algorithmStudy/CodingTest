package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2606_정다운 {
	
	static int N, L; // 노드(컴퓨터), 간선 개수
	static List<Integer>[] connInfo; // 노드 연결 정보
	static boolean[] visit; // 방문 여부 배열
	static int res; // 결과
	
	
	public static void main(String[] args) throws Exception {
		// 컴퓨터끼리 연결한 뒤
		// 1번 컴퓨터부터 탐색시작
		// dfs 로 고고
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		L = Integer.parseInt(br.readLine());
		
		connInfo = new ArrayList[N+1]; // idx 1부터 사용
		for (int i=0; i<N+1; i++) {
			connInfo[i] = new ArrayList<>();			
		}
		visit = new boolean[N+1];
		res = 0;
		
		for (int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			connInfo[a].add(b);
			connInfo[b].add(a);
		}
		
		// 1번 노드에서 갈 수 있는 노드 개수 확인
		virus(1);
		
		System.out.println(res-1); // 1번 컴퓨터 개수도 포함이라 -1 
	}


	static void virus(int i) {
		// 모든 노드를 방문했을때 리턴
		if (visit[i]) {
			return;
		}
		visit[i] = true; // 방문 처리
		res++; // 바이러스 걸림
		
		for (int a : connInfo[i]) {
			virus(a);
		}
		
	}
}
