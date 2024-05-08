package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1421_나무꾼이다솜 {
	static long max = -1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		int C = Integer.parseInt(info[1]);
		int W = Integer.parseInt(info[2]);
		int[] woods = new int[N];
		for (int i = 0; i < N; i++) {
			woods[i] = Integer.parseInt(br.readLine());
		}
//		System.out.println(Arrays.toString(woods));
		Arrays.sort(woods);
		int maxW = woods[woods.length - 1]; // 나무중 최대 길이
		for (int i = 1; i <= maxW; i++) {
			int cuts = 0; // 전체 자른 횟수
			long worth = 0; // 전체 가격
			for (int j = 0; j < N; j++) {
				int len = woods[j]; // 현재 나무의 길이
				int eachCut = len / i; // i의 길이로 잘랐을 때 필요한 커팅횟수
				long thisWorth = W * (eachCut * i); // 해당 나무의 가격
				if ((len % i) == 0) { // 만약 나무가 i로 정확히 나누어 떨어질 때
					eachCut--; // i.e.) 길이가 4인 나무를 2의 길이로 나누려면 1번만 자르면 되지만 /로 나누면 2가 나옴
				}				// 그래서 커팅횟수 1 빼줌
				if (thisWorth < (C * eachCut)) { // 만약 자르는 비용이 해당 나무의 가격보다 크다면
					eachCut = 0;    // 자르지 않고 해당 나무를 무시하는쪽을 선택
					thisWorth = 0;
				}
				cuts += eachCut;
				worth += thisWorth;
			}
//			System.out.println("length: " + i + ", cuts: " + cuts + ", worth: " + (worth - cuts*C));
			max = Math.max((worth - cuts*C), max);
		}
		System.out.println(max);
	}
}
