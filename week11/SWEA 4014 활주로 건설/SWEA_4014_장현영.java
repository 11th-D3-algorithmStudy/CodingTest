package week11;

import java.io.*;
import java.util.*;

public class SWEA_4014_장현영 {

	// 활주로 건설
	// arr -> 높이
	// 가로나 세로방향으로 건설할 수 있는 가능성 확인
	// 높이가 같더나 경사로를 설치해야 가능
	// 경사로의 길이를 고려했을 때 활주로 ox여부의 o인 곳을 세라
	// 오름차순으로 판단함(0->n-1, n-1->0 2번실시)
	// 주의점 1. 단차가 2이상이면 즉시 false
	// 주의점 2. 0->n-1때 설치한 활주로에 n-1->0일때 똑같은 위치 활주로 필요하면 false
	// 주의점 3. 동높이 개수 >= x이어야 활주로 설치가능 (만족 못하면 그 즉시 false) 
	
	
	static int n, x;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			int[][] arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// --- 입력 끝 ---
			// transpose
			int[][] arrT = new int[n][n];
			for(int i=0; i<n;i++) {
				for(int j=0; j<n;j++) {
					arrT[j][i] = arr[i][j];
				}
			}
			
			int cnt = 0;
			// 가로 세로 탐색하기
			for (int i = 0; i < n; i++) {
				if (isAble(arr[i])) { // 가로
					//System.out.println("가로" +(i+1)+"번째");
					cnt++;
				}
				if(isAble(arrT[i])) { // 세로
					//System.out.println("세로" +(i+1)+"번째");
					cnt++;
				}
			}
			System.out.printf("#%d %d\n", t,cnt);
			
		}
	}
	
	static boolean isAble(int[] route) {
		// 높이가 달라질떄 지금까지 셌던 동 높이 개수가 x보다 크거나 같아야 세울수 있음
		boolean[] visited = new boolean[n];
		int currCnt = 0;
		// (높이가 커지는 것만 계산)
		int curr = route[0];
		// 0 to n-1
		for (int i = 0; i < n; i++) {
			if (Math.abs(curr - route[i]) >1) return false;
			if (curr < route[i]) {
				curr = route[i];
				if (currCnt >= x) { // 활주로 설치 가능
					for(int j=i-1;j>=i-x;j--) {
						visited[j]=true;
					}
					currCnt = 1;
				} else {
					// 활주로 설치 불가능
					return false;
				}
			} else if (curr == route[i]) {
				currCnt++;
			}
			else { // 작아지는 것 계속 update 해줘야함
				curr = route[i];
				currCnt = 1;
			}
			
		}
		currCnt = 0;
		curr = route[n - 1];
		// n-1 to 0
		for (int i = n - 1; i >= 0; i--) {
			if (Math.abs(curr - route[i]) >1) return false;
			if (curr < route[i]) {
				curr = route[i];
				if (currCnt >= x) { // 활주로 설치 가능
					for(int j=i+1;j<=i+x;j++) {
						if(visited[j]) return false; // 이미 설치한 곳 불가능!
						visited[j] = true;
					}
					currCnt = 1;
				} else {
					// 활주로 설치 불가능
					return false;
				}
			} else if (curr == route[i]) {
				currCnt++;
			}
			else { // 작아지는 것 계속 update 해줘야함
				curr = route[i];
				currCnt = 1;
			}
		}
		return true;
	}

}
