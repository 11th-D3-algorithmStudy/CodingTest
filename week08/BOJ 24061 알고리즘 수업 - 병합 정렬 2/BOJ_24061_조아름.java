package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24061_조아름 {
	static int N, K, cnt = 0;
	static int[] arr, newArr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 배열의 크기
		K = Integer.parseInt(st.nextToken()); // 변경 횟수

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		newArr = new int[N]; // 배열을 메인에 선언해주어야 함 ㅠㅠㅠ!!!!
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		mergeSort(0, N - 1);
		if(cnt<K) {
			System.out.println(-1);
			}

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
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {
			if (arr[l] <= arr[r]) {
				newArr[idx++] = arr[l++];
			} else {
				newArr[idx++] = arr[r++];
			}
		}

		if (l <= mid) {
			for (int i = l; i <= mid; i++) {
				newArr[idx++] = arr[i];
			}
		} else {
			for (int i = r; i <= right; i++) {
				newArr[idx++] = arr[i];
			}
		}
		
		for(int i=left;i<=right;i++) {
			arr[i] = newArr[i];
			cnt++;
			if(cnt == K) {
				for(int k=0;k<arr.length;k++) {
					sb.append(arr[k]+ " ");
				}
				System.out.println(sb);
				break;
			}
		}
		
		
	}

}
