package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_24061_성민기 {

	/* 풀이시간 : 240323 15:10 ~ 17:15
	 * 메인접근법
	 *     - 수업시간에 배운 병합 정렬 사용 
	 * 
	 * 막힌 부분 
	 *     1. 인덱스 에러 -> 이건 왜 일어난지도 잘 모르겠다. 복붙해서 다시 했더니 해결됨
	 *     2. 틀렸습니다
	 *         - 정렬의 횟수를 카운트하는 경우를 잘못 설정하였음
	 *           -> 정렬 했을 때의 횟수를 카운트해야하는 것이지 값을 넣을때마다 count하는 것이 아니다
	 *              따라서 정렬된 결과 원상 복구할 때 count++와 if(count==M)이라는 조건을 추가
	 *     3. 시간 초과 -> StringBuilder를 이용하여 해결
	 *           
	 * 메모리 : 129196 KB / 시간 : 852 ms
	 */
	
	static int[] arr;
	static int[] sortedArr;
	static int N, M, count;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		sortedArr = new int[N];
		count = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		mergeSort(0, N - 1);
		if (count < M) {
			System.out.println(-1);
			return;
		}
		for (int i = 0; i < N; i++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb);
	}

	public static void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, mid, right);
		}
	}

	public static void merge(int left, int mid, int right) {
		int L = left;
		int R = mid + 1;
		int idx = left;
		if (count >= M) return;
		
		while (L <= mid && R <= right) {
			if (arr[L] <= arr[R]) {
				sortedArr[idx++] = arr[L++];
			} else {
				sortedArr[idx++] = arr[R++];
			}
		}

		if (L <= mid) {
			for (int i = L; i <= mid; i++) {
				sortedArr[idx++] = arr[i];
			}
		} else {
			for (int i = R; i <= right; i++) {
				sortedArr[idx++] = arr[i];
			}
		}

		// 정렬값 원상복귀
		for (int i = left; i <= right; i++) {
			arr[i] = sortedArr[i];
			// 여기서 카운트를 해줘야한다.
			count++;
			// 같은 경우 바로 멈추도록
			if (count == M) break;
		}
	}
}
