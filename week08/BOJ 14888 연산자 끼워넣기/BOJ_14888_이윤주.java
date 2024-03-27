package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_이윤주 {
	/*
	 * Q. 연산자 끼워넣기
	 * 
	 */
	static int N, plus, minus, mul, div;
	static int[] nums;
	static int max, min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		plus = Integer.parseInt(st.nextToken());
		minus = Integer.parseInt(st.nextToken());
		mul = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		find(1); //1번부터 시작
		
		System.out.println(max);
		System.out.println(min);
	}
	
	//idx:수열에서 현재 인덱스
	private static void find(int idx) {
		if(idx == N) { //끝까지 계산했다면 최대,최소 갱신
			max = Math.max(max, nums[N - 1]); 
			min = Math.min(min, nums[N - 1]); 
			return;
		}
		
		//현재 위치에서 할 일
		int a = nums[idx - 1];
		int b = nums[idx];
		
		if(plus > 0) {
			plus--;
			nums[idx] = a + b;
			find(idx + 1);
			nums[idx] = b;
			plus++;
		}
		if(minus > 0) {
			minus--;
			nums[idx] = a - b;
			find(idx + 1);
			nums[idx] = b;
			minus++;
		}
		if(mul > 0) {
			mul--;
			nums[idx] = a * b;
			find(idx +1);
			nums[idx] = b;
			mul++;
		}
		if(div > 0) {
			div--;
			if(a < 0) {
				nums[idx] = -(-a / b);
			}else {
				nums[idx] = a / b;
			}
			find(idx + 1);
			nums[idx] = b;
			div++;
		}
	}
}

