package week05;

import java.io.*;
import java.util.*;

public class BOJ_19637_장현영 {
	// 이미 정렬된 상태에서 위치를 찾는다 + 시간이 촉박해보인다 + 입력값의 범위가 매우 크다
	// 이분탐색
	// 처음엔 완전탐색으로 진행하다가 시간초과
	// list 써서 뒤에 들어온 중복값 아예 배제시키기!

	static List<String> names;
	static List<Integer> points;
	static int listLen; // 담긴 리스트의 길이
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		names = new ArrayList<>();
		points = new ArrayList<>();
		int temp = -1;

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int p = Integer.parseInt(st.nextToken());
			if(temp < p) {
				names.add(name);
				points.add(p);
				temp = p;
				listLen++;
			}
		}
		int num;
		
		for(int i=0;i<m;i++) {
			num = Integer.parseInt(br.readLine());
			sb.append(binarySearch(num)).append("\n");
		}
		System.out.print(sb);
	}
	public static String binarySearch(int num) {
		int left = 0;
		int right = listLen -1;
		int mid;
		int idx = right; // 정답 index 줄여나가는 과정이므로 right이 max
		while(left <= right) {
			mid = (left+right) / 2;
			if(num <= points.get(mid)) { // 왼쪽을 보자
				idx = Math.min(idx, mid);
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		return names.get(idx);
	}
}
