package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2210_이규빈 {
	/*
	*	Q.숫자판 점프
	*	# 문제 요약 : 주어진 숫자판에서 상하좌우 한 칸씩 이동해 만든 6자리 수의 개수를 구하라.
	*	# 풀이시간 : 1시간 40분
	*	# 메모리 / 실행시간 : 16,496kb / 352ms
	*	# 메인 접근법 - DFS
	*		- 재귀 깊이가 하나 내려갈 때마다 사방탐색을 통해 숫자를 하나씩 덧붙인다.
	*		- 숫자 길이가 6이 되면 리스트에 저장한 숫자들과 비교해, 모두 다른 경우만 추가한다.
	*	# 막힌 부분 해결
	*		DFS인 것은 파악했으나, 개념 이해가 덜 되어 구현이 힘들었음
	*		-> 상태공간 트리와 시스템 스택을 그려보면서 재귀함수 완성함
	*/
	static String[][] map;
	static List<String> numList;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int nr, nc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new String[5][5];
		for (int r = 0; r < 5; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 5; c++) {
				map[r][c] = st.nextToken();
			}
		}
		
		// --------- INPUT END ------------
		
		// 완성된 6자리 수들을 저장할 리스트
		// - 일치여부 판단을 위해 탐색이 빈번하므로, ArrayList를 선택
		numList = new ArrayList<>(); 
		
		// DFS 실행
		// - map을 순회하며 각 원소를 시작점으로 6자리 수 만들기
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				dfs(r, c, map[r][c]);
			}
		}
		
		System.out.println(numList.size());
	}

	/**
	 * 
	 * @param r,c: map 좌표
	 * @param tmp: 현재 만들고 있는 숫자 -> 재귀 깊이에 따라 숫자를 하나씩 덧붙이므로, 매개변수로 넘겨줘야 한다.
	 */
	static void dfs(int r, int c, String tmp) {
		// base case - 6자리 수를 완성한 경우
		if (tmp.length() == 6) {
			// 기존에 만든 수가 아닌 경우에만 리스트에 저장
			if (!numList.contains(tmp)) {
				numList.add(tmp);
			}
			return;
		}
		
		// recursive case
		// - 한번 거쳤던 칸을 다시 거쳐도 되므로, 방문 판단은 하지 않는다.
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			
			if (0 <= nr && nr <= 4 && 0 <= nc && nc <= 4) { // 경계조건
				dfs(nr, nc, tmp + map[nr][nc]);
			}
		}
	}
}
