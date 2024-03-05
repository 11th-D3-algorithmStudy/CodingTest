import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24511_이윤주_TimeOut {
	public static void main(String[] args) throws Exception {
		//11시 시작 - 30분에 힌트봄..
		
		//문제 이름대로 큐, 스택 사용
		//첫째줄 자료구조 개수 N
		//둘째줄 N개의 수열 -> 0 큐 / 1 스택
		//셋째줄 N개의 수열 -> i번에 들어있는 원소
		//넷째줄 삽입할 수열 길이 M
		//다섯째줄 M개의 수열 -> queuestack에 삽입할 것!!
		
		//queuestack 작동
		// 입력 -> 1번에 삽입 , 1번에서 pop -> 2번에 삽입, 2번에서 pop -> ... -> N번째 자료구조에서 pop 한 것 리턴
		
		//힌트 조금 보고 참고함 
		//https://velog.io/@gayeong39/%EB%B0%B1%EC%A4%80-24511-queuestack-JAVA
		
		//큐 -> 새거 넣고 원래 들어있던거 뱉음
		//스택 -> 새거 튕겨냄
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//첫째줄 자료구조 개수 N
		int n = Integer.parseInt(br.readLine());
		
		//0행 : 자료구조 종류 / 1행 : 데이터
		int[][] queuestack = new int[2][n];
		
		//둘째줄 N개의 수열 -> 0 큐 / 1 스택
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			queuestack[0][i] = Integer.parseInt(st.nextToken());
		}
		
		//셋째줄 N개의 수열 -> i번에 들어있는 원소
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			queuestack[1][i] = Integer.parseInt(st.nextToken());
		}
		
		//넷째줄 삽입할 수열 길이 M
		int m = Integer.parseInt(br.readLine());

		//다섯째줄 M개의 수열 -> queuestack에 삽입할 것!!
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int insert = Integer.parseInt(st.nextToken());
			//삽입할 수 하나 받아서,
			
			//각 자료구조의 리턴 저장하는 변수
			int nextX = 0;
			for(int j = 0; j < n; j++) {
				if(queuestack[0][j] == 0) {
					//j번째 자료구조가 큐이면, 기존 것 꺼내고, 새로운 것 넣기
					nextX = queuestack[1][j];
					queuestack[1][j] = insert;
					//다음 자료구조에 넣을 것 덮어씌우기
					insert = nextX;
				} else {
					//j번째 자료구조가 스택이면, 새로운 것 넘기기
					nextX = insert;
				}
			}
			//queuestack 전부 순회하고나서 리턴값 출력
			System.out.print(nextX + " ");
		} //삽입 끝		
		
		//시간초과 나는데 어떡하지..?
		
	}
}
