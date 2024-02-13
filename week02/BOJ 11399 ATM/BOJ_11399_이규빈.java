import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399_이규빈 {
	/*
	 * 	BOJ 11399. ATM
	 *  풀이시간 : 20분
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int res = 0;
		
		// 인출시간의 최솟값 계산
		// - 가장 작은 숫자는 N번 더해지고, 그 다음 숫자는 N-1번, ... 가장 큰 숫자는 1번만 더해진다.
		for (int i = 0; i < N; i++) {
			res += arr[i] * (N - i);
		}
		
		System.out.println(res);
	}
}