package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798_정다운 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer info = new StringTokenizer(br.readLine());
		
		// 카드 개수
		int N = Integer.parseInt(info.nextToken());
		// 합은 M을 넘지 않아야 한다
		int M = Integer.parseInt(info.nextToken());
		
		StringTokenizer cards = new StringTokenizer(br.readLine());
		
		// N개의 카드 저장 배열
		int[] cardArr = new int[N];
		for (int i=0; i<N; i++) {
			cardArr[i] = Integer.parseInt(cards.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		// 카드 3개의 합 경우의 수 모두 구하기 & 최대값 업데이트
		for (int a=0; a<N; a++) {
			for (int b=a+1; b<N; b++) {
				for (int c=b+1; c<N; c++) {
					int sum = cardArr[a] + cardArr[b] + cardArr[c];
					if (sum <= M && sum > max) {
						max = sum;
					}
				}
			}
		}
		
		System.out.println(max);
	}
}

