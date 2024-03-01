package another;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/*
		 * 풀이시간 : 240227 23:03 ~ 23:33
		 * 메인접근법 
		 *      - 패턴매칭 방법과 유사하게 풀이함
		 *      - 패턴매칭은 큰 것과 작은 것을 알고 있는 상태에서 진행하기 때문에
		 *      - 범위는 abs로 하여 처리하였고, 더 작은 것을 Math.min으로 구하였다.
		 * 막힌부분
		 *      - 패턴매칭처럼 풀이를 하니 어떤 것이 큰 것인지 구별 못했었음
		 * 해결방법 : 메인접근법처럼 abs, min을 통해 해결
		 * 
		 * 메모리 : 19,988 kb
		 * 시간 : 112 ms
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] A = new int[N];
			int[] B = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			// 패턴매칭 활용
			int max = Integer.MIN_VALUE;
			// Math.abs(M-N)을 통해 배열 비교할 범위를 구함
			for(int i=0; i<=Math.abs(M-N); i++) {
				int sum = 0;
				// 더 작은 것을 움직이며 큰 것과 비교하기 위해 Math.min 사용
				for(int j=0; j<Math.min(M,N); j++) {
					if(N<=M) sum += B[i+j] * A[j];
					else if(N>M) sum += A[i+j] * B[j];
				}
				if(max < sum) {
					max = sum;
				}
			}
			sb.append("#" + t + " " + max);
		}
		System.out.println(sb);
	}
}
