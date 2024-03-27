package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class BOJ_14888_성민기 {
	
	/* 풀이시간 : 240324 15:00 ~ 16:32
	 * 메인접근법 : 백트래킹을 어떻게 넣어줘야할지 생각하였음
	 * 
	 * 막힌 부분
	 *     - 생각의 접근을 못함 -> 리턴조건부터 생각하기
	 *     - 추가로 막힌부분은 메소드에 주석으로 작성
	 * 
	 * 메모리 : 14348 KB / 시간 : 128 ms
	 */
	
	static int[] arr;
	static int[] operation;
	static int N, min, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		operation = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operation[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		BACK(1, arr[0]);
		System.out.println(max);
		System.out.println(min);
		
	}
	
	public static void BACK(int idx, int result) {
		// 기저조건
		if(idx==N) {
			min = Math.min(result, min);
			max = Math.max(result, max);
			return;
		}
		
		for(int j=0; j<4; j++) {
			
			// 처음에는 result += arr[idx]로 하였으나
			// 값을 따로 선언하지않고 그대로 사용한다면,
			// 재귀 호출을 통해 다른 분기로 이동할 때 이전 분기에서의 연산 결과가 유지되지 않고
			// 덮어씌워지게 된다. -> 잘못된 결과 초래
			// 따라서 newResult 선언
			int newResult = 0;

			if(operation[j]>0) {
				if(j==0) newResult = result + arr[idx];
				else if(j==1) newResult = result - arr[idx];
				else if(j==2) newResult = result * arr[idx];
				else if(j==3) newResult = result / arr[idx];
				operation[j]--;
				BACK(idx+1, newResult);	
				operation[j]++; // 백트래킹
			}
		}
	}
}
