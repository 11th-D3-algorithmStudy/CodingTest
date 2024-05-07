package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942_이윤주 {
	// 팰린드롬?
	// 자연수 N개, 질문 M개
	// S~E까지 (1~N) 팰린드롬이 되는지 출력
	// 가능 1 불가능 0

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder(); // 출력값

		Q: for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()); // 시작위치
			int E = Integer.parseInt(st.nextToken()); // 끝위치
			
			int mid = (S + E) / 2;
			
			for(int left = S, right = E; left <= mid; left++, right--) {
				if(nums[left] != nums[right]) {
					sb.append(0 + "\n");
					continue Q;
				}
			}
			//여기까지 왔다는 건 팰린드롬이라는 뜻
			sb.append(1 + "\n");
		}
		System.out.println(sb);
	}
}
