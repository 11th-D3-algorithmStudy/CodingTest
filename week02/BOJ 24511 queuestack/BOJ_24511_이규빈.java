import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_24511_이규빈 {
	/*	
	 * 	BOJ 24511. queuestack
	 * 	풀이시간 : 50분
	 * 	메인 접근법
	 * 		1. 스택은 들어갔던 숫자가 그대로 나오므로, 없는 것처럼 취급한다.
	 * 		2. 따라서 리턴값은 기존에 큐들에 들어있는 숫자들을 뒤부터 출력하고, 이후 수열 C의 원소들을 출력하면 된다. (단 출력값은 M개)
	 *  막힌 부분 : 시간초과
	 *  해결방법 : System.out.println을 BufferedWriter로 변경
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 자료구조 개수 N
		int N = Integer.parseInt(br.readLine());
		
		// 2차원배열에 수열 A, 수열 B를 저장
		// - 단 마지막 큐에 저장된 숫자부터 출력하고 싶으므로, 마지막 열부터 반대로 저장
		int[][] arr = new int[2][N];
		
		for (int r = 0; r < 2; r++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			for (int c = N - 1; c >= 0; c--) {
				arr[r][c] =  Integer.parseInt(st1.nextToken());
			}
		}
		
		// 큐에 결과값 저장
		Queue<Integer> res = new LinkedList<>();
		for (int c = 0; c < N; c++) {
			if (arr[0][c] == 0) { // 1인 경우 스택이므로 무시한다.
				res.offer(arr[1][c]);
			}
		}
		
		// 삽입할 수열의 길이 M
		int M = Integer.parseInt(br.readLine());
		
		// 수열 C의 원소들을 res 큐에 덧붙여주기
		// - 만약 큐의 개수가 M보다 작은 경우, 수열의 원소가 출력되기 때문이다.
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			res.offer(Integer.parseInt(st2.nextToken()));
		}
		
		// 결과 출력
		// - 삽입한 수열의 길이 M개만큼만 출력한다.
		// - BufferedWriter 의 경우 버퍼를 잡아 놓았기 때문에, 반드시 flush() / close()를 호출해 뒤처리해야 한다.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < M; i++) {
			bw.write(res.poll() + " ");
		}
		bw.flush(); // 남아있는 데이터를 모두 출력시킴
		bw.close(); // 스트림을 닫음
	}

}