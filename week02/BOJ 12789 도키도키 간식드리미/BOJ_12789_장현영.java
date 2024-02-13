package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 간식드리미
// 무작위로 넣는 순번에 하나씩 번호를 뽑는데
// 받은 정수는 result, wait에 조건 걸어서 하나라도 못 들어가면 false 처리를 전제로 시작
// 항상 비교는 받은 정수와 wait의 peek과 비교해서 더 작은 수만 넣기
// result에는 정순, wait에는 역순으로 넣게 되는 방식
// 모든 비교를 peek으로 하기 때문에 wait, result에 값 미리 넣고 판단하기
// 순서에 해당하면 정답 stack에 넣기
// 처음엔 while문을 전체로 감싸서 출발했는데 막혀서 peek으로 접근해서 푸는게 빠르다고 판단


public class BOJ_12789_장현영 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> result = new Stack<>();
		Stack<Integer> wait = new Stack<>();
		String answer = "Nice";
		
		result.push(0);
		wait.push(-1);
		for(int i=0;i<n;i++) {
			int temp = Integer.parseInt(st.nextToken());
            // peek으로만 비교하기 때문에, 중간에 wait.peek()값이 result에 들어가야될 경우는 while문으로 해당하는 값 다 넣기
			while(result.peek()+1 == wait.peek()) {
				result.push(wait.pop());
			}
            
			if(temp == result.peek()+1) { // result에 들어갈 조건
				result.push(temp);
			}
			else if(wait.peek()== -1 || temp < wait.peek()){ // wait이 비어있거나, 받은 정수가 peek보다 작을 때
				wait.push(temp);
			}
			else { // result, wait 모두 들어갈 수 없는 상황
				answer = "Sad";
				break;
			}		
		}
		System.out.println(answer);
		
		
	}
}
