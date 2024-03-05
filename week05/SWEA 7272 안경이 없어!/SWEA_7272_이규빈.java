package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7272_이규빈 {
	/*
	*	Q.안경이 없어
	*	# 문제 요약 : 알파벳을 구멍 개수로 판별할 때, 두 문자열의 일치여부를 판별하라.
	*	# 풀이시간 : 50분
	*	# 메모리 / 실행시간 : 100,660kb / 442ms
	*	# 메인 접근법 - Brute Force
	*		구멍이 하나도 없는 문자를 0, 하나인 문자를 1, B를 2로 치환한 후 일치여부를 판단한다.
	*		(각 문자열의 길이가 10이하이므로, 메모리나 실행시간에 무리가 없겠다고 판단함)
	*	# 막힌 부분 해결
	*		replace 메소드를 쓸때 기존 문자열에 재할당 하지 않고 "str1.replace(s, "0");"과 같이 작성
	*		-> 문자열이 변경되지 않는 문제가 발생 (replace 메소드는 기존 문자열 객체가 변경되는 것이 아니라, 새로운 객체가 생성되는 것이기 때문임을 알게 됨)
	*		-> "str1 = str1.replace(s, "0");"과 같이 변경해 해결
	*/
	
	// 일치여부 판단용 배열에 알파벳 저장
	static String[] holeZero = {"C", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "S", "T", "U", "V", "W", "X", "Y", "Z"}; // 19개
	static String[] holeOne = {"A", "D", "O", "P", "Q", "R"}; // 6개
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		next : for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			
			// Backtracking(?) - 문자열 길이가 다르면 곧바로 DIFF 출력
			if (str1.length() != str2.length()) {
				System.out.println("#" + t + " " + "DIFF");
				continue; // 다음 테스트케이스로 이동
			}
			
			// 구멍이 없는 알파벳 -> 0으로 변환
			for (String s : holeZero) {
				if (str1.contains(s))  str1 = str1.replace(s, "0");
				if (str2.contains(s))  str2 = str2.replace(s, "0");
			}
			
			// 구멍이 하나만 있는 알파벳 -> 1로 변환
			for (String s : holeOne) {
				if (str1.contains(s))  str1 = str1.replace(s, "1");
				if (str2.contains(s))  str2 = str2.replace(s, "1");
			}
			
			// B -> 2로 변환
			if (str1.contains("B"))  str1 = str1.replace("B", "2");
			if (str2.contains("B"))  str2 = str2.replace("B", "2");

			// 일치여부 판단
			// - 하나라도 다르면 바로 DIFF 출력
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) != str2.charAt(i)) {
					System.out.println("#" + t + " " + "DIFF");
					continue next; // 다음 테스트케이스로 이동
				}
			}
			
			// 이 구역에 들어왔으면 두 문자열은 같은 것
			System.out.println("#" + t + " " + "SAME");
		}
	}
}