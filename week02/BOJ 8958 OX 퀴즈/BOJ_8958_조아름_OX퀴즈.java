package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8958_조아름_OX퀴즈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<tc;i++) {
			String s = br.readLine();
			
			int cnt = 0; // 연속횟수
			int sum = 0; // 누적합
			
			for(int j=0;j<s.length();j++) {
				if(s.charAt(j)=='O') {
					cnt++;
					sum+=cnt;
				}else {
					cnt = 0; // 점수 초기화
				}
			}
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}

}
