package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_7490_이서영{
	static char[] operators = { ' ', '+', '-' };
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			int[] og = new int[N];
			for (int j = 0; j < N; j++) {
				og[j] = j + 1;
			}
			char[] ops = new char[N - 1];
			perm(og, ops, 0);
			System.out.println();
		}
	}

	public static void perm(int[] og, char[] ops, int depth) {
		if (depth >= N - 1) {
//			System.out.println(Arrays.toString(ops));
			eval(og, ops);
			return;
		}
		for (int i = 0; i < operators.length; i++) {
			char op = operators[i];
			ops[depth] = op;
			perm(og, ops, depth + 1);
		}
	}

	static void eval(int[] og, char[] ops) {
		int idx = 0;
		int num = og[idx];
		while (idx < N - 1) {
			char op = ops[idx];
			int jump = 1;
			int res = og[++idx];
			while (idx < N - 1 && ops[idx] == ' ') {
				res = res * 10 + og[++idx];
				jump++;
			}

			if (op == '-') {
				num -= res;
			} else if (op == '+') {
				num += res;
			} else if (op == ' ') {
				num = (int) (num * Math.pow(10, jump) + res);
			}
		}
		if (num == 0) {
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < N; k++) {
				sb.append(og[k]);
				if (k < N - 1) {
					sb.append(ops[k]);
				}
			}
			System.out.println(sb);
//			System.out.println(num);
		}
	}
}
