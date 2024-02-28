import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BOJ_2941_성민기 {
	public static void main(String[] args) throws IOException {
		
		/*
		 * 풀이시간 : 240217 16:20 ~ 16:55
		 * 메인접근법 : String으로 입력값을 받은 후 charAt을 통해 문자를 하나하나 비교하는 방안을 생각
		 * 			 추가로 겹치는 알파벳이 있다면 if 조건문을 줄이려고 함.
		 *           
		 * 막혔던 부분 : 코드를 줄이려고 시도하다가 오류가 계속나서 시간이 지체됨
		 *            추가문제 : charAt만으로 비교하니 런타임 에러 (StringIndexOutOfBounds) 발생
		 * 해결 방법 : (croatia.charAt(i+1) == '=' || croatia.charAt(i+1) == '-')
		 *          여기 부분에서 괄호를 잘못쳐서 나왔었던 인덱스 에러 -> 윗줄과 같이 고침
		 *          배열을 String의 크기보다 +2하여 선언하여 활용
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = 0;
		String croatia = br.readLine();
		char[] alpha = new char[croatia.length()+2];
		for(int i=0; i<croatia.length(); i++) {
			alpha[i] = croatia.charAt(i);
		}
		
		for(int i=0; i<alpha.length; i++) {
			if (alpha[i] == 'c' && 
					(alpha[i+1] == '=' || alpha[i+1] == '-')) {
				count++; i += 1;
			} else if(alpha[i] == 'd' && alpha[i+1] == '-') {
				count++; i += 1;
			} else if(alpha[i] == 'd' && alpha[i+1] == 'z'
					&& alpha[i+2] == '=') {
				count++; i += 2;
			} else if((alpha[i] == 'n' || alpha[i] == 'l') && alpha[i+1] == 'j') {
				count++; i += 1;
			} else if((alpha[i] == 'z' || alpha[i] == 's') && alpha[i+1] == '=') {
				count++; i += 1;
			} else if('a' <= alpha[i] && alpha[i] <= 'z') {
				count++;
			}
		}
		System.out.println(count);
	}
}
