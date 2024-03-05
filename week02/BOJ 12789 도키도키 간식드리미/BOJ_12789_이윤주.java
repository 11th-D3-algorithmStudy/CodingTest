import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789_이윤주 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 큐를 이용하자? 스택?
		// 현재 줄 서있는 곳 -> 큐
		Queue<Integer> nowQ = new LinkedList<>();

		// 순서대로만 들어갈 수 있는 라인 -> 큐
		Queue<Integer> snackQ = new LinkedList<>();

		// 한명씩만 설 수 있는 공간 -> 스택
		Stack<Integer> stack = new Stack<>();

		// 학생들의 수 n 입력받기
		int n = Integer.parseInt(br.readLine());
		// n 개의 번호표 입력받기 -> 현재 줄 서있는 큐에 넣기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			nowQ.offer(num);
		}

		// 현재 차례를 알려주는 변수
		int order = 1;

		// 결과를 저장하는 변수
		String result = "Nice";
		
		
		while(true) {
			
			if(nowQ.isEmpty() && stack.isEmpty()) {
				//현재큐, 스택 둘 다 비어 있는 경우 -> 모두 차례대로 스낵큐에 넣음!! -> Nice
				break;
			} else if(nowQ.isEmpty() && stack.peek() == order) {
				//현재큐가 비어있고, 스택의 맨위에 현재 차례가 있는 경우 -> 스낵큐에 넣어
				snackQ.offer(stack.pop());
				order++;
			} else if(nowQ.isEmpty() && stack.peek() != order) {
				//현재큐가 비어있고, 스택의 맨위에 현재 차례가 없는 경우 -> Sad
				result = "Sad";
				break;
			} else if(nowQ.peek() == order) {
				//현재큐에 현재 차례가 있는 경우 -> 스낵큐에 넣어
				snackQ.offer(nowQ.poll());
				order++;
			} else if(!stack.isEmpty() && stack.peek() == order) {
				//스택 맨 위에 현재 차례가 있는 경우 -> 스낵큐에 넣어
				snackQ.offer(stack.pop());
				order++;
			} else {
				//현재큐, 스택 맨 위에 다른 차례가 있는 경우 -> 현재 큐에서 하나 스택에 쌓기
				stack.push(nowQ.poll());
			} 
		}
		
		System.out.println(result);
		
		//else if(nowQ.peek() == order) -> 이 구문에서 NullPointerException 발생
		//해결 방법 : if문 위치를 바꿔서 nowQ.isEmpty로 위에서 다 거르도록 하니까 해결됨
		
	}
}