package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_이서영 {
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int plus = Integer.parseInt(st.nextToken());
		int minus = Integer.parseInt(st.nextToken());
		int product = Integer.parseInt(st.nextToken());
		int div = Integer.parseInt(st.nextToken());
//		int[] operators = {plus, minus, product, modulo};
		evaluate(nums, 1, plus, minus, product, div, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void evaluate(int[] nums, int idx, int p, int m, int pr, int div, int res) {
		if (idx == nums.length) {
			min = Math.min(res, min);
			max = Math.max(max, res);
			return;
		}
		
		if (p > 0) {
			evaluate(nums, idx + 1, p - 1, m, pr, div, res + nums[idx]);
		}
		if (m > 0) {
			evaluate(nums, idx + 1, p, m - 1, pr, div, res - nums[idx]);
		}
		if (pr > 0) {
			evaluate(nums, idx + 1, p, m, pr - 1, div, res * nums[idx]);
		}
		if (div > 0) {
			evaluate(nums, idx + 1, p, m, pr, div - 1, res / nums[idx]);
		}
	}
}
