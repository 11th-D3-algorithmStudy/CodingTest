import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1926_이규빈 {
	/*
	 *  Q.간단한 369게임
	 *	# 문제 요약
	 *		1부터 입력한 N까지 369 게임 규칙에 맞게 출력한다. 박수는 횟수에 맞게 -를 출력한다.
	 *	# 풀이시간 : 50분
	 *	# 메인 접근법 
	 *		숫자를 valueOf 메소드로 문자열로 변환해, 3,6,9 개수를 세서 "-"로 바꿔주기
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			String strNum = String.valueOf(i);
			for (int j = 0; j < strNum.length(); j++) {
				if (strNum.charAt(j) == '3' || strNum.charAt(j) == '6' || strNum.charAt(j) == '9') {
					sb.append("-");
					cnt++;
				}
			}
			if (cnt == 0) {
				sb.append(strNum);
			}
			sb.append(" ");
		}
		
		System.out.println(sb);
	}

}
