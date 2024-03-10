package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4047_이규빈 {
	/*
	 *  Q.카드 카운팅
	 *	# 문제 요약 : 부족한 카드 개수를 구하라. 단, 겹치는 카드가 있다면 오류를 출력하라.
	 *	# 풀이시간 : 40분
	 *	# 메모리 / 실행시간 : 18,840kb / 104ms
	 *	# 메인 접근법 : 4*13짜리 카운트 배열을 사용
	 *	# 막힌 부분 해결 : 처음에는 크기 4짜리 1차원 배열을 사용하려 했다가, 그러면 카드가 겹치는 경우를 처리할 수 없어 2차원 배열로 변경.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		next : for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			
			// 카드 개수를 셀 카운트 배열 (0행,0열은 쓰지 않음)
			// - 행은 S,D,H,C 순서
			int[][] cnt = new int[5][14];
			
			for (int i = 0; i <= str.length() - 3; i += 3) {
				// 카드의 무늬
				int cardPattern = 0; 
				switch (str.charAt(i)) {
					case 'S': cardPattern = 1; break;
					case 'D': cardPattern = 2; break;
					case 'H': cardPattern = 3; break;
					case 'C': cardPattern = 4; break;
				}
				
				// 카드의 번호
				int cardNum = (str.charAt(i + 1) - '0') * 10 + (str.charAt(i + 2) - '0');
				
				if (cnt[cardPattern][cardNum] != 0) { // 이미 겹치는 카드가 있는 경우
					System.out.println("#" + t + " " + "ERROR");
					continue next; // 다음 테스트케이스로 넘어감
				} else {
					cnt[cardPattern][cardNum]++;
				}
			}
			
			// 이 구역 도달시, 겹치는 카드는 없다.
			System.out.printf("#" + t);
			for (int i = 1; i <= 4; i++) {
				int lackCnt = 0; // 부족한 카드 수
				for (int j = 1; j <= 13; j++) {
					if (cnt[i][j] == 0)  lackCnt++;
				}
				System.out.printf(" " + lackCnt);
			}
			System.out.println();
		}
	}
}