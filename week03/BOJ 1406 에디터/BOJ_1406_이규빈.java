// 시간 초과

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1406_이규빈 {
	/*
	 *  Q.에디터
	 *	# 문제 요약
	 *		문자열과 M개의 명령어가 주어진다. 모든 명령어를 수행한 후의 문자열을 구하시오.
	 *	# 풀이시간 : : 1시간 40분
	 *	# 메모리/시간 : 
	 *	# 메인 접근법
	 *		1. 삭제와 추가가 빈번한 경우이므로, LinkedList를 활용한다.
	 *		2. 커서의 인덱스를 요소들 인덱스의 중간 숫자라고 가정한다.
	 *			예컨대 1번 요소와 2번 요소 사이에 커서가 있는 경우, 1.5 인덱스라고 본다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 연결리스트에 주어진 문자열 저장
		String str = br.readLine();
		LinkedList<Character> list = new LinkedList<>();
		list.add(null); // 계산 편의를 위해, 맨 앞에 빈 노드 추가
		for (int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		// 커서의 최초 위치
		// - 맨 뒤 문자의 인덱스 + 0.5
		double cur = str.length() + 0.5;
		
		// 명령어 수행
		int M = Integer.parseInt(br.readLine());
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
				case "L": {
					if (cur != 0.5) { // 커서가 맨 앞이면 무시
						cur--; 
					}
					break;
				}
				case "D": {
					if (cur != list.size() - 0.5) { // 커서가 맨 뒤면 무시
						cur++;
					}
					break;
				}
				case "B": {
					if (cur != 0.5)  { // 커서가 맨 앞이면 무시
						list.remove((int) cur); // int로 강제 타입변환시 소수점 이하가 버려지므로, 왼쪽 문자의 인덱스가 된다.
						cur--; // 커서가 삭제요소의 다음 요소의 뒤로 가므로, -1 해줘야 한다.
					}
					break;
				}
				case "P": {
					char c = st.nextToken().charAt(0);
					list.add((int)(cur + 0.5), c); // 커서 다음 위치에 문자 넣기
					cur++; // 커서가 추가요소의 앞이 되므로, +1 해줘야 한다.
					break;
				}
			}
		}
		
		// 결과 출력
		StringBuilder res = new StringBuilder();
		for (int i = 1; i < list.size(); i++) { // 맨 앞의 빈 노드는 제외
			res.append(list.get(i));
		}
		System.out.println(res);
	}
}
