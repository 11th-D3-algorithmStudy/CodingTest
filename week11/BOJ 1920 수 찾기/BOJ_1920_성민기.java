package AlgorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_성민기 {
	
	/* 한달전에 풀음
	 * 수업시간에 배운 이분탐색을 활용했었음
	 * 정렬된 값을 가지고 탐색해야 하는점 주의
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 입력값 
		int N = Integer.parseInt(br.readLine());
		int[] jungsu = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			jungsu[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬해준다
		Arrays.sort(jungsu);
		
		int M = Integer.parseInt(br.readLine());
		int[] exist = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			exist[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색 실시
		for(int i=0; i<M; i++) {
			int result = search(jungsu, exist[i]);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int search(int[] arr, int M) {
		int left = 0;
		int right = arr.length-1;
		// left가 right를 넘어갈 때 까지
		// 여기서 이분탐색의 부호에 따라 설정하는 것이 달라짐
		// left < right로도 가능
		// M <= arr[mid]로 해버린다거나 등등의 다양한 방법이 있다고 함.
		while(left <= right) {
			int mid = (left+right)/2;
			if(M == arr[mid]) return 1;
			else if(M < arr[mid]) right = mid-1;
			else if(M > arr[mid]) left = mid+1;
		}
		return 0;
	}
}