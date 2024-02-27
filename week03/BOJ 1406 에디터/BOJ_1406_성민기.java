import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class BOJ_1406_성민기 {
	public static void main(String[] args) throws IOException {
		
		/* 풀이시간 : 240217 20:53 ~ 22:45
		 * 메인접근법
		 *     - 입력받은 문자에 해당할 때 커서를 이동시키며 에디터의 명령을 수행한다
		 * 
		 * 막힌 부분 : 시간 초과
		 *     - ArrayList로 구현한 것을 LinkedList로 구현하여 remove 시간 단축 -> 시간초과
		 *     						(LinkedList의 삭제 시간복잡도 : O(1))
		 * 	   - if-elseif 조건문을 switch문으로 변경 -> 시간초과
		 *     - ListIterator 사용해도 시간초과난 경우 생김
		 *          -> StringTokenizer 사용하지 않고 한 라인에 입력받은 것을 charAt을 통해 
		 *             charAt(2)로 추가 입력한 것으로 함
		 *     - 그래도 시간초과 나서 리스트값을 출력하는 부분을 수정
		 *          수정 전->  for(int i=0; i<editor.size(); i++) {
		 *					    sb.append(editor.get(i));
		 * 				     }
		 * 			
		 * 			수정 후->  for(Character result : editor) {
		 *						sb.append(result);
		 *					 }
		 * 
		 * 해결 방법 : ListIterator<>에 대해서 추가 공부(메소드 사용)
		 * 
		 * 메모리 : 76624 KB
		 * 시간 : 468 ms
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		int T = Integer.parseInt(br.readLine());
		LinkedList<Character> editor = new LinkedList<>();

		for(int i=0; i<line.length(); i++) {
			editor.add(line.charAt(i));
		}
		
		ListIterator<Character> iter = editor.listIterator();
		// 커서를 맨 뒤로 옮기는 작업
		// hasNext() -> 다음 요소 가지고 있으면 true 리턴
		while(iter.hasNext()) {
			iter.next();
		}
		
		for(int t=0; t<T; t++) {
			String alphabet = br.readLine();
			char c = alphabet.charAt(0);
			switch(c) {
			case 'L': 
				if(iter.hasPrevious()) iter.previous();
				break;
			case 'D': 
				if(iter.hasNext()) iter.next();
				break;
			case 'B': 
				// 이전값이 있으면 커서를 역방향으로 이동시킨 후 값 제거
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
				break;
			case 'P': 
				char t1 = alphabet.charAt(2);
				iter.add(t1);
				break;
			default : break;
			}
		}
		
		for(Character result : editor) {
			sb.append(result);
		}
		System.out.println(sb);
	}
}
