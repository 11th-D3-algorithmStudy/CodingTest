package week3;

import java.io.*;
import java.util.*;


public class BOJ_2108_장현영 {
	// 산술, 중앙 최빈 범위
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE; // 범위
		int min = Integer.MAX_VALUE; // 범위 
	
		//int[] cntArr = new int[4001]; 중앙값과 최빈값을 처리하려 했으나 음수가 있어서 불가능
		// 양수 음수 나눠서 cntArr 만들어서 개수 세기 방법도 좋음
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int maxCnt = 0; // 최빈값 개수
		int sum = 0; // 산술
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(br.readLine());
			sum += x;
			// 개수 셀 때 사용하는 getordefault
			// key에 접근할 때 처음 접근하는 거면 0이 value값이 되도록 설계
			map.put(x, map.getOrDefault(x, 0) +1);
			
			// 최빈값 구하기 (stack에 한 번에 넣기 위한 작업)
			if(map.get(x) > maxCnt) {
				maxCnt = map.get(x);
			}
			// 범위
			if(x > max) max = x;
			if(x < min) min = x;
		}
		
		// 
		// map 정렬 후 최빈값 중앙값 확인한다.
		List<Integer> keyList = new ArrayList<>(map.keySet());
		
		keyList.sort(Comparator.naturalOrder());
		int cntMedian = 0;
		int median = 0;
		// 중앙값 구하기
		for(int key : keyList) {
			cntMedian += map.get(key); // 개수 count
			if (cntMedian > n/2) {
				median = key;
				break;
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		for(int key: keyList) {
			if(map.get(key) == maxCnt)
				stack.push(key);
		}
		// 최빈값이 3개 이상이면 2개까지 pop으로 날려버림
		// 최빈값이 1개라면 반복문 x
		while(stack.size() > 2)
			stack.pop();
		
		// 산술
		System.out.println(Math.round((double)sum/n));
		// 중앙
		System.out.println(median);
		// 최빈
		System.out.println(stack.pop()); // stack.peek()과 동일 top 부분 출력
		// 범위
		System.out.println(max-min);
		
	}
}
