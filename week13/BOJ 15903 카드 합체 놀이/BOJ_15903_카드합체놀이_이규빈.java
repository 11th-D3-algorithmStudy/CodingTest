package week13;

import java.io.*;
import java.util.*;

public class BOJ_15903_카드합체놀이_이규빈 {
	/*
	 *  처음에 배열과 결과값 타입을 int로 해서 틀림
	 *  -> 문제 다시 보니 21억 넘을 수도 있을거 같아 long으로 바꿨더니 통과됨
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// ----- 입력 끝 -----
		
		// 정렬 후 가장 앞의 두 숫자 더하는 작업을 m번 반복
		// -> 다른 답안 보니 PQ를 사용하면 더 효율적일듯
		for (int i = 0; i < m; i++) {
			Arrays.sort(arr);
			long num = arr[0] + arr[1];
			arr[0] = num;
			arr[1] = num;
		}
		
		long res = 0;
		for (int i = 0; i < n; i++) {
			res += arr[i];
		}
		
		System.out.println(res);
	}
}
