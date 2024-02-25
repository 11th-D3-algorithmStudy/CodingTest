import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959_이윤주 {
	/*
	 * Q. 두 개의 숫자열
	 * 	#문제요약
	 * 		N개 숫자열 A, M개 숫자열 B를 시작위치를 다르게 해서
	 * 		마주보는 숫자들을 곱해서 모두 더할 때 최댓값 구하기
	 * 	#풀이시간 : 10분
	 * 	#메모리/시간 : 18892kb / 103ms
	 * 	#메인 접근법
	 * 		1. N과 M중 더 큰 값을 기준으로 한칸씩 옮겨가면서 곱 더한값 최댓갑 찾기
	 * 		2. 완전탐색? 브루트포스
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트케이스
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//숫자열의 길이
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] numA = new int[n];
			int[] numB = new int[m];
			
			//각 숫자열 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				numA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				numB[i] = Integer.parseInt(st.nextToken());
			}
			
			//최댓값
			int max = Integer.MIN_VALUE;
			
			// N과 M 중에 더 긴 숫자열을 기준으로 짧은 숫자열을 옮길 것임
			if(n >= m) { 
				for(int i = 0; i <= n - m; i++) {
					int sum = 0;
					//앞에서부터 한칸씩 옮겨가면서 곱의 합들을 구해서 최댓값 찾기
					for(int j = i; j < i + m; j++) { //짧은 숫자열이 이동함!
						sum += numA[j] * numB[j - i];
					}
					max = Math.max(max, sum);
				}
			} else { // M이 더 긴 경우
				for(int i = 0; i <= m - n; i++) {
					int sum = 0;
					//앞에서부터 한칸씩 옮겨가면서 곱의 합들을 구해서 최댓값 찾기
					for(int j = i; j < i + n; j++) { //짧은 숫자열이 이동함!
						sum += numA[j - i] * numB[j];
					}
					max = Math.max(max, sum);
				}
			}

			//출력
			System.out.println("#" + tc + " " + max);
			
		} //테스트케이스 끝

	}
}
