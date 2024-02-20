package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2941_장현영 {
	// 처음엔 if-else if문으로 풀다가 꼬여서 (약 1시간 이상)
	// contains에 해당하면 replace하는 방식으로 생각(10분)
	// 풀이는 간단하나 이렇게 풀면 대체하는 모든 상황을 생각해야한다는 단점이 있음
	// 추가 : replace와 replaceAll 차이 알기
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		// dz=, z= 순서 주의
		String[] croatia = {"dz=","z=","c=","c-","d-","lj","nj","s="};
		
		for(int i=0; i<8;i++) {
			if(str.contains(croatia[i]))
				str = str.replace(croatia[i], "!");
			// replace는 해당 특정 문자열 모두 바꿈(한번만 바꾸는 것인 줄 알고 찾아보니 모두 바꿈)
			// replaceAll은 정규식으로 표현된 regex에 포함이 된다면 바꾸는 형식
			// 숫자나 특수문자 등 일정한 규칙이 있을 때 문자열 전처리엔 정규식으로 접근하는 게 좋음
			// 참고: https://bada744.tistory.com/16
		}
		System.out.println(str.length());

	
	}
}


