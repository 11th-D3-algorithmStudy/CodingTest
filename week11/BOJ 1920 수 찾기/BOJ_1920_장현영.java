package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1920_장현영 {

	// 수 찾기
	// 100000개 내에서 존재하는 A 파악하기
	// 이분 탐색으로 현재 있는 수 있는지 확인
	// 초기엔 arr에 답 넣어서 출력했는데
	// 풀이시간이 오래걸려서 다른 답보니 stringbuilder 안써서 2배이상 시간 소요
	// 시간 모자라면 stringbuilder 꼭 기억하기 (오랜만에 사용) 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st1.nextToken());
		}
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (st2.hasMoreTokens()) {
			int target = Integer.parseInt(st2.nextToken());
			int start = 0;
			int end = n - 1;
			boolean flag = true;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (arr[mid] < target) { // 오른쪽
					start = mid + 1;
				} else if (arr[mid] > target) // 왼쪽
					end = mid - 1;
				else {
					sb.append(1).append("\n");
					flag = false;
					break;
				}

			}
			if (flag)
				sb.append(0).append("\n");
		}
		
		System.out.print(sb);
		
	}
}
