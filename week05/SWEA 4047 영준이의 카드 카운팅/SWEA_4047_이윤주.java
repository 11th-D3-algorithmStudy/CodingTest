import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_4047_이윤주 {
	/*
	 * Q. 영준이의 카드 카운팅 #문제요약 스페이드, 다이아몬드, 하트, 클로버 무늬별로 1~13 숫자 카드 총 52장 이미 가지고 있는 카드가
	 * 있고 게임을 하기 위해서 몇 장의 카드가 더 필요한지? 겹치는 카드를 가지고 있다면 오류 출력 입력 : 카드무늬+카드숫자(01~13) 의
	 * 3자리가 연결된 문자열 출력 : 무늬 별로 부족한 카드 수 or 이미 겹치는 카드가 있으면 ERROR 출력
	 * 
	 * #풀이시간 : 20분
	 * #메모리/시간 : 19684kb / 111ms
	 * #메인접근법 
	 * 1. 문자열에서 3글자씩 나눠서 카드 수 세기 
	 * 2. 2장 나오는 카드가 있다면 ERROR 출력
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 부족한 카드 수 저장
			int S = 13;
			int D = 13;
			int H = 13;
			int C = 13;

			boolean[] Scard = new boolean[14];
			boolean[] Dcard = new boolean[14];
			boolean[] Hcard = new boolean[14];
			boolean[] Ccard = new boolean[14];
			
			//중복 플래그
			boolean error = false;

			String cards = br.readLine();

			// 3자리씩 나눠서 확인
			out: for (int i = 0; i < cards.length(); i = i + 3) {
				char shape = cards.charAt(i);
				int num10 = cards.charAt(i + 1) - '0';
				int num1 = cards.charAt(i + 2) - '0';
				int num;

				if (num10 == 0) { // 숫자 첫자리 0인 경우
					num = num1;
				} else {
					num = (num10 * 10) + num1;
				}

				switch (shape) {
				case 'S':
					if(Scard[num]) {
						error = true;
						break out;
					}
					Scard[num] = true;
					S--;
					break;
				case 'D':
					if(Dcard[num]) {
						error = true;
						break out;
					}
					Dcard[num] = true;
					D--;
					break;
				case 'H':
					if(Hcard[num]) {
						error = true;
						break out;
					}
					Hcard[num] = true;
					H--;
					break;
				case 'C':
					if(Ccard[num]) {
						error = true;
						break out;
					}
					Ccard[num] = true;
					C--;
					break;
				}
			}
			
			if(error) {
				System.out.printf("#%d ERROR\n", tc);
				continue;
			}

			System.out.printf("#%d %d %d %d %d\n", tc, S, D, H, C);
		} // 테스트케이스 끝
	}

}
