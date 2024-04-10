import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197_이윤주 {
	/*
	 * Q. 최소 스패닝 트리
	 * 
	 * 크루스칼 알고리즘으로 해보자!
	 */
	static int[] p; //집합의 대표를 저장하는 배열
	
	static class Edge implements Comparable<Edge>{
		int A,B,C;

		public Edge(int a, int b, int c) {
			A = a;
			B = b;
			C = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.C, o.C); //간선 오름차순 정렬
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); //정점 개수
		int E = Integer.parseInt(st.nextToken()); //간선 개수
		
		Edge[] edges = new Edge[E]; //간선들 저장할 배열
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(A,B,C);
		} //간선 정보 입력 끝
		
		
		//크루스칼 
		//1. 간선 가중치 오름차순 정렬
		Arrays.sort(edges);

		//2. 가중치 작은 것 부터 사이클 안되는지 확인해서 선택하기
		p = new int[V + 1]; //서로소집합, 상호배타집합 만들기위해 대표를 저장하는 배열
		//정점 번호 1부터 시작 V까지
		for(int i = 1; i < V + 1; i++) {
			p[i] = i;
		}//초기화
		
		int pick = 0;
		int answer = 0;
		
		for(int i = 0; i < E; i++) {
			int pa = findset(edges[i].A);
			int pb = findset(edges[i].B);
			
			if(pa != pb) { //서로 다른 집합에 있으면 추가 
				union(pa, pb);
				pick++;
				answer += edges[i].C;
				if(pick == V - 1)
					break;
			}
		}
		
		System.out.println(answer);
	}
	
	//합집합 만들기
	private static void union(int pa, int pb) {
		//a집합에 냅다 b넣어버리기
		p[pb] = pa;
	}

	//집합의 대표 찾기
	private static int findset(int a) {
		//패스 컴프레션
		if(p[a] != a)
			p[a] = findset(p[a]);
		return p[a];
	}
}
