package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_2579_성민기 {
	
	/* 풀이시간 : 240407 16:55 ~ 18:20
	 * 메인접근법 
	 *    - Bottom-Up 방식
	 *    - 계단을 오르는 방식은 2가지
	 *        1. n-3 => n-1 => n
	 *        2. n-2 => n
	 *    - 마지막 계단은 무조건 밟아야하므로 두 가지 중 큰값 + 현재 계단을 더한 값
	 * 
	 * 막힌 부분 : DP가 어려워서 답지를 봄(전혀 모르겠었음)
	 * 메모리 : 14232 KB, 시간 : 128 ms
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] dp = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = arr[1];
		// N이 1이 입력될 수도 있기 때문에 예외처리 필요
		// if문 없이 그냥 하게 되면 런타임 에러 (ArrayIndexOutOfBounds) 발생
		if(N>=2) dp[2] = arr[1] + arr[2];
		
		// 마지막 계단은 무조건 밟아야하므로 두 가지 중 큰값 + 현재 계단을 더한 값
		for(int i=3; i<N+1; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3]+arr[i-1]) + arr[i];
		}
		
		// 최종 계단
		System.out.println(dp[N]);
	}
}
