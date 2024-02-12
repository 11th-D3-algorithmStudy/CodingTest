package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OX퀴즈 {
	public static void main(String[] args) throws IOException {
		// OX퀴즈 결과를 하나씩 배열에 넣고
		// 배열을 돌면서 연속 정답인 경우 추가점수++
		// 연속 정답 아닌 경우 추가점수 1로 초기화
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<T; i++) {
			// OX퀴즈 결과 배열
			char[] resArr = br.readLine().toCharArray();
			
			// 총점
			int totScore = 0;
			
			// 맞출때 추가되는 점수 변수
			int addScore = 1;
			
			for (int j=0; j<resArr.length; j++) {
				if (resArr[j] == 'O') {
					// 인덱스 범위 조건 & 직전에 문제를 맞췄는지 확인
					if ((j-1) >= 0 && resArr[j-1] == 'O') {
						addScore++;
						totScore += addScore;
					} else { // 연속 정답 아닌경우
						addScore = 1;
						totScore += addScore;
					}
				}
			}
			sb.append(totScore+"\n");
		}
		System.out.println(sb);
	}
}
