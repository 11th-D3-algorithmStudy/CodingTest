import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1406_이윤주 {
	public static void main(String[] args) throws Exception {
		//에디터
		//최대 6*10^5
		//3시 57분 시작 4시 39분 끝
		//L : 커서 왼쪽 한칸 이동
		//D : 커서 오른쪽으로 한칸 이동
		//B : 커서 왼쪽 문자 삭제
		//P 문자 : 문자 커서 왼쪽에 추가
		
		//지정한 곳 삭제하고 추가하는 기능이 있어서 
		//링크드리스트로 구현하는게 좋을 것 같다
		//커서의 포인터 역할을 하는 변수도 추가하는게 좋겠다
		//커서 위치 문장의 맨 앞 = 0, 문장의 맨 뒤 = list.size
		
		//시간초과 발생...
		//
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력 첫줄 문자열 
		String input = br.readLine();
		
		int n = input.length();
		
		//명령어를 수행하기 위해 문자열을 문자 하나씩 링크드리스트에 넣기
		List<Character> list = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			list.add(input.charAt(i));
		}
		
		//커서의 위치를 기억하는 변수 - 초기 위치 : 문장의 맨 뒤
		int cursor = list.size();
		
		//명령어의 개수 m
		int m = Integer.parseInt(br.readLine());
		
		//명령어 m개 받기
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char instruction = st.nextToken().charAt(0);
			
			if(instruction == 'L') {
				//명령어 - L : 커서 왼쪽 한칸 이동
				if(cursor == 0) //커서가 맨 앞이면 무시
					continue;
				cursor--;
				
			} else if(instruction == 'D') {
				//명령어 - D : 커서 오른쪽으로 한칸 이동
				if(cursor == list.size()) //커서가 맨 뒤면 무시
					continue;
				cursor++;
				
			} else if(instruction == 'B') {
				//명령어 - B : 커서 왼쪽 문자 삭제
				if(cursor == 0) //커서가 맨 앞이면 무시
					continue;
				list.remove(--cursor); //커서 왼쪽으로 옮기고 그 위치 원소 삭제
			} else {
				//명령어 - P 문자 : 문자 커서 왼쪽에 추가
				char c = st.nextToken().charAt(0);
				list.add(cursor++, c); //커서 위치에 추가 하고 커서 오른쪽으로 옮김
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(char c : list) {
			sb.append(c);
		}
		
		//문자열 출력
		System.out.println(sb);
		
	}
}
