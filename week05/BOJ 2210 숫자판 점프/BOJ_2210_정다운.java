package 스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2210_정다운 {

	static int[][] arr;
	// 중복된 수를 저장하지 않기 위해 set 사용
	static Set<String> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arr = new int[5][5];

		for (int r = 0; r < 5; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 5; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		set = new HashSet<>();

		// 모든 위치에서 시작
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				String str = arr[r][c] + ""; 
				getNums(r, c, str);
			}
		}

		// 가능한 경우의 수 출력
		System.out.println(set.size());
	}

	static void getNums(int r, int c, String str) {
		// arr[r][c]에서 시작해서 사방으로 탐색한 지역의 숫자를 문자열에 쌓고
		// 글자길이가 6이 되었을때 set에 입력 후 return
		if (str.length() == 6) {
			set.add(str);
			return;
		}

		// 상하좌우
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
				getNums(nr, nc, str+arr[nr][nc]);
			}
		}
	}
}
