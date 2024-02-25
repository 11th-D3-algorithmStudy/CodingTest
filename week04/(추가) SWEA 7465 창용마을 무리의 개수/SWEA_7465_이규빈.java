// A형 보충수업 문제
// - 자력으로는 못 풀었고, 보충수업을 들은 후 다시 풀어봄
/*
 *  Q.창용 마을 무리의 개수
 *	# 문제 요약
 *		사람 관계 문제 -> 그래프
 *		무리가 몇 개인지 계산하라.
 *	# 풀이시간 : 1시간
 *	# 메인 접근법
 *		N이 100 이하이므로, 배열을 이용해 그래프를 구현해도 무방해 보인다. (=메모리 초과 가능성 적다)
 *		관계 정보를 인접 배열에 저장하고, "그래프 연결여부"를 판단하는 문제이므로 DFS를 수행한다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465_이규빈 {
	static int N, M;
	static int relation[][]; // 관계정보를 저장하는 인접 행렬
	static boolean visited[]; // 정점 방문처리용
	static int res; // 무리 개수를 세는 변수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			relation = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			
			// 연결 정보 저장
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				relation[a][b] = 1;
				relation[b][a] = 1; // "서로" 알고 있는 양방향 그래프이므로, 행과 열을 바꿔서도 1을 입력한다.
			}
			
			// ------------------ Input End -----------------------
			
			// 정점마다 그래프 연결여부 확인 (DFS)
			res = 0;
			for (int i = 1; i <= N; i++) {
				// i번 정점을 이미 방문했다는 것은, 다른 무리에 이미 속해있다는 의미
				// - 따라서 방문하지 않았던 경우만 처리한다.
				if (!visited[i]) {
					dfs(i); // 이 함수가 종료된 경우 시스템 스택이 비워진 것이므로, 
							// 그래프의 끝까지 탐색했다는 의미 (= DFS 사이클 하나 끝)
					res++; // DFS 사이클 수 == 무리 개수
				}
			}
			
			// 결과 출력
			System.out.println("#" + t + " " + res);
		}
	} // 메인 메소드 종료
	
	static void dfs(int node) {
		if (!visited[node]) {
			visited[node]= true; // 방문 처리
			for (int i = 1; i <= N; i++) {
				if (relation[node][i] == 1 && !visited[i])  dfs(i);
			}
		}
	}
}