package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24061_정다운 {
	
	static int N, K;
	static int[] arr, sortedArr; // 원본배열, 정렬시 사용할 배열
	static int cnt = 0; // 몇번째 저장임? 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// 병합정렬 구현 고고
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		sortedArr = new int[N];
		
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		
		mergeSort(0, N-1);
		
		if (cnt >= K) {
			System.out.println(sb);
		} else {
			System.out.println("-1");
		}
	}

	static void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left+right)/2;
			mergeSort(left, mid);
			mergeSort(mid+1, right);
			merge(left, mid, right);
		}
	}

	static void merge(int left, int mid, int right) {
		// 사용할 포인터를 만들자 ~
		int L = left; // 왼쪽 배열 시작위치
		int R = mid+1; // 오른쪽 배열 시작위치
		int idx = left; // 정렬한 값 넣어줄 인덱스
		
		// 서로 비교할 값 남아있는 동안
		while (L <= mid && R <= right) {
			// 작은 값을 idx에 넣자
			if (arr[L] <= arr[R]) {
				sortedArr[idx++] = arr[L++];
			} else {
				sortedArr[idx++] = arr[R++];
			}
		}
		
		// 왼쪽이 남았다 ? 
		if (L <= mid) {
			// 남은거 추가하세염
			for (int i=L; i<=mid; i++) {
				sortedArr[idx++] = arr[i];
			}
		} else {
			for (int i=R; i<=right; i++) {
				sortedArr[idx++] = arr[i];
			}
		}
		
		// 원본 배열에 반영 !!!!
		for (int i=left; i<=right; i++) {
			arr[i] = sortedArr[i];
			cnt++;
			if (cnt == K) {
				for (int j=0; j<N; j++) {
					sb.append(arr[j]+" ");
				}
			}
		}
		
	}
}
