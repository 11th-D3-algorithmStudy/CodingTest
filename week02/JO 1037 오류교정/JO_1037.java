import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1037 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] innerArr = new int[n];
			for (int j = 0; j < n; j++) {
				innerArr[j] = Integer.parseInt(st.nextToken());
			}
			arr[i] = innerArr;
		}
		
		int oddRow = -1;
		int oddCol = -1;
		boolean isCorrect = true; 
		
		for (int r = 0; r < n; r++) {
			int sum = 0;
			for (int c = 0; c < n; c++) {
				sum += arr[r][c];
			}
			if (sum % 2 != 0) {
				if (oddRow == -1) { // 만약 첫번째로 나온 홀수값일 경우
					oddRow = r;		// oddRow에 해당 row 할당
				}else {
					isCorrect = false; // 홀수인 row가 2개 이상일 경우 항상 false
					break;
				}
			}
		}
		if (isCorrect) {
			for (int c = 0; c < n; c++) {
				int sum = 0;
				for (int r = 0; r < n; r++) {
					sum += arr[r][c];
				}
				if (sum % 2 != 0) {
					if (oddCol == -1) {
						oddCol = c;
					}else {
						isCorrect = false;
						break;
					}
				}
			}
		}
		if (isCorrect) { // 허용범위 안일 경우만
			if (oddCol == -1 && oddRow == -1) {  // 홀수인 행과 열이 나오지 않았을 때
				System.out.println("OK");
			}else if (oddCol != -1 && oddRow != -1) { // 홀수인 행과 열이 하나씩 나왔을 때
				System.out.println("Change bit (" + (oddRow+1) + "," +(oddCol + 1) + ")");
			}else { // 행 혹은 열중 하나만 홀수일 때
				isCorrect = false;
			}
		}
		if (!isCorrect) {
			System.out.println("Corrupt");
		}
	}
}