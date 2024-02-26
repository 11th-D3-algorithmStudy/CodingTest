import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959_이서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 0; i < cases; i++) {
			String[] infos = br.readLine().split(" ");
			int n = Integer.parseInt(infos[0]);
			int m = Integer.parseInt(infos[1]);
			int[] arr1 = new int[n];
			int[] arr2 = new int[m];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr1[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr2[j] = Integer.parseInt(st.nextToken());
			}
			int[] smaller = arr1;
			int[] larger = arr2;
			if (n > m) {
				smaller = arr2;
				larger = arr1;
			}
			int diff = 0;
			int max = 0;
			// diff : 오른쪽으로 미는거
			while (smaller.length + diff <= larger.length) {
				int sum = 0;
				for (int j = 0; j < smaller.length; j++) {
					sum += (smaller[j] * larger[j+diff]); 
				}
				max = Math.max(max, sum);
				diff++;
			}
			System.out.println("#" + (i+1) + " " + max); 
		}
	}
}
