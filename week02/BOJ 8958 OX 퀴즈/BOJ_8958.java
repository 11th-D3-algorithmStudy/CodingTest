import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_8958 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		for (int i = 0; i < count; i++) {
			String[] arr = br.readLine().split("X"); // X를 기점으로 O만 나눠줌
			int sum = 0;
			for (String s : arr) {
				sum += (s.length() * (s.length() + 1)) / 2; // 1+2+3+...+n = n*(n+1)/2 를 이용
			}
			System.out.println(sum);
		}
	}
}
