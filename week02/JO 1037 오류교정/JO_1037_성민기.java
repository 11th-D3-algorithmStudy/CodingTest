import java.util.Scanner;

public class JO_1037_성민기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] parity = new int[n][n];
		
		int[] row = new int[n];
		int[] col = new int[n];
		
		// 입력값 받기
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				parity[i][j] = sc.nextInt();
			}
		}
		
		// 행의 합의 값들을 따로 rowSum 배열에 입력
		for(int i=0; i<n; i++) {
			int rowSum = 0;
			for(int j=0; j<n; j++) {
				rowSum += parity[i][j];
			}
			row[i] = rowSum;
		}
		
		// 열의 합의 값들을 따로 colSum 배열에 입력
		for(int j=0; j<n; j++) {
			int colSum = 0;
			for(int i=0; i<n; i++) {
				colSum += parity[i][j];
			}
			col[j] = colSum;
		}
		// rowIdx, colIdx -> 홀수합일 때의 행렬인덱스
		// rowCnt, colCnt -> 홀수가 몇개인지 카운트
		int rowIdx = Integer.MIN_VALUE;
		int colIdx = Integer.MIN_VALUE;
		int rowCnt = 0;
		int colCnt = 0;
		String end = "Corrupt";
		
		// rowSum과 colSum의 값이 홀수일 때
		// 카운트 및 그 때의 i값을 rowIdx, colIdx에 입력
		for(int i=0; i<n; i++) {
			if(row[i] % 2 != 0) {
				rowCnt++;
				rowIdx = i;
			} 
			if(col[i] % 2 != 0) {
				colCnt++;
				colIdx = i;
			}
		}
		
		// 카운트값이 2개 이상이면 오류교정을 못하기 때문에
		// Corrupt 출력 후 종료
		if(rowCnt >= 2 || colCnt >= 2) {
			System.out.println(end);
			return;
		}
		
		// 1개씩일 경우 교정 가능 -> rowIdx, colIdx의 값+1을 출력
		if(rowCnt == 1 && colCnt == 1) {
			System.out.println("Change bit (" + (rowIdx+1) + "," + (colIdx+1) + ")");
		}
		
		// 정상이면 OK 출력
		if(rowCnt == 0 && colCnt == 0) {
			System.out.println("OK");
		}
	}
}
