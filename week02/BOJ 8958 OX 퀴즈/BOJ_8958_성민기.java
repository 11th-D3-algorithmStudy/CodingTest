
import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_8958_B2_OXquiz {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String[] Quiz = new String[N];
		String[] array = null;
		int sum = 0;

		// N줄 입력
		// array값 초기화
		out: for(int i=0; i<N; i++) {
			String OX = sc.next();
			
			// Quiz 배열에 OX 입력값 넣기
			Quiz[i] = OX;
			for(int j=0; j<Quiz[i].length(); j++) {
				// Quiz[i]값을 스플릿한 결과를 array 배열에 삽입
				array = Quiz[i].split("");				
			}

			int bonus = 0;
			// 현재 array의 길이만큼 반복
			for(int r=0; r<array.length; r++) {
				if(array[r].equals("O")) {
					bonus++;
					sum += bonus;
				} else if(array[r].equals("X")) {
					bonus = 0;
				} else {
					// 그 외의 값은 out 라벨링으로 예외처리
					break out;
				}
			}
			System.out.println(sum);
			// sum값 초기화
			sum = 0;
		}
	}
}
