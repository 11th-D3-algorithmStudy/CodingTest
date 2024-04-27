package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16918_이서영 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int row;
	static int col;
	static int[][] map;
	static boolean[][] visited;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		row = Integer.parseInt(info[0]);
		col = Integer.parseInt(info[1]);
		int time = Integer.parseInt(info[2]);

		char[][] temp = new char[row][col];
		map = new int[row][col];
		for (int r = 0; r < row; r++) {
			temp[r] = br.readLine().toCharArray();
		}
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				char block = temp[r][c];
				if (block == '.') {
					map[r][c] = -1;
				}
				if (block == 'O') {
					map[r][c] = 2;
				}
			}
		}
		for (int t = 1; t < time; t++) {
			visited = new boolean[row][col];
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) { 
					int num = map[r][c];
					if (num == -1 && !visited[r][c]) {
						map[r][c] = 3;
					} else if (num > 1) {
						map[r][c]--;
					} else if (num == 1) {
						explode(r, c);
						map[r][c] = -1;
					}
				}
			}
		}
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (map[r][c] == -1) {
					System.out.print('.');
				}else if (map[r][c] > 0) {
					System.out.print('O');
				}
			}
			System.out.println();
		}
	}

	public static void explode(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int x = r + dx[d];
			int y = c + dy[d];
			if (x >= 0 && x < row && y >= 0 && y < col) {
				if (map[x][y] == 1 && (x > r || y > c)) {
					//
//					System.out.println("r: " + r + " c: " + c);
//					System.out.println("here x :" + x + " y: " + y);
				}else {
					map[x][y] = -1;
					visited[x][y] = true;
				}
			}
		}
	}
}
