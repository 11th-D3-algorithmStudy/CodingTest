package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기_이규빈 { // 풀다 실패해서 답안 참고해서 학습함
	static int N;
	static int[] number; // 숫자
	static int[] operator; // 연산자 개수
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		number = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		// ------------------ INPUT END ----------------------
		
		dfs(number[0], 1);
		
		System.out.println(max);
		System.out.println(min);
	}

	/**
	 * @param num: 연산자 실행 결과를 저장 (누적적으로 계산해야 하므로 매개변수로 넘겨주는 것이 좋다)
	 * @param idx: number 배열의 인덱스 (= 재귀의 깊이)
	 * 
	 * # 본 메서드의 작동원리
	 *  - 상태 공간 트리를 생각해보면, for문에 의해 "쿼드 트리"와 같은 형태가 된다.
	 *  - 그 내부의 if문을 통해, 연산자 개수가 남아있을 떄만 다음 재귀호출을 진행한다.
	 *  - 재귀호출을 반복하다가 깊이가 N에 도달해 모든 연산을 마친 경우, 계산값을 리턴한다.
	 *  - Backtrack 되어 한 단계 올라온 경우, 연산자 개수를 복구시켜줘야 한다.
	 */
	private static void dfs(int num, int idx) {
		// base case
		if (idx == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		
		// recursive case
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i]--;
				
				switch (i) {
				case 0: dfs(num + number[idx], idx + 1);  break;
				case 1: dfs(num - number[idx], idx + 1);  break;
				case 2: dfs(num * number[idx], idx + 1);  break;
				case 3: dfs(num / number[idx], idx + 1);  break;
				}
				
				// Backtrack 되어 한 단계 올라온 경우, 연산자 개수 복구
				operator[i]++;
			}
		}
	}
}
