package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1037_장현영 {
	// 오류교정
	// 패리티 행렬
	// 행열 교차하는 지점이 행 열 둘다 홀수일때 하나만 바꿔서 짝수로 바꿈
	// 단 교차지점이 하나만 홀수거나 둘다 홀수인것이 여러개인 경우는 FAIL
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][n];

		int rawCnt = 0; // 홀수 개수
		int colCnt = 0; // 홀수 개수
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int changeR = -1;
		int changeC = -1;
		for (int i = 0; i < n; i++) {
			int rawSum = 0; // i:고정, j: 0 ~ n
			int colSum = 0; // i:0~n, j:고정
			for (int j = 0; j < n; j++) {
				rawSum += arr[i][j];
				colSum += arr[j][i];
			}
			if (colSum % 2 == 1) {
				colCnt++;
				changeC = i+1;
			}
			if (rawSum % 2 == 1) {
				rawCnt++;
				changeR = i+1;
			}
			if(rawCnt > 1 || colCnt > 1) {
				System.out.println("Corrupt");
				return; // main 종료
			}
		}
		// for문을 다 돌았다면 cnt가 1개씩일 경우만 change bit 가능
		if(rawCnt == 1 && colCnt  == 1) {
			System.out.println("Change bit ("+changeR+","+changeC+")");
		}else
			System.out.println("OK");
		
		
	}
}
