import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Surveillance {
	int r;
	int c;
	int type;

	public Surveillance(int r, int c, int type) {
		super();
		this.r = r;
		this.c = c;
		this.type = type;
	}

}

public class BOJ_15683 {
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int row;
	static int col;
	static boolean[][] visited;
	static int[][] map;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		row = Integer.parseInt(info[0]);
		col = Integer.parseInt(info[1]);
		map = new int[row][col];
		int survCnt = 0;
		for (int r = 0; r < row; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < col; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				if (num > 0 && num < 6) {
					survCnt++;
				}
			}
		}
		Surveillance[] CCTV = new Surveillance[survCnt];
		int idx = 0;

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				int num = map[r][c];
				if (num == 1) {
					CCTV[idx] = new Surveillance(r, c, 1);
					idx++;
				} else if (num == 2) {
					CCTV[idx] = new Surveillance(r, c, 2);
					idx++;
				} else if (num == 3) {
					CCTV[idx] = new Surveillance(r, c, 3);
					idx++;
				} else if (num == 4) {
					CCTV[idx] = new Surveillance(r, c, 4);
					idx++;
				} else if (num == 5) {
					CCTV[idx] = new Surveillance(r, c, 5);
					idx++;
				}
			}
		}

		boolean[] permVisit = new boolean[survCnt];
		perm(CCTV, new Surveillance[survCnt], permVisit, 0, survCnt);
		System.out.println(min);
	}

	static void perm(Surveillance[] arr, Surveillance[] output, boolean[] permVisit, int depth, int r) {
		if (depth == r) {
			visited = new boolean[row][col];
			int cnt = eval(output);
			min = Math.min(min, cnt);
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (!permVisit[i]) {
				permVisit[i] = true;
				output[depth] = arr[i];
				perm(arr, output, permVisit, depth + 1, r);
				permVisit[i] = false;
			}
		}
	}

	static int eval(Surveillance[] output) {
		int surv = output.length;
		for (int i = 0; i < surv; i++) {
			Surveillance s = output[i];
			if (s.type == 1) {
				servOne(s.r, s.c);
			} else if (s.type == 2) {
				servTwo(s.r, s.c);
			} else if (s.type == 3) {
				servThree(s.r, s.c);
			} else if (s.type == 4) {
				servFour(s.r, s.c);
			} else if (s.type == 5) {
				servFive(s.r, s.c);
			}
		}
		int non = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (!visited[r][c] & map[r][c] == 0) {
					non++;
				}
			}
		}
		return non;
	}

	static int countPerDir(int d, int r, int c) {
		int count = 0;
		int x = r + dx[d];
		int y = c + dy[d];
		while (x >= 0 && x < row && y >= 0 && y < col) {
			if (map[x][y] == 6) {
				break;
			}
			if (!visited[x][y] && map[x][y] == 0) {
				count++;
			}
			x += dx[d];
			y += dy[d];
		}
		return count;
	}

	static void visitDir(int d, int r, int c) {
		int x = r + dx[d];
		int y = c + dy[d];
		while (x >= 0 && x < row && y >= 0 && y < col) {
			if (map[x][y] == 6) {
				break;
			}
			visited[x][y] = true;
			x += dx[d];
			y += dy[d];
		}
	}

	private static void servOne(int r, int c) {
		int max = -1;
		int maxDir = -1;
		for (int d = 0; d < 4; d++) {
			int count = countPerDir(d, r, c);
			if (count > max) {
				max = count;
				maxDir = d;
			}
		}
		visitDir(maxDir, r, c);

	}

	private static void servTwo(int r, int c) {
		int max = -1;
		int maxDir = -1;
		int[] directions = { 0, 1 };
		for (int i = 0; i < 2; i++) {
			int dir1 = directions[i];
			int count = 0;
			for (int j = 0; j < 4; j += 2) {
				int dir = dir1 + j;
				int subCnt = countPerDir(dir, r, c);
				count += subCnt;
			}
			if (count > max) {
				max = count;
				maxDir = dir1;
			}
		}
		for (int j = 0; j < 4; j += 2) {
			visitDir(maxDir + j, r, c);
		}
	}

	private static void servThree(int r, int c) {
		int max = -1;
		int maxDir = -1;
		for (int i = 0; i < 4; i++) {
			int count = 0;
			for (int j = 0; j < 2; j++) {
				int dir = (i + j) % 4;
				int subCnt = countPerDir(dir, r, c);
				count += subCnt;
			}
			if (count > max) {
				max = count;
				maxDir = i;
			}
		}
		for (int j = 0; j < 2; j++) {
			int dir = (maxDir + j) % 4;
			visitDir(dir, r, c);
		}
	}

	private static void servFour(int r, int c) {
		int max = -1;
		int maxDir = -1;
		for (int i = 0; i < 4; i++) {
			int count = 0;
			for (int j = 0; j < 3; j++) {
				int dir = (i + j) % 4;
				int subCnt = countPerDir(dir, r, c);
				count += subCnt;
			}
			if (count > max) {
				max = count;
				maxDir = i;
			}
		}
		for (int j = 0; j < 3; j++) {
			int dir = (maxDir + j) % 4;
			visitDir(dir, r, c);
		}

	}

	private static void servFive(int r, int c) {
		for (int d = 0; d < 4; d++) {
			visitDir(d, r, c);
		}

	}
}
