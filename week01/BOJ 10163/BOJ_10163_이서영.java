import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_10163_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[][] arr = new int[1001][1001];
		for (int i = 1; i <= num; i++) {
			String[] properties = br.readLine().split(" ");
			int col = Integer.parseInt(properties[0]);
			int row = Integer.parseInt(properties[1]);
			int width = Integer.parseInt(properties[2]);
			int height = Integer.parseInt(properties[3]);
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					arr[col + w][row + h] = i;
				}
			}
		}
		HashMap<Integer, Integer> area = new HashMap<>();
		for (int r = 0; r < 1001; r++) {
			for (int c = 0; c < 1001; c++) {
				area.put(arr[r][c], area.getOrDefault(arr[r][c], 0) + 1);
			}
		}
		for (int j = 1; j <= num; j++) {
			System.out.println(area.getOrDefault(j, 0));
		}
	}
}
