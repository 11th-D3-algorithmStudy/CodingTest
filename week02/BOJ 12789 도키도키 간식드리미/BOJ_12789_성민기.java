import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789_성민기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		// 서있는 사람의 번호 입력
		Queue<Integer> order = new LinkedList<>();
		
		// 설 수 있는 공간
		Stack<Integer> wait = new Stack<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			order.offer(Integer.parseInt(st.nextToken()));
		}

		int rank = 1;
		while(!order.isEmpty()) {
			
			/* 처음 생각했던 부분
			if(rank != order.peek()) {
				wait.push(order.poll());
			} else {
				order.poll();
				rank++;
			}
			 */
			
			// 차례이면 큐에서 값 제거 후 rank++
			if(rank == order.peek()) {
				order.poll();
				rank++;
			} else if(!wait.isEmpty() && wait.peek() == rank) {
			// 설 수 있는 공간이 비어있지 않으면서 차례이면 스택에서 값 제거 및 rank++; 
			// 반례 확인 : 2 1 3 
			// 다음 반례 순서로 하였을 때 스택에 들어간 2와 큐에 남아있는 3을 비교하여 
			// 큐와 스택을 동시에 확인하며 차례를 비교해야하기 때문	
				wait.pop();
				rank++;
			} else {
			// 차례가 아니면 wait 스택에 값 삽입
				wait.push(order.poll());
			}
		}
		// 큐 비었을 때의 rank값 이어서 사용

		String mood = "Nice";
		// 스택이 비지않을 때까지 반복
		while(!wait.isEmpty()) {
			// 랭크값이 스택의 마지막 값과 같다면 pop 후 rank++
			if(rank == wait.peek()) {
				wait.pop();
				rank++;
			} else {
			// 스택의 값과 rank가 같지 않다면 Sad 후 break
				mood = "Sad";
				break;
			}
		}
		System.out.println(mood);
	}
}
