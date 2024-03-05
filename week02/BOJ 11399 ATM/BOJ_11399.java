import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] time = new int[count];
		for (int j = 0; j < count; j++) {
			time[j] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time); // 오름차순으로 정렬
		// 오름차순으로 정렬한 순서가 최종합이 가장 적은 순서가 된다
		int sum = 0;
		// 1, 1+2, 1+2+3, ... 순으로 가면 최종적인 합에는 (총 연산 횟수 - 수 자신의 인덱스) 만큼 해당 수가 더해지게 됨
		for (int j = 0; j < count; j++) {
			sum += time[j] * (count - j);
		}
		System.out.println(sum);
	
	}
}
