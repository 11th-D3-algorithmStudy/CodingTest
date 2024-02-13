package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// atm
// 예시에서 정렬을 해서 앞 순서대로 해당하는 시간만큼 합을 뽑아내는 것이 가장 최솟값으로 판단함
// 반례가 따로 없음
// n이 1000개까지 있으므로 최대한 효율적으로 푸는 방식 생각해보기
// 더해줄 값을 항상 저장해주면서 정렬된 값의 최솟값에 접근
// 풀이시간 약 20분 소요


public class BOJ_11399_장현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		//array에 담고 정렬
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n;i++) { 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int answer = 0;
		int cnt = 0;
		for(int i=0;i<n;i++) {
			cnt += arr[i];
			answer += cnt;
		}
		
		System.out.println(answer);
		
	}
}
