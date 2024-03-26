package week08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_24061_장현영 {
	// 시간초과
	static int n, k, cnt;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st1.nextToken());
		k = Integer.parseInt(st1.nextToken());
		arr = new int[n];
		cnt = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		mergeSort(0, n - 1); // 0~n-1
		bw.write(sb.toString());
		if (cnt < k) { // k보다 작은경우 -1
			System.out.println(-1);
		} 
		bw.flush();
		bw.close();
	}

	static void mergeSort(int start, int end) {
		if (start >= end)
			return;
		int mid = (start + end) / 2;
		mergeSort(start, mid);
		mergeSort(mid + 1, end);
		if (cnt >= k)
			return; // k찾으면 조기종료
		merge(start, end, mid);
	}

	static void merge(int start, int end, int mid) {
		int[] buff = new int[n];
		int l = start;
		int r = mid + 1;
		int idx = start;
		while (l <= mid && r <= end) {
			if (arr[l] < arr[r]) {
				buff[idx++] = arr[l++];
			} else {
				buff[idx++] = arr[r++];
			}
		}

		while (l <= mid) {
			buff[idx++] = arr[l++];
		}

		while (r <= end) {
			buff[idx++] = arr[r++];
		}

		// 출력
		int i = 0;
		l = start;
		while (l <= end) {
			arr[l++] = buff[i++];
			if (++cnt == k) {
				for (int j = 0; j < n; j++) {
					sb.append(arr[j]).append(" ");
				}
				sb.append("\n");
				return;
			}
		}
	}

}
