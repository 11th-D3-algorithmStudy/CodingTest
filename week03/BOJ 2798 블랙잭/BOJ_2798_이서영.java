import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] cardInfo = br.readLine().split(" ");
		int n = Integer.parseInt(cardInfo[0]);
		int m = Integer.parseInt(cardInfo[1]);
		int[] cards = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		int minDiff = Integer.MAX_VALUE;
		int maxNum = 0;
		
		for (int j = 0; j < n - 2; j++) {
			for (int k = j + 1; k < n - 1; k++) {
				for (int l = k + 1; l < n; l++) {
					int num = cards[j] + cards[k] + cards[l];
					if (num <= m) {
						if (m - num < minDiff) {
							minDiff = m - num;
							maxNum = num;
						}
					}
				}
			}
		}
		System.out.println(maxNum);
	}
}
