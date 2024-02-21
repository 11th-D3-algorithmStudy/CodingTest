import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2567_이규빈 {
	/*
	 *  Q. 색종이-2
	 *	# 문제 요약
	 *		100*100 흰 도화지에 10*10 검은 색종이를 붙인다.
	 *		색종이들의 좌하단 점의 좌표가 주어진다.
	 *		검은 영역의 둘레 길이를 구하시오.
	 *	# 풀이시간 : 2시간
	 *	# 메인 접근법
	 *		1. 흰 도화지를 0이 채워진 100*100 2차원배열로 보고, 색종이를 붙인 영역에 1을 채운다.
	 *		2. 배열을 순회하며 1을 만났을 때, "가장자리 조건"에 만족하면 둘레 +1 한다.
	 *  # 막힌 부분 해결
	 *  	둘레를 어떻게 구할지 고민하느라 많은 시간 소요
	 *  	-> 2차원 배열로 보고, 둘레를 "0과 1의 경계선 개수"로 생각해 풀어냄
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 색종이 수
		
		// 흰 도화지
		int[][] arr = new int[100][100];
		
		// 검은 색종이 붙이기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 좌하단 점의 x좌표
			int b = Integer.parseInt(st.nextToken()); // 좌하단 점의 y좌표
			
			// 색종이에 해당하는 면적을 1로 채운다.
			for (int x = a; x < a + 10; x++) {
				for (int y = b; y < b + 10; y++) {
					if (arr[x][y] == 0)  arr[x][y] = 1; 
				}
			}
		}
		
		// 검은 영역의 둘레 재기
		int res = 0;
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				if (arr[x][y] == 1) {
					if (x != 0 && x != 99) {
						// "가장자리 조건" - 특정 x 값에서 y축 방향으로 순회하는 경우를 상상해보자.
						// 왼쪽 또는 오른쪽 요소가 0이라면, 둘레 +1
						if (arr[x - 1][y] == 0 || arr[x + 1][y] == 0)  res++;
					}
					
					if (y != 0 && y != 99) {
						// 아래 또는 위 요소가 0이라면, 둘레 +1
						if (arr[x][y - 1] == 0 || arr[x][y + 1] == 0)  res++;
					}
					
					// 반례 회피 - 검은 영역이 도화지 가장자리에 붙은 경우
					// - 이 경우 무조건 검은 영역의 가장자리이다.
					if (x == 0 || x == 99)  res++;
					if (y == 0 || y == 99)  res++;
				}
			}
		}
		
		// 결과 출력
		System.out.println(res);
	}
}