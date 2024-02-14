

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789_이규빈_FAIL {
	/*
	 *  풀이시간 : 3시간
	 *  메인 접근법 : 먼저 Queue의 학생들을 스택 또는 간식줄로 내보내 큐를 비워낸 다음, Stack이 비워지는지 여부를 판단하기
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 줄서있는 학생들을 큐에 저장 (FIFO)
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			queue.offer(Integer.parseInt(st.nextToken()));
		}
		
		
		// 왼쪽 공간을 나타내는 스택 선언 (FILO)
		Stack<Integer> stack = new Stack<>();
		
		
		// 1. "큐"에서 순서대로 찾기
		int lastOut = 0;
		
		for (int i = 1; !queue.isEmpty() && i <= N; i++) {
			while (!queue.isEmpty() && queue.peek() != i) { // 순번에 맞지 않는 학생 - 스택으로 내보내기
				stack.push(queue.poll()); // 큐에서 빼서 스택에 넣기
			}
			
			if (!queue.isEmpty() && queue.peek() == i) { // 순번에 맞는 학생 - 간식줄로 내보내기
				queue.poll();
				lastOut = i; // 반복문이 종료되는 경우, lastOut에는 마지막으로 나간 학생의 순번이 저장됨.
			}
		} // 큐가 모두 비워짐
		
		
		// 원래 코드
		// 막힌 부분 : 5 4 1 3 2의 경우, 5,4를 스택으로 내보내고 1이 간식줄로 내보내지지 않아져서 큐가 비워지지 않는 경우가 발생
		// 해결방법 : 순번에 맞지 않는 학생을 만난 경우 먼저 비워내고 (while문), 맞는 학생을 만난 경우를 위와 같이 따로 분리해 (if문) 큐가 전부 비워지도록 수정
//		for (int i = 1; !queue.isEmpty() && i <= N; i++) {
//			if (queue.peek() != i) {
//				while (!queue.isEmpty() && queue.peek() != i) {
//					stack.push(queue.poll());
//				}
//			} else {
//				queue.poll();
//				lastOut = i;
//			}
//		}
		
		
		// 2. "스택"에서 나머지 번호들 순서대로 찾기
		if (lastOut < N) { // 위 단계에서 모두 간식줄로 나간 경우가 아닌 경우
			for (int i = lastOut + 1; i <= N; i++) {
				if (stack.peek() != i) { // 순번에 맞지 않는 학생이 top인 경우 - 간식 받기 불가
					break;
				} else { // 순번에 맞는 학생 - 간식줄로 내보내기
					stack.pop();
				}
			}
		}
		
		// 결과 출력
		// - 스택이 비어있는 경우, 앞 학생들이 모두 간식을 받아갔으므로, 승환이도 간식 받을 수 있어 "Nice"
		// - 스택에 학생이 남아있는 경우, 승환이는 간식 받을 수 없으므로 "Sad"
		if (stack.isEmpty()) {
			System.out.println("Nice");
		} else {
			System.out.println("Sad");
		}
	}
}
