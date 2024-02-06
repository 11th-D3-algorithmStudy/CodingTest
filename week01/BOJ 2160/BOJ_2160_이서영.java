import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2160_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		List<char[][]> paintings = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			char[][] painting = new char[5][7];
			for (int j = 0; j < 5; j++) {
				painting[j] = br.readLine().toCharArray();
			}
			paintings.add(painting);
		}
		
		int min = Integer.MAX_VALUE;
		int p1 = 0;
		int p2 = 0;
		for (int l = 0; l < num; l++) {
			for (int m = 1; m < num-l; m++) {
				int diff = compare(paintings.get(l), paintings.get(l+m));
				System.out.println("1: " + (l+1) + " 2: " + (l+m+1) + " diff: " + diff);
				if (diff < min) {
					min = diff;
					p1 = l;
					p2 = l+m;
				}
			}
		}
		System.out.println((p1+1) + " " + (p2+1));
	}
	
	static int compare(char[][] p1, char[][] p2) {
		int diff = 0;
		for (int r = 0; r < p1.length; r++) {
			for (int c = 0; c < p1[0].length; c++) {
				if (p1[r][c] != p2[r][c]) {
					diff++;
				}
			}
		}
		return diff;
	}
}


