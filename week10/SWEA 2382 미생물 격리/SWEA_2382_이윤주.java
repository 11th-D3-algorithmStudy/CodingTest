package Study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Cluster {
	int x;
	int y;
	int num;
	int dir;

	public Cluster(int x, int y, int num, int dir) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.dir = dir; // 상1 하2 좌3 우4
	}
}

public class SWEA_2382_이윤주 {
	// M 시간 후 남은 미생물 수 총합

	static int T, N, M, K;
	static int[][] map;
	static List<Cluster> list;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("미생물.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 한변의 길이 5~100
			M = Integer.parseInt(st.nextToken()); // 격리 시간 1~1000
			K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수 5~1000

			map = new int[N][N]; // 각 셀에 있는 군집의 개수를 표시하는 맵
			list = new ArrayList<>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); // 세로위치
				int y = Integer.parseInt(st.nextToken()); // 가로위치
				int num = Integer.parseInt(st.nextToken()); // 미생물 수
				int dir = Integer.parseInt(st.nextToken()); // 이동방향

				list.add(new Cluster(x, y, num, dir));
				map[x][y] = 1;
			} // 입력 끝

			for (int i = 1; i <= M; i++) { // M시간 확인
				for (int j = list.size() - 1; j >= 0; j--) { // 군집 list 순회하면서 각 군집을 이동시킨다.
					int dir = list.get(j).dir;
					int x = list.get(j).x;
					int y = list.get(j).y;
					map[x][y] -= 1;

					switch (dir) {
					case 1:// 상
						x -= 1;
						// 약품 부분으로 이동한 경우
						if (x == 0) {
							list.get(j).num /= 2; // 2로 나누고 소수점 버림
							list.get(j).dir = 2; // 반대방향으로 바뀜
						}
						break;
					case 2:// 하
						x += 1;
						// 약품 부분으로 이동한 경우
						if (x == N - 1) {
							list.get(j).num /= 2; // 2로 나누고 소수점 버림
							list.get(j).dir = 1; // 반대방향으로 바뀜
						}
						break;
					case 3:// 좌
						y -= 1;
						// 약품 부분으로 이동한 경우
						if (y == 0) {
							list.get(j).num /= 2; // 2로 나누고 소수점 버림
							list.get(j).dir = 4; // 반대방향으로 바뀜
						}
						break;
					case 4:// 우
						y += 1;
						// 약품 부분으로 이동한 경우
						if (y == N - 1) {
							list.get(j).num /= 2; // 2로 나누고 소수점 버림
							list.get(j).dir = 3; // 반대방향으로 바뀜
						}
						break;
					}

					if (list.get(j).num == 0) { // 군집이 사라지는 경우
						list.remove(j);
						map[x][y] -= 1;
						continue;
					}

					map[x][y] += 1;

					list.get(j).x = x;
					list.get(j).y = y;
				} // 군집 이동 끝

				// 합쳐지는 경우 확인하기!!
				for (int x = 1; x < N - 1; x++) {
					for (int y = 1; y < N - 1; y++) {
						if (map[x][y] >= 2) {
							int sum = 0;
							int max = 0;
							int dir = 0;

							for (int j = list.size() - 1; j >= 0; j--) {
								if (list.get(j).x == x && list.get(j).y == y) {
									sum += list.get(j).num;
									if (max < list.get(j).num) {
										max = list.get(j).num;
										dir = list.get(j).dir;
									}
									list.remove(j); // 합칠거니까 삭제
								}
							} // 같은 좌표에 있는 군집 확인 끝
							list.add(new Cluster(x, y, sum, dir));
							map[x][y] = 1;
						} // 같은 좌표에 여러개 있는 경우 합치기 끝
					}
				} // 모든 좌표 확인 끝
			} // M시간 끝

			int answer = 0;
			for (int i = 0; i < list.size(); i++) {
				answer += list.get(i).num;
			}

			System.out.printf("#%d %d\n", tc, answer);
		} // tc
	}// main
}
