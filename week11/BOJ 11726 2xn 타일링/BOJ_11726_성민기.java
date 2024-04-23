package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_11726_성민기 {
	
	/* 풀이시간 : 240414 20:00 ~ 21:05
	 * 메인접근법 : 
	 *       - 타일을 어떻게 더하는지가 중요
	 *       - ※1부터 N-1 크기에 직사각형과 관련된 경우의 수를 모두 구해놓았다고 가정
	 *       - N-1과 N-2에서 만들 수 있는 
	 *       	타일의 경우의 수를 더하면 N 길이에서의 경우의 수를 구할 수 있음.
	 *       	(어떻게 이런 생각을 하지...)
	 * 막힌 부분 : 진짜 모르겠어서 정답 찾아보았음
	 * 메모리 : 14268 KB, 시간 : 124 ms
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 여기서 인덱스 에러 났었음 -> N+1을 크기로 한 것이 아닌 1001로 하니까 해결됨
		int[] DP = new int[1001];
		DP[1] = 1;
		DP[2] = 2;
		for(int i=3; i<=N; i++) {
			// 여기서 10007나눠줘야 안틀림 
			DP[i] = (DP[i-1] + DP[i-2]) % 10007;
		}
//		System.out.println(Arrays.toString(DP));
		// 여기서 DP[N] % 10007 했다가 틀렸었음
		System.out.println(DP[N]);
	}
}
