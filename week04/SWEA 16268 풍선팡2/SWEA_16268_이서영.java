import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16268_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		for (int i = 0; i < cases; i++) {
			String[] infos = br.readLine().split(" ");
			int row = Integer.parseInt(infos[0]);
			int col = Integer.parseInt(infos[1]);
			int[][] balloons = new int[row][col]; 
			for (int r = 0; r < row; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < col; c++) {
					balloons[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] dx = {0, 0, -1, 1};
			int[] dy = {-1, 1, 0, 0};
			int max = 0;
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					int sum = balloons[r][c];
					for (int j = 0; j < 4; j++) {
						int x = r + dx[j];
						int y = c + dy[j];
						if (x >= 0 && x < row && y >=0 && y < col) {
							sum += balloons[x][y];
						}
					}
					max = Math.max(max, sum);
				}
			}
			System.out.println("#" + (i+1) + " " + max);
		}
		
	}
}
