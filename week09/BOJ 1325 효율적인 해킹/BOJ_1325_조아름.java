package week9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1325_조아름 {
	static int N, cnt;
	static List<Integer>[] list;
	static boolean[] visited;
	static int[] cntArr;

	public static void main(String[] args) throws IOException {
		// N 개의 컴퓨터
		// A가 B를 신뢰하는 경우 : B를 해킹하면 A도 해킹할 수 있다는 소리.

		// 입력 - A, B : A가 B를 신뢰한다.
		// 입력 첫줄 - N, M : N개의 컴퓨터, M개의 신뢰하는 줄

		// dfs 사용 -> 시간 초과 나옴.. bfs로 수정해야할 것 같은데 내일 하겠슴니다..

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1]; // 컴퓨터 개수만큼, 컴퓨터는 1번부터
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>(); // 초기화
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
		} // 그래프 연결하기.

		cntArr = new int[N + 1];
		int max = Integer.MIN_VALUE;

		// 1번부터 N번까지 search
		for (int i = 1; i < N + 1; i++) {
			visited = new boolean[N + 1]; // dfs 를 컴퓨터 개수만큼 돌려야 하므로 매번 초기화.
			dfs(i); 

		}

		for (int i = 1; i <= N; i++) {
			if (cntArr[i] > max) {
				max = cntArr[i];
			}
		}

		for (int i = 1; i <= N; i++) {
			if (cntArr[i] == max) {
				bw.write(i + " ");
			}
		}
		
		bw.flush();
		bw.close();
	}

	static void dfs(int idx) {
		visited[idx] = true; // 방문처리

		for (int i : list[idx]) {
			if (!visited[i]) {
				cntArr[i]++;
				dfs(i);
			}
		}
	}

}
