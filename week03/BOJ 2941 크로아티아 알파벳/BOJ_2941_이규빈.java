import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2941_이규빈 {
	/*	
	 *  Q. 크로아티아 알파벳
	 * 	# 문제 요약
	 *		크로아티아 알파벳은 여러 개의 (영어) 알파벳 또는 특수기호(-,=)로 이루어진 것이 있다.
	 *		주어진 단어가 몇 개의 크로아티아 알파벳으로 이루어져 있는지 구하라.
	 *	# 풀이시간 : 40분
	 *	# 메인 접근법
	 *		문제가 되는 크로아티아 알파벳들은 모두 마지막 자리가 =, -, 또는 j로 끝난다.
	 *		따라서 주어진 단어를 스택에 넣고, pop하며 위 문자를 만나면 크로아티아 알파벳인지 판단 후, 맞다면 한 글자로 센다.
	 *	# 막힌 부분 해결
	 *		런타임 에러 (EmptyStack) 발생
	 *		-> 마지막 남은 문자가 =,-일리는 없지만, z,j인 경우 위 에러가 발생할 수도 있음을 깨달음.
	 *			따라서 z,j인 경우 스택이 비어있지 않을 때만 peek하는 코드를 추가해 해결.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		
		// 입력받은 단어를 스택에 넣기
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
		}
		
		// 하나씩 pop하며 문제가 되는 크로아티아 알파벳인지 판단
		// - i++의 의미 : 한 반복 사이클에서 문자 여러 개를 pop한 경우, 반복문의 반복 횟수를 그만큼 줄여줘야 한다.
		// - res++의 의미 : 여러개의 (영어) 알파벳이 크로아티아 알파벳 1개에 해당해 카운트 한 것이다.
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = stack.pop();
			if (c == '=') { // c=, s=, z=인지, dz=인지 판단하는 작업이 필요
				char c2 = stack.pop(); // c, s 또는 z 버리기
				i++;
				if (!stack.isEmpty() && c2 == 'z' && stack.peek() == 'd') {
					stack.pop(); // d 버리기
					i++;
				}
				res++;
			} else if (c == '-') { // 무조건 c-, d- 중 하나이므로, 앞 문자를 볼 필요 없다.
				stack.pop(); // c 또는 d 버리기
				i++;
				res++;
			} else if (c == 'j') { // lj 또는 nj인지 판단하는 작업이 필요
				if (!stack.isEmpty() && (stack.peek() == 'l' || stack.peek() == 'n')) {
					stack.pop(); // l 또는 n 버리기
					i++;
				}
				res++;
			} else { // 일반적인 알파벳인 경우
				res++;
			}
		}
		
		// 결과 출력
		System.out.println(res);
	}
}