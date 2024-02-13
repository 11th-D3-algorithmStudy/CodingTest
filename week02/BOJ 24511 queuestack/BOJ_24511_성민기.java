import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_24511 {
	public static void main(String[] args) throws IOException {
		
		/* 풀이시간 : (Retry) 240213 15:00 ~ 16:23
		 * 메인 접근법 
		 *    - 예시를 보았을 때 큐일 경우에만 수가 활용되는 것을 확인
		 *    - 스택일 경우에는 신경쓰지 않음
		 *    - 처음 부분에 큐를 활용하여 하고자 하였음 
		 *    		-> 순서대로 들어갔다가 순서대로 나오는 것이 아니기 때문에 활용X
		 *          -> 따라서 Deque를 사용
		 * 
		 * 막혔던 부분
		 * 	  - 처음 보자마자는 큐와 스택을 동시에 사용하여 활용해야 가능하지 않나 생각함 -> 구현 실패
		 *    - 순서대로 넣기 때문에 큐를 활용하기 위해서는 입력값들을 정렬해도 되지 않을까? 
		 *    		-> 어떤 값이 테스트케이스가 될지는 모르기 때문에 X
		 * 해결 방법 : 문제가 이해되지 않아 넘긴 후 며칠 뒤 풀이한 사람의 풀이를 확인 후 본인 생각 적용
		 * 
		 * 메모리 : 70120 kb
		 * 시간 : 740 ms
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		// 수열 A, B를 담을 배열 생성
		// 수열 A : 큐 or 스택      |     수열 B : 자료구조 원소
		int[] A = new int[N];
		int[] B = new int[N];
		Deque<Integer> queuestack = new LinkedList<>();
		
		// 각각 값을 입력 후
		// A 배열의 원소 중 0인 값이 있으면 큐에 해당하므로
		// 덱의 뒤쪽부터 삽입해준다
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st1.nextToken());
			B[i] = Integer.parseInt(st2.nextToken());
			if(A[i] == 0) {
				queuestack.offerLast(B[i]); // 뒤쪽부터 넣어줘야
			}
		}
		
		// 삽입할 크기 M, M크기의 배열 C를 선언
		int M = Integer.parseInt(br.readLine());
		int[] C = new int[M];
		
		// C 배열에 삽입할 원소값 입력
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			C[i] = Integer.parseInt(st3.nextToken());
		}
		
		// 덱의 앞부분에 C의 원소를 넣는다
		// 덱의 뒷부분의 원소를 빼주며 그 값을 sb에 쌓는다.
		for(int i=0; i<M; i++) {
			queuestack.offerFirst(C[i]);
			sb.append(queuestack.pollLast() + " ");
		}
		
		System.out.println(sb);

	}
}
