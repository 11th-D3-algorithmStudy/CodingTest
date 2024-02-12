package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 도키도키간식드리미 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 학생 수
		int N = Integer.parseInt(br.readLine());

		// 현재 줄서있는곳 : queue
		// 한 명씩 설 수 있는 공간 : stack

		// 현재 줄서있는곳
		Queue<Integer> curLine = new LinkedList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			curLine.offer(Integer.parseInt(st.nextToken()));
		}

		// 한 명씩 서는 공간
		Stack<Integer> stack = new Stack<>();

		// 줄 순서
		int turnCnt = 1; // 1번 부터~

		// 현재 줄서있는 곳이 빌 때까지
		while (!curLine.isEmpty()) {
			// 줄 설 순서가 아닌 경우 스택으로 push, 줄 설 순서인 경우 날리기 & 줄 순서++
			if (curLine.peek() == turnCnt) {
				curLine.poll();
				turnCnt++;
			} else { 
				// 스택의 마지막 요소가 줄 순서와 일치하는지도 확인 **
				if (!stack.isEmpty()) {
					if (stack.peek() == turnCnt) {
						stack.pop();
						turnCnt++;
					} else {
						stack.push(curLine.poll());
					}
				} else {
					stack.push(curLine.poll());
				}
			}
		}

		// 스택에서 꺼내지는 순서가 줄 순서와 일치하지 않으면 Sad
		while (!stack.isEmpty()) {
			if (stack.peek() == turnCnt) {
				stack.pop();
				turnCnt++;
			} else {
				System.out.println("Sad");
				break;
			}
		}

		// 줄 순서 끊기지 않고 while문 탈출
		if (turnCnt == N + 1) {
			System.out.println("Nice");
		}
	}
}
