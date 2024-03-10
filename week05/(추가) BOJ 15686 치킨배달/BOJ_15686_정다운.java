package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_정다운 {

	static int[][] cityArr; // 도시 정보
	static List<int[]> hList = new ArrayList<>(); // 집 좌표 리스트
	static List<int[]> ckList = new ArrayList<>(); // 치킨집 좌표 리스트

	static int M; // 남겨야 하는 치킨집 개수

	// 남길 치킨집의 좌표(배열)들을 임시 저장할 리스트
	static List<int[]> sel = new ArrayList<>();
	
	static int res;

	public static void main(String[] args) throws Exception {
		// List에 모든 치킨집 좌표를 입력하고
		// 그 중 M개만 고르는 경우를 모두 뽑아
		// 그 경우들의 치킨거리를 구하자

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 치킨집 개수

		cityArr = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int info = Integer.parseInt(st2.nextToken());
				cityArr[r][c] = info;

				int[] coorArr = { r, c };

				if (info == 1) { // 집이면
					hList.add(coorArr); // 집 좌표 리스트에 저장
				} else if (info == 2) { // 치킨집이면
					ckList.add(coorArr); // 치킨집 좌표 리스트에 저장
				}
			}
		}

		int[] test = new int[2];
		res = Integer.MAX_VALUE;

		for (int i = 0; i < M; i++) {
			sel.add(test);
		}

		combination(0, 0);
		
		System.out.println(res);

	}

	// idx : 현재 내가 판단할 재료
	// sidx : 조합할 재료의 인덱스
	static void combination(int idx, int sidx) {
		// base case
		if (sidx == M) { // 남길 치킨집을 다 고르면
			int cityLen = 0;
			for (int h = 0; h < hList.size(); h++) { // 모든 집 돌면서
				int min = Integer.MAX_VALUE;
				for (int[] i : sel) { // 가장 가까운 치킨집 찾기 
					int len = Math.abs(i[0] - hList.get(h)[0]) + Math.abs(i[1] - hList.get(h)[1]);
					min = Math.min(len, min);
				}
				cityLen += min;
			}
			
			res = Math.min(cityLen, res);

			return;
		}

		// recursive case
		for (int i = idx; i <= (ckList.size() - M) + sidx; i++) {
			sel.set(sidx, ckList.get(i));
			combination(i + 1, sidx + 1);
		}

	}

}