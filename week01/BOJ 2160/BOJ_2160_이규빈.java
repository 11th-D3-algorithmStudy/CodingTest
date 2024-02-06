import java.util.Scanner;

public class BOJ_2160_이규빈 {
	/*
	 * 	BOJ 2160. 그림 비교
	 *  - X와 .으로 표현된 여러 그림 중, 다른 칸의 개수가 가장 적은 두 그림을 찾기
	 * 	- N * 35의 2차원배열로 보고 풀이함.
	 */
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();  // 개행문자 제거
		char[][] arr = new char[N][35];
		
		// 그림 정보를 N * 35 배열에 저장
		for (int r = 0; r < N; r++) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < 5; i++) {  // 5줄의 문자를 한 번에 저장
				sb.append(sc.nextLine());
			}
			
			for (int c = 0; c < 35; c++) {
				arr[r][c] = sb.charAt(c);
			}
		}
		
		
		// 그림 유사판단
		int min = 35;  // 총 칸이 35개이므로, 최소값은 무조건 35 이하이다.
		int picNum1 = 1;
		int picNum2 = 2;
		// 수정사항
		// 처음에 picNum1, picNum2를 0으로 초기화 하니, 예제는 통과하나 제출하면 틀리는 경우가 발생
		// 오래 고민하다 결국 백준 질문 게시판을 참고하니 아래와 같이 완전히 반전되는 경우 "0 0"이 나와 틀리는 거였음
		/*
		 * 		2
		 *		.......
		 *		.......
		 *		.......
		 *		.......
		 *		.......
		 *		XXXXXXX
		 *		XXXXXXX
		 *		XXXXXXX
		 *		XXXXXXX
		 *		XXXXXXX
		 */
		// 결국 초기화 값을 picNum1 = 1, picNum2 = 2로 바꾸니 통과됨
		
		for (int r = 0; r < N; r++) {
			for (int i = 1; i < N - r; i++) {  // r + i : 비교할 행의 인덱스
				int cnt = 0;
				
				for (int c = 0; c < 35; c++) {
					if (arr[r][c] != arr[r + i][c]) {
						cnt++;
					}
				}
				
				if (min > cnt) { 
					min = cnt;
					picNum1 = r + 1;  // 인덱스는 0부터 시작하고, 그림 번호는 1부터 시작하므로 1을 더한다.
					picNum2 = r + i + 1;
				}
			}
		}
		
		
		// 결과 출력
		System.out.println(picNum1 + " " + picNum2);
		  
	}

}