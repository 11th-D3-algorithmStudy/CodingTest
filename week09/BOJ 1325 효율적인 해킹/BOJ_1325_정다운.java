package week09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_정다운 {
	
	static int N, M;
	static List<Integer>[] connInfo;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	static int max=0;
	static int min=987654321;
	static int[] cCntArr;
	
	public static void main(String[] args) throws Exception {
		// 시간 줄이기 위해 노력한 부분.......
		// 1. 처음에 dfs로 풀었다가 bfs로 수정
		// 2. bfs 내부에서 방문하는 컴퓨터의 최대값을 구한 다음에 bfs를 나와서 cCntArr에 입력해줬었는데
		//    컴퓨터에 방문할때마다 cCntArr의 인덱스에 접근해서 ++로 세어주는게 더 시간이 단축된다
		// 이 방법으로 1%에서 4%까지 갔으나 여전히 시간초과 ㅠ
		
		// 추가로 시간을 단축할 수 있는 힌트를 받았다..!!!!!
		// Queue를 LinkedList로 구현하지 말고 ArrayDeque으로 구현하면 더 빠르다고 한다
		// + 메모리 측면에서도 더 효율적!
		// https://velog.io/@newdana01/Java-%ED%81%90-%EA%B5%AC%ED%98%84%EC%8B%9C-ArrayDeque%EC%99%80-LinkedList-%EC%84%B1%EB%8A%A5-%EC%B0%A8%EC%9D%B4-Deque-Queue-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4
		
		// ArrayDeque으로 구현하니까 바로 통과 ~
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		connInfo = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) {
			connInfo[i] = new ArrayList<>();
		}
		cCntArr = new int[N+1];
				
		for (int i=0; i<M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st2.nextToken());
			int B = Integer.parseInt(st2.nextToken());
			
			connInfo[A].add(B); // 어떤 컴퓨터에게 해킹당할 수 있는지
			
		}
		
		for (int i=1; i<=N; i++) {
			visit = new boolean[N+1];
			bfs(i);
		}
		
		for (int i=1; i<=N; i++) {
			if (max < cCntArr[i]) {
				max = cCntArr[i];
			}
		}
		
		for (int i=1; i<=N; i++) {
			if (cCntArr[i] == max) {
				sb.append(i+" ");
			}
		}
		
		System.out.println(sb);
	}

	static void bfs(int c) {
//		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> queue = new ArrayDeque<>();

		
		queue.add(c);
		visit[c] = true;
		
		while(!queue.isEmpty()) {
			int next = queue.poll();

			for (int i : connInfo[next]) {
				if (!visit[i]) {
					queue.add(i);
					visit[i] = true;
					cCntArr[i]++; // 배열요소를 직접 ++ 하는게 더 시간이 덜걸린다 4%까지는 감....
				}
			}
		}
	}
}
