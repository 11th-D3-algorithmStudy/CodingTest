package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_조아름 {
	static boolean[][] visited;
	static ArrayList<int[]> list; // 방문한 값 저장할 ArrayList
	static int[][] arr;
	static int N, L, R;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 }; // 상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(move());

	}

	private static int move() { // 인구 이동 횟수 반환
		int result = 0;
		while (true) {
			visited = new boolean[N][N]; // 돌아갈때마다 방문 초기화
			boolean isMove = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						int sum = bfs(i, j); // 연합찾기
						if(list.size()>1) {
							change(sum);
							isMove = true;
						}
					}
				}
			}
			if(!isMove) 
				return result;
			result++; // isMove가 true일 때만 작동해아 하므로 이 자리.
		}
	}

	private static void change(int sum) {
		int avg = sum / list.size();
		for(int i=0;i<list.size();i++) {
			int x = list.get(i)[0];
			int y = list.get(i)[1];
			arr[x][y] = avg;
		}
	}

	private static int bfs(int x, int y) { // 조건에 해당하면 값을 다 더해서 sum값 반환하기
		Queue<int[]> q = new LinkedList<>();
		list = new ArrayList<>();

		q.add(new int[] { x, y });
		list.add(new int[] { x, y });
		visited[x][y] = true;
		int sum = arr[x][y];

		while (!q.isEmpty()) {
			int[] node = q.poll();

			for (int i = 0; i < 4; i++) {
				int xx = node[0] + dr[i];
				int yy = node[1] + dc[i];

				if (inMap(xx, yy) && !visited[xx][yy]) {
					int cha = Math.abs(arr[node[0]][node[1]] - arr[xx][yy]);
					if (cha >= L && cha <= R) {
						visited[xx][yy] = true;
						q.add(new int[] { xx, yy });
						list.add(new int[] { xx, yy });
						sum += arr[xx][yy];
					}
				}
			}
		}
		return sum;
	}

	private static boolean inMap(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
}
