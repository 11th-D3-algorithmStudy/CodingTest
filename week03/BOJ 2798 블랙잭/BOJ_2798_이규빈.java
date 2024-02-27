import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2798_이규빈 {
	/*
	 *  Q. 블랙잭
  	 *	# 문제 요약
  	 *		N장 카드 중 3장을 고르고, 그 합이 M을 넘지 않는 한도에서 최대를 만들어야 한다.
  	 *		합이 M에 가장 가까운 카드 3장을 구하시오.
	 * 	# 풀이시간 : 30분
	 * 	# 메인 접근법
	 * 		배열을 오름차순으로 정렬하고, 앞 3장부터 차례대로 합을 계산한다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st1.nextToken());
		int M = Integer.parseInt(st1.nextToken());
		
		// 배열에 카드의 값들 저장하고 오름차순 정렬
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		Arrays.sort(arr);
		
		// 배열을 순회하며 3장 값 합산
		int res = 0;
		int sum = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				for (int k = j + 1; k < N; k++) {
					sum = arr[i] + arr[j] + arr[k];
					if (res < sum && sum <= M)  res = sum;
				}
			}
		}
		
		// 결과 출력
		System.out.println(res);
	} // 메인 메소드 종료
}