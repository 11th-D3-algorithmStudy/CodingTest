package week08;

import java.io.*;
import java.util.*;

public class BOJ_14888_장현영 {
	
	// 연산자 끼워맞추기
	// 처음엔 순열 써서 접근해야하나 고민
	// 답안 코드 보면서 값 수정
	// dfs부분 switch로 접근해서 문제푸는 방법 블로그 참고
	
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static List<Integer> arr = new ArrayList<>();
	static int[] operator = new int[4];

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		// arr는 받는 값이 유동적이라 list로 접근하기 
		while(st1.hasMoreTokens()) {
			arr.add(Integer.parseInt(st1.nextToken()));
		}
		
		for(int i = 0 ; i<4;i++) {
			operator[i] = Integer.parseInt(st2.nextToken());
		}

		dfs(arr.get(0), 1);
		
		System.out.println(max);
		System.out.println(min);
		
}
	public static void dfs(int a, int depth) {
		
		//base
		if(depth == N) {
			max = Math.max(max, a);
			min = Math.min(min, a);
			return;
		}
		
		// 4개 연산자 개수 확인하면서 backtracking 진행
		for(int i = 0 ; i < 4 ; i++) {
			if(operator[i] > 0) {
				operator[i] = operator[i]-1;
				switch(i) {
					case 0:dfs(a+arr.get(depth), depth+1);	break;
					case 1:dfs(a-arr.get(depth), depth+1);	break;
					case 2:dfs(a*arr.get(depth), depth+1);	break;
					case 3:dfs(a/arr.get(depth), depth+1);	break;
				}
				
				operator[i] = operator[i]+1;
			}
		}	
	}
}
