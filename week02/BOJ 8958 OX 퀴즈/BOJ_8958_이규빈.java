import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8958_이규빈 {
	/*
	 * 	BOJ 8958. OX퀴즈
	 *  풀이시간 : 15분
	 *  메인 접근법 : 연속되는 O 개수를 나타내는 변수를 따로 선언
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String ox = br.readLine();

			int continuity = 0; // 연속되는 O 개수
			int score = 0;
			
			for (int i = 0; i < ox.length(); i++) {
				if (ox.charAt(i) == 'O') {
					continuity++;
					score += continuity;
				} else {
					continuity = 0; // X가 나올 경우 초기화
				}
			}
			
			// 결과 출력
			System.out.println(score);
		} // 테스트케이스 종료
	}
}