import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_이윤주 {
	/*
	 * Q. 직사각형 #문제요약 두 직사각형의 왼쪽아래, 오른쪽위꼭짓점 좌표를 주고 공통부분을 조사해서, 공통부분이 직사각형 / 선분 / 점 /
	 * 없는 경우로 나누어서 출력하기
	 *
	 * #풀이시간 : 1시간 20분
	 * #메모리/시간 : 14344kb / 124ms
	 * #메인 접근법 
	 * 1. 각 경우마다 조건을 생각하자 
	 * 1) 공통부분이 선분인 경우 -> q1 = y2 || p1 = x2 || y1 = q2 || x1 = p2 
	 * 2) 공통부분이 점인 경우 
	 * -> p1 = x2 && q1 = y2 
	 * -> p1 = x2 && q2 = y1 
	 * -> x1 = p2 && y1 = q2 
	 * -> x1 = p2 && y2 = q1 
	 * 3) 공통부분이 없는 경우 
	 * -> p1 < x2 
	 * -> p2 < x1
	 * 
	 * => 계속 틀렸습니다 떴는데 if문 순서 바꾸고, 빠진 조건 채워넣어서 성공함!
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 4줄 입력
		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 직사각형 2개 좌표 입력
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());
			
			//결과 - 선분 b, 점 c, 없음 d
			char result = '\0';
			
			if(p1 < x2 || q1 < y2 || p2 < x1 || q2 < y1) {
				//공통부분이 없는 경우
				result = 'd';
			} else if(p1 == x2 && q1 == y2) {
				//공통부분이 점인 경우
				result = 'c';
			} else if(p1 == x2 && q2 == y1) {
				//공통부분이 점인 경우
				result = 'c';
			} else if(x1 == p2 && y1 == q2) {
				//공통부분이 점인 경우
				result = 'c';
			} else if(x1 == p2 && y2 == q1 ) {
				//공통부분이 점인 경우
				result = 'c';
			}else if (q1 == y2 || p1 == x2 || y1 == q2 || x1 == p2) {
				//공통부분이 선분인 경우
				result = 'b';
			} else {
				//공통부분 직사각형인 경우
				result = 'a';
			}
			
			System.out.println(result);
		}
	}
}
