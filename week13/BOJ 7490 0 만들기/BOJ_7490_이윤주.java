package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7490_이윤주 {
	// 0만들기
	// 수식의 결과가 0이 되는 모든 수식을 출력
	// ascii 순서에 따라 출력
	// 공백 -> 32
	// + -> 43
	// - -> 45

	// 부분집합으로 수식만들어서 계산해서 0 되면 출력하기

	static int T, N;
	static String expression;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			makeExpression(1, "");

			System.out.println(); // 각 테케마다 한줄 띄우기
		} // tc
	}

	// 1~N까지 숫자 부분집합(공백, +, -) 넣어서 만들기
	private static void makeExpression(int n, String line) {
		// 끝까지 왔다면 계산하기
		if (n == N) {
			line += n; // 마지막 숫자 넣기
			String result = line;
			line = line.replaceAll(" ", ""); // 공백제거
			int num = 0;
			Queue<Integer> qNum = new LinkedList<>();
			Queue<Character> qChar = new LinkedList<>();

			for (int i = 0; i < line.length(); i++) {
				char now = line.charAt(i);
				if (now == '+' || now == '-') {
					qNum.offer(num);
					num = 0;
					qChar.offer(now);
				} else {
					num *= 10;
					num += now - '0';
				}
			}

			qNum.offer(num); // 마지막 숫자 넣어주기
			
//			System.out.println(qNum.toString());
//			System.out.println(qChar.toString());
			
			int sum = qNum.poll();;
			
			while (!qChar.isEmpty()) {
				char op = qChar.poll();
				if (op == '+') {
					sum += qNum.poll();
				} else { // -인 경우
					sum -= qNum.poll();
				}
			}

			if (sum == 0)
				System.out.println(result);
			
			return;
		}

		line += n; // 지금 숫자 추가
		// 공백
		makeExpression(n + 1, line + " ");
		// 플러스
		makeExpression(n + 1, line + "+");
		// 마이너스
		makeExpression(n + 1, line + "-");

	}
}
