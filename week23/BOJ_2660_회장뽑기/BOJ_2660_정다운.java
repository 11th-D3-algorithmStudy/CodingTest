import java.io.*;
import java.util.*;

public class 회장뽑기 {
	
	static class Node {
		int num;
		int dist;
		
		Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
	}
	
	static int N; // 회원 수
	static List<Integer>[] connInfo; // 회원 연결정보
	static boolean[] visit;
	static int min = 987654321; // 점수
	
	public static void main(String[] args) throws Exception {
		// 본인과 가장 멀리 연결된 사람과의 거리가 가장 짧은 사람이 회장이 된다
		// bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		connInfo = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			connInfo[i] = new ArrayList<>();
		}
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			if (A == -1) break;
			int B = Integer.parseInt(st.nextToken());
			connInfo[A].add(B);
			connInfo[B].add(A);
		}
		
		int[] arr = new int[N+1]; // 회원 점수 보관 배열
		for (int i=1; i<=N; i++) {
			Queue<Node> queue = new ArrayDeque<>();
			visit = new boolean[N+1];
			visit[i] = true;
			queue.add(new Node(i, 0));
			int d = 0;
			
			while (!queue.isEmpty()) {
				Node next = queue.poll();
				d = next.dist;
				
				for (int n : connInfo[next.num]) {
					if (!visit[n]) {
						visit[n] = true;
						queue.add(new Node(n, d+1));
					}
				}
			}
			
			arr[i] = d;
			min = Math.min(min, d);
		}
		
		int cnt = 0; // 후보 수
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 회장 후보 보관 큐
		for (int i=1; i<=N; i++) {
			if (arr[i] == min) {
				cnt++;
				pq.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(min+" "+cnt+"\n");
		while (!pq.isEmpty()) {
			sb.append(pq.poll()+" ");
		}
		
		System.out.println(sb);
		
	}
}
