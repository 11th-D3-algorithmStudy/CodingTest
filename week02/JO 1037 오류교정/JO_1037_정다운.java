package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 오류교정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 행렬 크기
		int N = Integer.parseInt(br.readLine());

		// 2차원 배열
		int[][] parityArr = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				parityArr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 행의 합, 열의 합 배열 2개 생성 후
		// 모든 요소가 짝수 -> OK
		// 홀수인 요소가 각 1개씩 있는 경우 -> change bit (r,c)  // (r,c = idx+1)
		// 그 외 -> Corrupt
		int[] rSumArr = new int[N];
		int[] cSumArr = new int[N];

		// 행의 합
		for (int r = 0; r < N; r++) {
			int rSum = 0;
			for (int c = 0; c < N; c++) {
				rSum += parityArr[r][c];
			}
			rSumArr[r] = rSum;
		}

		// 열의 합
		for (int c = 0; c < N; c++) {
			int cSum = 0;
			for (int r = 0; r < N; r++) {
				cSum += parityArr[r][c];
			}
			cSumArr[c] = cSum;
		}
		
		// 각 배열에서 홀수인 요소의 인덱스를 확인한다
		List<Integer> rOddList = getOddElement(rSumArr);
		List<Integer> cOddList = getOddElement(cSumArr);
		
		// 홀수인 요소가 없다
		if (rOddList.size() == 0 && cOddList.size() == 0) {
			System.out.println("OK");
		} // 홀수인 요소가 각 1개씩 있다
		 else if (rOddList.size() == 1 && cOddList.size() == 1) {
			System.out.println("Change bit ("+rOddList.get(0)+","+cOddList.get(0)+")");
		} // 그 외 
		 else {
			System.out.println("Corrupt");
		}

	}
	
	public static List<Integer> getOddElement(int[] arr) {
		List<Integer> oddList = new ArrayList<>();
		
		for (int i=0; i<arr.length; i++) {
			if (arr[i] % 2 != 0) { // 요소가 홀수이면
				oddList.add(i+1); // 해당 인덱스+1을 리스트에 입력
			}
		}
		
		return oddList;
	}
}
