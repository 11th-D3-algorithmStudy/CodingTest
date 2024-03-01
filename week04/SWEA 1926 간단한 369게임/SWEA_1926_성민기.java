package another;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1926 {
	public static void main(String[] args) throws IOException {
		
		/*
		 * 풀이시간 : 230227 23:38 ~ 00:01
		 * 메인접근법 
		 * 	  - 입력값 N만큼 반복하며 정수를 뽑아낸다.
		 *    - 그 정수로 String으로 변환 후 String의 크기만큼 charAt을 구하는 것을 반복하여
		 *    - charAt의 결과가 3, 6, 9이면 비어있는 String값인 line에 더해준다.
		 *    - 이러면 36과 같이 두개에 해당된다고 하더라도 두개 다 더해줄 수 있다.
		 *    - line값이 '--' 이거나 '-'이면 line을 출력하고 아니면 기존 number값을 출력한다.
		 * 
		 * 막힌부분 : 딱히 없었음
		 * 메모리 : 18,224 kb
		 * 실행시간 : 109 ms
		 */
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		
		for(int t=1; t<=N; t++) {
			String number = Integer.toString(t);
			String line = "";
			for(int i=0; i<number.length(); i++) {
				char c = number.charAt(i);
				if(c=='3' || c=='6' || c=='9') line += '-';
			}
			if(line.equals("--") || line.equals("-")) {
				System.out.print(line + " ");
			} else {
				System.out.print(number + " ");
			}
		}
	}
}
