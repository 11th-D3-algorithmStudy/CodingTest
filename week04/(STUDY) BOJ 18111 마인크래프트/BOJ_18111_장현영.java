package week04;

import java.io.*;
import java.util.*;

public class BOJ_18111_장현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];

		// +-1 단위이므로 높이가 이 범위 내에서만 정답이 나올거라고 판단
		int max = 0;
		int min = 257; // 최대 256
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[r][c]);
				min = Math.min(min, arr[r][c]);
			}
			
		}
		// 자 여기서 생각할 점
		// 1. 모든 경우의 수를 다 본 뒤에 최소 시간과 그 때의 높이를 뽑아내기
		// 2. 어차피 블록은 처리할 수 있는 작업이 +-1 단위 이므로 MAX와 MIN 사이에서 정답이 될 수 밖에 없음
		// 3. 가장 중요한 게 인벤토리의 block의 개수를 최대한 댕겨온다음 가능한지 판단하기
		
		// 그전에 cntArr로 접근해보기
		int[] cntArr = new int[257]; 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cntArr[arr[i][j]]++;
			}
		}
		
		// min으로 높이 맞추기는 모든 블록을 그만큼 깎으면 되기 때문에 무조건 답이 나옴
		// b에 상관없이 --작업(2초)은 무한 진행 가능 // 선작업
		// b가 더이상 없다면 ++ 작업(1초) 불가능    // 후작업
		
		int ansHeight=0; // 높이
		int ansTime = Integer.MAX_VALUE; // 시간

		int hsum; // higher sum
		int lsum; // lower sum
 		for(int curr=min; curr <=max; curr++) {
 			// height 기준에 맞게 일단 큰 것들만큼 빼주고(블럭 최대 확보)
 			hsum =0;
 			lsum =0;
 			int seconds;
 			// b에 상관없이 --작업(2초)은 무한 진행 가능 // 선작업
 			for(int higher=curr+1; higher<=max; higher++) { // 기준보다 큰 것들(higher)
 				hsum += cntArr[higher] * (higher-curr); // higher =3 기준 curr 1이면 차이만큼 * 진행
 			}
 			// ++ 작업(1초) 채우기 위한 작업    // 후작업
 			for(int lower=curr-1; lower>=min; lower--) { // 기준보다 큰 것들(lower)
 				lsum += cntArr[lower] * (curr-lower);
 			}	
 			if (hsum + b < lsum) // 인벤토리가 부족하다면
 				continue;
 			// 
 			
 			// 시간
			seconds = hsum*2 + lsum;
 			// 시간 재기
			if (seconds <= ansTime) { // 최소 시간 경신
				ansTime = seconds;
				ansHeight = curr; // ansTime에 해당하는 값이 2개라도 curr은 큰 값에서 update 됨
			}
 			
		}
		
		System.out.println(ansTime + " "+ ansHeight);

	}
}
