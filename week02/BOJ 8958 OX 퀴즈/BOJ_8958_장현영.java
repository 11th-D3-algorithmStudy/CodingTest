package week2;

import java.util.Scanner;

// ox 퀴즈
// 1,2 쌓아가면서 없어지는 것을 보니 숫자로 푸는 식을 생각
// o를 만나면 1개씩 쌓고 x 만나는 순간 0으로 초기화
// 약 20분 소요


public class BOJ_8958_장현영 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int t=0; t<N;t++) {
			String str = sc.next();
			// 정답 변수와 더하는 기준의 cnt변수 설정
			int answer = 0;
			int cnt = 0;
			for(int i=0;i<str.length();i++) {
				if(str.charAt(i) == 'O')
					answer += ++cnt; 
				else
					cnt = 0;
			}
			System.out.println(answer);
		}
	
		
	}
}
