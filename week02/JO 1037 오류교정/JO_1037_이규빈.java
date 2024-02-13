import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1037_이규빈 {
	/*
	 * 	JO 1037. 오류교정
	 * 	풀이시간 : 1시간
	 * 	메인 접근법 : 각 행 숫자의 합을 저장한 rowSum 배열, 각 열 숫자의 합을 저장한 colSum 배열을 활용해 오류 탐색
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 행렬 크기 n 입력
		int n = Integer.parseInt(br.readLine());
		
		// 각 행과 열의 합을 저장하는 배열
		int[] rowSum = new int[n + 1]; // i번 인덱스에 arr의 i행에 있는 1의 개수가 저장됨
		int[] colSum = new int[n + 1]; // j번 인덱스에 arr의 j열에 있는 1의 개수가 저장됨
		
		// (n+1) * (n+1) 행렬 입력
		// - 문제는 각 행과 열이 1부터 시작하므로, 헷갈리지 않도록 1만큼 더 크게 행렬을 만들고 0행과 0열은 쓰지 않는다.
		int[][] arr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 1) {
					rowSum[i]++;
					colSum[j]++;
				}
			}
		}
		
		// 오류 교정을 위한 변수들 선언 및 초기화
		int rowErrorIdx = 0;
		int rowErrorCnt = 0;
		
		int colErrorIdx = 0;
		int colErrorCnt = 0;
		
		// 오류 탐색
		for (int k = 1; k <= n; k++) {
			if (rowSum[k] % 2 != 0) {
				rowErrorIdx = k;
				rowErrorCnt++;
			}
			if (colSum[k] % 2 != 0) {
				colErrorIdx = k;
				colErrorCnt++;
			}
		}

		// case 1. 두 배열의 원소가 모두 짝수인 경우, "OK" - 패리티 성질을 가짐
		if (rowErrorCnt == 0 && colErrorCnt == 0) {
			System.out.println("OK");
		}
		
		// case 2. 두 배열에서 홀수인 원소가 하나씩 있는 경우, "Change bit (i,j)"
		else if (rowErrorCnt == 1 && colErrorCnt == 1) {
			System.out.printf("Change bit (%d,%d)\n", rowErrorIdx, colErrorIdx);
		} 
		
		// case 3. 그 외는 "Corrupt" - 잘못된 행렬
		else {
			System.out.println("Corrupt");
		}
		
	}

}
