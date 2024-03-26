package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_5568_이윤주 {
	/*
	 * Q.카드 놓기
	 * 카드 n(4~10)장, 각 카드 1~99 정수, k(2~4)장 선택, 나란히 정수를 만듬
	 * 만들 수 있는 정수의 개수는 총 몇가지?
	 * -> 조합?
	 * -> 전에 숫자 만들기랑 유사
	 * -> dfs 로 set에 숫자 담아서 중복제거
	 */
	static int N, K;
	static int[] cards;
	static boolean[] visited;
	static Set<String> nums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		cards = new int[N];
		visited = new boolean[N];
		nums = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(br.readLine());
		}
		
		makeNum(0, "");
		
		System.out.println(nums.size());
	}
	
	//N개의 숫자 중에 K개 뽑아서 숫자 String으로 만들어서 set에 넣는 메서드
	//idx : 지금 뽑은 숫자 개수
	private static void makeNum(int idx, String num) {
		//기저조건
		if(idx == K) { //숫자 K개 다 뽑으면
//			System.out.println(num);
			nums.add(num);
			return;
		}
		
		//현재 상태에서 할 것
		//재귀부분
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				makeNum(idx + 1, num + cards[i]);
				visited[i] = false;
			}
		}
	}
}
