import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_1926_이윤주 {
	/*
	 * Q. 간단한 369게임
	 * 	#문제요약
	 * 		숫자 1부터 순서대로 말하고, 3,6,9 가 들어있는 수는 - 출력
	 * 		3,6,9 가 여러개 들어있으면 들어있는 수만큼 --연속으로 출력
	 * 
	 * 	#풀이시간 : 10분
	 * 	#메모리/시간 : 18400kb / 105ms
	 * 	#메인 접근법
	 * 		1. 자리수마다 확인해야하므로, 숫자를 문자열로 바꿔서 3,6,9확인해야겠다.
	 * 		2. StringBuilder 이용해서 하나의 문자열로 출력하기.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		//1부터 n까지 숫자를 문자열로 바꿔서 자리수마다 확인해서 출력
		for(int i = 1; i <= n ; i++) {
			String num = Integer.toString(i);
			
			boolean is369 = false;
			
			//문자열의 길이만큼 각 자리수 확인
			for(int idx = 0; idx < num.length(); idx++) {
				//각 자리수가 3,6,9에 해당하는 경우
				if(num.charAt(idx) == '3' || num.charAt(idx) == '6' || num.charAt(idx) == '9') {
					sb.append("-");
					is369 = true;
				}		
			}
			if(!is369) { //369가 들어있지 않은 수라면 그냥 출력
				sb.append(num);
			}
			
			//각 숫자 사이의 공백
			sb.append(" ");
		}
		
		//출력
		System.out.println(sb);
	}
}
