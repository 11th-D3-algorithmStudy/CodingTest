package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24091_이윤주 {
	/*
	 * Q. 퀵 정렬2
	 * 
	 */
	
	static int N, K, count;
	static int[] nums, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		
		quick_sort(0, nums.length - 1);
		
		if(count < K) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for(int i : result) {
				sb.append(i + " ");
			}
			System.out.println(sb);
		}
	}
	//오름차순 정렬하는 퀵소트
	private static void quick_sort(int left, int right) {
		if(left < right) {
			int pivot = partition(left, right); //분할
			quick_sort(left, pivot - 1); 		//왼쪽 부분 정렬
			quick_sort(pivot+1, right);			//오른쪽 부분 정렬
		}
	}
	private static int partition(int left, int right) {
		int x = nums[right];	//기준원소
		int i = left - 1; 		//x보다 작거나 작은 원소들의 끝지점
		for(int j = left; j < right; j++) {
			if(nums[j] <= x) { //i값 증가 후 교환
				int tmp = nums[++i];
				nums[i] = nums[j];
				nums[j] = tmp;
				if(++count == K) {
					result = nums.clone();
				}
			}
		}
		if(i + 1 != right) { //i+1과 right가 다르면 교환
			int tmp = nums[i+1];
			nums[i + 1] = nums[right];
			nums[right] = tmp;
			if(++count == K) {
				result = nums.clone();
			}
		}
		return i + 1;
	}
}
