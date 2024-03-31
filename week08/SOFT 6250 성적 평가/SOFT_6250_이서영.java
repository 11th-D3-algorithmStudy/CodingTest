package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SOFT_6250_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] race1 = new int[n];
		int[] race2 = new int[n];
		int[] race3 = new int[n];
		int[] total = new int[n];

		int[] count1 = new int[1001];
		int[] count2 = new int[1001];
		int[] count3 = new int[1001];
		int[] countTotal = new int[3001];

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num1 = Integer.parseInt(st1.nextToken());
			race1[i] = num1;
			count1[num1]++;
			int num2 = Integer.parseInt(st2.nextToken());
			race2[i] = num2;
			count2[num2]++;
			int num3 = Integer.parseInt(st3.nextToken());
			race3[i] = num3;
			count3[num3]++;
			total[i] = num1 + num2 + num3;
			countTotal[total[i]]++;
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			int num = race1[i];
			int rank = 1;
			for (int j = 1000; j > num; j--) {
				rank += count1[j];
			}
			sb.append(rank + " ");
		}
		sb.append("\n");

		for (int i = 0; i < n; i++) {
			int num = race2[i];
			int rank = 1;
			for (int j = 1000; j > num; j--) {
				rank += count2[j];
			}
			sb.append(rank + " ");
		}
		sb.append("\n");

		for (int i = 0; i < n; i++) {
			int num = race3[i];
			int rank = 1;
			for (int j = 1000; j > num; j--) {
				rank += count3[j];
			}
			sb.append(rank + " ");
		}
		sb.append("\n");

		for (int i = 0; i < n; i++) {
			int num = total[i];
			int rank = 1;
			for (int j = 3000; j > num; j--) {
				rank += countTotal[j];
			}
			sb.append(rank + " ");
		}
		sb.append("\n");
		System.out.println(sb);
	}
}
