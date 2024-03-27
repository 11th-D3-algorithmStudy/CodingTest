package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SOFT_6275_이서영 {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int minMoves = Integer.MAX_VALUE;
	static int moves = 0;
	static boolean[][] visited;
	static int row;
	static int col;
	static int count = 0;
	static int over;
	static int xPos = -1;
	static int yPos = -1;
	static int direction = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		row = Integer.parseInt(info[0]);
		col = Integer.parseInt(info[1]);
		char[][] map = new char[row][];
		for (int r = 0; r < row; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < col; c++) {
				if (map[r][c] == '#') {
					count++;
				}
			}
		}

		visited = new boolean[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (map[r][c] == '#') {
					over = 1;
					search(map, r, c, 0);
				}
			}
		}
		String dirStr = "";
		if (direction == 0) {
			dirStr = ">";
		} else if (direction == 1) {
			dirStr = "v";
		} else if (direction == 2) {
			dirStr = "<";
		} else {
			dirStr = "^";
		}
		System.out.println((xPos + 1) + " " + (yPos + 1));
		System.out.println(dirStr);
		over = 1;
		tracing(map, xPos, yPos, direction);
	}

	private static void search(char[][] map, int r, int c, int dir) {
		if (visited[r][c]) {
			return;
		}
//		System.out.println("over =" + over + " count = " + count);
		if (over == count) {
			if (moves < minMoves) {
				xPos = r;
				yPos = c;
				direction = (dir + 2) % 4;
			}
			return;
		}

		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int newDir = (dir + d) % 4;
			moves++;
			if (d == 3) {
				moves -= 2;
			}
			// when op == A
			int x = r + dx[newDir];
			int y = c + dy[newDir];
			int x2 = r + 2 * dx[newDir];
			int y2 = c + 2 * dy[newDir];
			if (x2 >= 0 && x2 < row && y2 >= 0 && y2 < col && map[x][y] == '#' && map[x2][y2] == '#'
					&& !visited[x2][y2]) {
				moves++;
				visited[x][y] = true;
				over += 2;
				search(map, x2, y2, newDir);
				visited[x][y] = false;
				moves--;
				over -= 2;
			}
		}

		visited[r][c] = false;
	}

	static void tracing(char[][] map, int r, int c, int dir) {
		if (visited[r][c]) {
			return;
		}
//		System.out.println("over =" + over + " count = " + count);
		if (over == count) {
			return;
		}

		visited[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int newDir = (dir + d) % 4;
			if (d == 3) {
				moves -= 2;
			}
			// when op == A
			int x = r + dx[newDir];
			int y = c + dy[newDir];
			int x2 = r + 2 * dx[newDir];
			int y2 = c + 2 * dy[newDir];
			if (x2 >= 0 && x2 < row && y2 >= 0 && y2 < col && map[x][y] == '#' && map[x2][y2] == '#'
					&& !visited[x2][y2]) {
				if (d == 1) {
					System.out.print("R");
				}  else if (d == 3) {
					System.out.print("L");
				}
				moves++;
				visited[x][y] = true;
				over += 2;
				System.out.print("A");
				tracing(map, x2, y2, newDir);
				visited[x][y] = false;
				moves--;
				over -= 2;
			}
		}

		visited[r][c] = false;
	}
}
