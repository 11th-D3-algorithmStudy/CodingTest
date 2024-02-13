package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_24511_장현영_fail {
	
	// queuestack
	// 자료구조 N개
	// 0,1 큐 스택
	// 초기 원소 m 개수만큼 답 출력
	// 시간초과 풀이
	// 다시 풀어보기
	
	public static void main(String[] args) throws IOException{
		//stack일때 queue일때 구분 지어서 반환하기
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int[] queuestack = new int[n];
		int[] arr = new int[n];
		
		// queuestack 여부 확인해줌
		for(int i=0; i<n;i++)	
			queuestack[i] = Integer.parseInt(st.nextToken());
		
		// 원소가 들어있는 arr
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++)	
			arr[i] = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int t=0; t<m;t++) { // test case	
			// x는 다음으로 넘겨주는 원소를 의미
			int x = Integer.parseInt(st.nextToken());
			for(int idx=0; idx<n; idx++) {
				if(queuestack[idx] == 0) { // queue라면 기존 값을 넘겨주기
					int queueReturn = arr[idx]; // 리턴해야할 임시 저장 
					arr[idx] = x; // 들어온 값을 새로 넣고
					x = queueReturn;  // x에 queue리턴 update
				}
//				else if (queuestack[idx] == 1) {
//					continue;
//				}
			}
			System.out.println(Arrays.toString(arr));
			sb.append(x+" ");
		}
		
		System.out.println(sb);
		
		
		
		
		
		
		
		
	}
	
}
