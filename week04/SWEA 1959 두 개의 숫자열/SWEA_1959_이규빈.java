import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959_이규빈 {
	/*
	 *  Q.두 개의 숫자열
	 *	# 문제 요약 : 두 숫자열의 마주보는 숫자들을 곱한 뒤 모두 더한 값의 최댓값을 구하라.
	 *	# 풀이시간 : 50분
	 *	# 메인 접근법 : 둘 중 작은 배열을 한칸씩 이동시켜 최댓값을 찾는다. (Brute force?)
	 *	# 막힌 부분 해결 : st 초기화를 반복문 안에 하는 실수 -> 찾는데 한참 걸림...
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] aArr = new int[N];
			int[] bArr = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				aArr[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bArr[i] = Integer.parseInt(st.nextToken());
			}

			// ---------------- Input End --------------------
			
			int res = Integer.MIN_VALUE;
			
			if (N <= M) {
				for (int i = 0; i <= M - N; i++) {
					int tmp = 0;
					for (int j = 0; j < aArr.length; j++) {
						tmp += aArr[j] * bArr[j + i]; 
					}
					if (res < tmp)  res = tmp;
				}
			} else {
				for (int i = 0; i <= N - M; i++) {
					int tmp = 0;
					for (int j = 0; j < bArr.length; j++) {
						tmp += aArr[j + i] * bArr[j]; 
					}
					if (res < tmp)  res = tmp;
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}
}