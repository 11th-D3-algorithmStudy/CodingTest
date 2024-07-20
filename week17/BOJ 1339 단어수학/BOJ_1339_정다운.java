package week17;

import java.io.*;
import java.util.*;

public class 단어수학 {

	static int N;
	static int[] alpArr; 
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		alpArr = new int[26]; // 길이 : 알파벳 수
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<input.length(); j++) {
				alpArr[input.charAt(j) - 'A'] += Math.pow(10, input.length()-j-1);
			}
		}
		
		Arrays.sort(alpArr);
		
		int mult = 9; // 가장 많이 사용되는 알파벳에 9부터 곱하기
		for (int i=25; i>=0; i--) {
			max += alpArr[i]*mult;
			mult--;
			if (mult == 0) break;
		}
		
		System.out.println(max);
	}
}
