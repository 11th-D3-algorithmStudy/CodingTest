// 미완

import java.util.Scanner;

public class BOJ_1296_이규빈 {
	/*
	 * 	BOJ 1296. 팀 이름 정하기
	 * 	- 연두의 영어이름과 팀이름의 L, O, V, E의 개수를 조합해 확률이 가장 높은 팀을 찾기
	 */
	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		int N = sc.nextInt();
		sc.nextLine();  // 개행문자 제거
		
		String arr[] = new String[N];  // 팀 이름 저장할 배열
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextLine();
		}
		

		// 연두의 영어이름 중 L, O, V, E 개수 구하기
		int nameL = 0;
		int nameO = 0;
		int nameV = 0;
		int nameE = 0;
		
		for (int i = 0; i < name.length(); i++) {
			switch (name.charAt(i)) {
				case 'L': nameL++; break;
				case 'O': nameO++; break;
				case 'V': nameV++; break;
				case 'E': nameE++; break;
			}
		}
		
		
		// 우승확률 최대인 팀 이름 구하기
		// - 확률이 같은 경우, 사전순으로 앞서는 팀 이름을 구해야 한다.
		int max = -1;
		int maxIdx = 0;
		
		for (int i = 0; i < N; i++) {
			int L = nameL;
			int O = nameO;
			int V = nameV;
			int E = nameE;
			
			for (int j = 0; j < arr[i].length(); j++) {
				switch (arr[i].charAt(j)) {
					case 'L': L++; break;
					case 'O': O++; break;
					case 'V': V++; break;
					case 'E': E++; break;
				}
			}
			
			int probablity = ((L+O)*(L+V)*(L+E)*(O+V)*(O+E)*(V+E)) % 100;
			
			// 챗GPT 도움받은 부분
			// - 원래는 compareTo를 생각하지 못하고 조건문을 복잡하게 써서, 알 수 없는 오류 발생
			// - 사전순 비교할 때는 compareTo가 간편함을 알게 되었음
			// - 자기자신.compareTo(매개변수) : 자기자신보다 매개변수가 작으면 +
			if (max < probablity || (max == probablity && arr[maxIdx].compareTo(arr[i]) > 0)) {
				max = probablity;
				maxIdx = i;
			}
		}
		
		
		// 결과 출력
		System.out.println(arr[maxIdx]);
		
	}
}