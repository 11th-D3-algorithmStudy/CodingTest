package week12;

import java.io.*;
import java.util.*;

public class BOJ_4485_장현영 {
	// 다익스트라 녹색 젤다
	// 기존 cost를 heap 이용해서 최소 비용 갱신해주기
	// int[]형 비교시에 comparator 사용하기(참고)

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int num = 1;

		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}

			int[][] arr = new int[n][n];
			int[][] rupee = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					rupee[i][j] = Integer.MAX_VALUE; // 최댓값으로 세팅
				}
			}

			int ans = dijkstra(arr, rupee, n);
			System.out.println("Problem " + num + ": " + ans);
			num++;
		}
	}
	
	public static int dijkstra(int[][] arr, int[][] rupee, int n) {
		// 최소 비용을 항상 뽑아내는 heap : cost를 항상 작은값으로 업데이트!
		PriorityQueue<int[]> heap = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]);
		heap.offer(new int[] { arr[0][0], 0, 0 });

		while (!heap.isEmpty()) {
			int[] node = heap.poll();
			int cost = node[0];
			int x = node[1];
			int y = node[2];

			if (rupee[x][y] < cost) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					int ncost = arr[nx][ny];
					int costSum = cost + ncost;

					if (costSum < rupee[nx][ny]) {
						heap.offer(new int[] { costSum, nx, ny });
						rupee[nx][ny] = costSum;
					}
				}
			}
		}

		return rupee[n-1][n-1];
	}

}
