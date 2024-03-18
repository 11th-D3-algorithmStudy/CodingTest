package week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023_정다운 {
	
	static List<Integer>[] connInfo;
	static int N, M; // 사람의 수, 관계의 수
	static boolean[] visit;
	static int cnt; // 연속 방문 횟수
	static int res = 0; // 결과
	
	public static void main(String[] args) throws Exception {
		// 문제부터 이해가 안됨........
		// 연속된 5개의 친구관계?
		// 어디서든 시작했을때 연속 5개 노드 방문 가능한경우 찾기
		// (재방문 불가)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		connInfo = new ArrayList[N]; // idx 0 부터 사용
		for (int i=0; i<N; i++) {
			connInfo[i] = new ArrayList<>();
		}
		
		visit = new boolean[N];
		
		
		for (int i=0; i<M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st2.nextToken());
			int b = Integer.parseInt(st2.nextToken());
			
			connInfo[a].add(b);
			connInfo[b].add(a);
		}
		
		for (int i=0; i<N; i++) {
			dfs(i, 1); // 시작하는 위치 cnt 1
			if (res == 1) {
				break;
			}
		}
		
		System.out.println(res);
		
	}

	static void dfs(int idx, int cnt) {
		// cnt 5 만족하면 res 업데이트 후 리턴
		if (cnt == 5) {
			res = 1;
			return;
		}
		visit[idx] = true; // 방문처리
		
		for (int a : connInfo[idx]) {
			if (!visit[a]) {
				dfs(a, cnt+1); // 카운트 늘려서 dfs
			}
		}
		visit[idx] = false; // cnt 5 만족 못하고 나오면 방문처리 초기화
		
	}
}
