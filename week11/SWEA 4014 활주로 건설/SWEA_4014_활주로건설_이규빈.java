package week11;

import java.io.*;
import java.util.*;

public class SWEA_4014_활주로건설_이규빈 {
	static int N, X;
	static int[][] map1, map2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			// 높이 정보 저장
			map1 = new int[N][N];
			map2 = new int[N][N]; // 행과 열을 바꾼 맵 따로 입력 -> for문 1개로 배열 2개 탐색 가능
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());
					map1[r][c] = num;
					map2[c][r] = num;
				}
			}
			
			int res = 0;
				
			// 한 행씩 인자로 넘겨서, 해당 행에서 활주로 건설이 가능한지 판단
			for (int r = 0; r < N; r++) {
				res += isOk(map1[r]);
				res += isOk(map2[r]);
			}
			
			sb.append("#" + tc + " " + res + "\n");
		}
		System.out.println(sb);
	}

	
	// 활주로 건설 가능하면 1, 불가능하면 0을 리턴
	static int isOk(int[] arr) {
		boolean[] check = new boolean[N]; // 경사로 건설여부 저장
		
		for (int i = 0; i < N - 1; i++) {
			int prev = arr[i];
			int next = arr[i + 1];
			
			// 높이가 2이상 차이나면 이번 행은 불가능
			if (Math.abs(prev - next) > 1)  return 0;
			
			// 해당 칸에 이미 경사로가 있거나 평지라면, 다음 칸으로 이동
			if (check[i + 1] || prev == next)  continue;
			
			// 1. 내려가는 경우 : i+1칸부터 i+X칸까지 경사로 설치 가능여부를 판단
			if (prev > next) {
				for (int j = i + 1; j <= i + X; j++) {
					// 경계조건 위반 or i+1칸~i+X칸이 평지가 아님 or 이미 경사로가 설치됨 -> 불가능
					if (j == N || arr[j] != next || check[j])  return 0;
					if (j != i + X) arr[j] = next + 1;
					// 경사로 설치
					check[j] = true;
				}
			}
			// 2. 올라가는 경우 : i칸부터 i-X+1칸까지 경사로 설치 가능여부를 판단 (로직 동일)
			else {
				for (int j = i; j >= 1 - X + 1; j--) {
					if (j < 0 || arr[j] != prev || check[j])  return 0;
					if (j != i - X + 1) arr[j] = prev + 1;
					check[j] = true;
				}
			}
		}
		return 1;
	}
}
