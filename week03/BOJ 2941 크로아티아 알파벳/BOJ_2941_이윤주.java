import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2941_이윤주 {
	public static void main(String[] args) throws Exception {
		//크로아티아 알파벳
		//2시 51분 시작 3시 27분 끝 - 풀이시간 : 36분
		//입력 : 최대 100글자 단어 알파벡 소문자, -, = 로만 이루어짐
		//출력 : 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력
		//단어 입력 받으면서 크로아티아 알파벳 조건에 맞는지 확인해서 카운트하기
		//String 메서드에서 쓸만한 게 있는지 확인해보니까
		//replace 로 줄어든 개수 차이로 세는 것을 사용해보자!!
		//replace를 빈 문자열""로 했을 때 ddz=z= -> dz= -> 카운트 한번 더 되는 문제 발생!
		//해결하기 위해 replace "*"로 하고 나서 최종 길이를 구하는 것으로 수정!!
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력되는 단어, 단어의 길이
		String input = br.readLine();
		int size = input.length();
		
		//크로아티아 알파벳 개수를 세는 변수
		int count = 0;
		
		//크로아티아 알파벳 빼버린 문자열
		String alphabet = input;
		
		//c=, c-, dz=, d-, lj, nj, s=, z= : *로 치환하기
		alphabet = alphabet.replace("c=", "*");
		alphabet = alphabet.replace("c-", "*");
		alphabet = alphabet.replace("dz=", "*");
		alphabet = alphabet.replace("d-", "*");
		alphabet = alphabet.replace("lj", "*");
		alphabet = alphabet.replace("nj", "*");
		alphabet = alphabet.replace("s=", "*");
		alphabet = alphabet.replace("z=", "*");
		
		//문자열 길이 = *개수 + 치환되지 않은 알파벳 개수 = 총 크로아티아 알파벳 개수
		count = alphabet.length();

		System.out.println(count);
	}

}
