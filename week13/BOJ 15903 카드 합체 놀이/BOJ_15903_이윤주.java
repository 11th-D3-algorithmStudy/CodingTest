package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15903_이윤주 {
	// 카드 합체 놀이
	// 정렬해서 가장 작은거 2개 합체하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] nums = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			Arrays.sort(nums);
			long sum = nums[0] + nums[1];
			nums[0] = sum;
			nums[1] = sum;
		}
		
		long sum = 0;
		for(long a : nums) {
			sum += a;
		}
		
		System.out.println(sum);

	}
}
