package week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;


public class BOJ_1406_정다운 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		// 문자열을 char로 쪼개서 LinkedList에 넣기
		LinkedList<Character> editList = new LinkedList<>();
		for (int i=0; i<input.length(); i++) {
			editList.add(input.charAt(i));
		}
		
		// 명령어 개수
		int M = Integer.parseInt(br.readLine());
		
//		// 커서 위치를 따로 선언해서 LinkedList를 탐색하면 시간초과
//		int cursor = input.length();
		
		// 시간초과 해결 방법 -> listIterator
		// https://zangzangs.tistory.com/70
		ListIterator<Character> iter = editList.listIterator();
		
		// 초기 커서위치 맨 뒤로 설정 
		while (iter.hasNext()) {
			iter.next();
		}
		
		// editor
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			char command = st.nextToken().charAt(0);
			
			switch (command) {
				case 'L': {
					if (iter.hasPrevious()) {
						iter.previous();
					}
					break;
				}
				case 'D': {
					if (iter.hasNext()) {
						iter.next();
					}
					break;
				}
				case 'B': {
					if (iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
					break;
				}
				case 'P': {
					char $ = st.nextToken().charAt(0);
					iter.add($);
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		
		// 일반 for문 돌면 시간초과... LinkedList는 for each문을 쓰자 ~ 
		// https://siyoon210.tistory.com/99
		/*
			[LinkedList]
			for-each문 = 19,774.56 ns
			일반 for문 = 151,597.54 ns
			
			LinkedList의 경우는 For-each문이 압도적으로 빠른 속도가 나왔습니다. 약 7.7배 정도 빠르게 나왔습니다. (list의 사이즈가 커질 수록 더 많은 격차가 예상됩니다.)
			(for-each문이 더 빠른이유는 내부적으로 iterator를 사용하기 때문입니다.)
		 */
		for (Character x : editList) {
			sb.append(x);
		}
		
		System.out.println(sb);
		
	}
}
