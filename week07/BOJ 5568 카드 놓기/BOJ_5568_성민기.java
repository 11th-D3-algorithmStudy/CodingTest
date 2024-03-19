package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_5568_성민기 {
	
	/* 풀이시간 : 240314 20:15 ~ 21:40 + 240315 22:40 ~ 22: 52
	 * 메인접근법
	 *      - 백트래킹(기저조건)의 기준을 K로 하여 K번만큼 들어갔다면 return
	 *      - HashSet을 사용하여 중복값 없도록 함
	 *      - 방문처리 후 false로 초기화화는 과정을 거침
	 *      
	 * 막힌 부분
	 *      - DFS 공부 부족으로 한번에 풀지 못하고 추가 공부 후 풀이함
	 *      - 순열을 어떻게 해야할지 몰라서 찾아보았음
	 *      - 백트래킹의 기준을 정하지 못했음 -> depth의 횟수로 파악할 수 있었음
	 * 
	 * 메모리 : 15400 KB
     * 시간 : 160 ms
	 */
	
	static boolean[] visited; // 방문처리
	static Set<Integer> set;  // 중복예방을 위한 set 사용
	static List<Integer> list;// 배열값 활용
	static int N, K;  // 입력값
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		list = new ArrayList<>();
		set = new HashSet<Integer>();
		
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		// 빈 String값 선언 후 DFS입력값으로 활용
		String start = "";
		
		DFS(start, 0);
		
		System.out.println(set.size());
	}
	
	public static void DFS(String s, int level) {
		// K번만큼 들어간다면 멈추고 set에 저장 후 종료
		if(level==K) {
			set.add(Integer.parseInt(s));
			return;
		}
		
		// 모든 경우의 수 확인
		for(int i=0; i<N; i++) {
			// 방문처리 확인
			if(visited[i]) continue;
			
			// 방문처리
			visited[i]=true;
			
			DFS(s+Integer.toString(list.get(i)), level+1);
			
			// 방문처리 초기화
			visited[i]=false;
		}
	}
	
	
}
