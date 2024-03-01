import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_10760_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 0; i < cases; i++) {
			String[] infos = br.readLine().split(" ");
			int row = Integer.parseInt(infos[0]);
			int col = Integer.parseInt(infos[1]);
			int[][] field = new int[row][col];
			for (int r = 0; r < row; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < col; c++) {
					field[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
			int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
			
			int available = 0;
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					int val = field[r][c];
					int count = 0;
					for (int d = 0; d < 8; d++) {
						int x = r + dx[d];
						int y = c + dy[d];
						if (x >= 0 && x < row && y >= 0 && y < col) {
							if (field[x][y] < val) {
								count++;
							}
						}
					}
					if (count >= 4) {
						available++;	
					}
				}
			}
			System.out.println("#" + (i + 1) + " " + available);
		}
	}
}
