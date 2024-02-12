package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ATM {
	public static void main(String[] args) throws IOException {
		// 돈 뽑는 순서의 우선순위를 부여하자~~
		// 걸리는 시간 배열 정렬 후 사용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사람 수
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 걸리는 시간 배열 선언 & 정렬 
		int[] timeArr = new int[N];
		for (int i=0; i<N; i++) {
			timeArr[i] = Integer.parseInt(st.nextToken());
		}
		insSort(timeArr);
		
		// 인출 시간
		int totTime = 0;
		// 앞 사람(들)의 인출시간 저장 변수
		int addTime = 0;
		
		// 배열 돌면서 각 사람 당 걸리는 인출 시간 구하기
		for (int i=0; i<N; i++) {
			if (i>0) { // 두번째 사람부터 앞사람 인출시간 고려
				addTime += timeArr[i-1]; 
			}
			// 인출시간 = 내 인출시간(timeArr[i] + 앞사람 인출 시간(addTime)
			totTime += addTime;
			totTime += timeArr[i];
		}
		
		System.out.println(totTime);
	}
	
	public static void insSort(int[] arr) {
		for (int i=1; i<arr.length; i++) {
			int key = arr[i];
			
			int j;
			for (j=i-1; j>=0 && arr[j] > key; j--) {
				arr[j+1] = arr[j];
			}
			
			arr[j+1] = key;
		}
	}
}
