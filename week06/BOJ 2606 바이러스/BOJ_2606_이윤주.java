package Study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2606_이윤주 {
	/*
	 * Q. 바이러스
	 *  #문제요약
	 *  	어떤 컴퓨터와 연결되어 있는 컴퓨터는 웜바이러스가 전파된다
	 *  	1번 컴퓨터가 웜바이러스에 걸렸을 때, 웜바이러스에 걸리게 되는 컴퓨터의 수를 출력하라
	 *  #풀이시간 : 1시간
	 *  #메모리/시간 : 14200kb / 124ms
	 *  #메인접근법
	 *  	1. 그래프에 연결된 모든 노드 찾으면서 카운트 -> DFS
	 *  
	 */
	
	static ArrayList<ArrayList<Integer>> graph;
	static int N; //컴퓨터의 수
	static int E; //컴퓨터 쌍의 수 = 간선의 수
	static int count; //1번을 통해 바이러스 걸리게 되는 컴퓨터 수
	static boolean[] visited;
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		visited = new boolean[N + 1]; //0번 더미
		graph = new ArrayList<>();
		
		//그래프 초기화 N+1개, 0번 더미
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		//간선 정보 입력
		for(int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}
		
		dfs(1);
		
		System.out.println(count); 
	}

	
	private static void dfs(int node) {
		//내가 끝까지 왔엉 - 현재 노드에 연결된 자식 1개 
		if(graph.get(node).size() == 1 && node != 1) {
			return;
		}
		
		visited[node] = true;
		
		//재귀부분
		for(int i = 0; i < graph.get(node).size(); i++) {
			int linkNode = graph.get(node).get(i);
			if(!visited[linkNode]) { //방문안한 자식 방문해
				visited[linkNode] = true;
				count++; //자식 개수 세기
				dfs(linkNode);
			}
		}
	}
}
