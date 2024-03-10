import java.util.Scanner;

public class Solution {
	/*
	 * Q. 안경이 없어 
	 #문제요약 
	 경근이는 알파벳 대문자로 이루어진 두 개의 문자열을 비교하는데 
	 문자열의 알파벳에 들어있는 구멍의 개수가 같으면 같은 문자열로 판단함 
	 입력 : 두 문자열 길이 각 10이하 
	 출력 : SAME / DIFF 
	 #풀이시간 : 
	 #메모리/시간 : 80248kb / 421ms
	 #메인접근법
	 * 1. 완전탐색 
	 * 2. 문자열에 담아서 contains로 확인? String.contains : true /false 반환
	 *  10000 개 중에 9661개 맞음..
	 *  B의 위치를 기억해야되나? 9883
	 * 구멍한개인 문자의 위치도 기억해서 확인해서 통과함!
	 */
//	static String noHole = "CEFGHIJKLMNSTUVWXYZ";
	static String oneHole = "ADOPQR";
//	static String twoHole = "B";
	static boolean[] bidx1;
	static boolean[] bidx2;
	static boolean[] oidx1;
	static boolean[] oidx2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		TC: for (int tc = 1; tc <= T; tc++) {
			String str1 = sc.next();
			String str2 = sc.next();
			
			//문자열 길이비교도 해야되나?
			if(str1.length() != str2.length()) {
				System.out.printf("#%d DIFF\n", tc);
				continue;
			}

			int count1 = 0;
			int count2 = 0;
			bidx1 = new boolean[str1.length()];
			bidx2 = new boolean[str2.length()];
			oidx1 = new boolean[str1.length()];
			oidx2 = new boolean[str2.length()];
			

			// 문자열 1에 구멍개수 세기
			for (int i = 0; i < str1.length(); i++) {
				String tmp = str1.charAt(i) + "";
				if (oneHole.contains(tmp)) {
					oidx1[i] = true;
				} else if (tmp.equals("B")) {
					bidx1[i] = true;
				}
			}
			// 문자열 2에 구멍개수 세기
			for (int i = 0; i < str2.length(); i++) {
				String tmp = str2.charAt(i) + "";
				if (oneHole.contains(tmp)) {
					oidx2[i] = true;
				} else if (tmp.equals("B")) {
					bidx2[i] = true;
				}
			}
			//oneHole, B위치비교
			for(int i = 0; i < bidx1.length; i++) {
				if(bidx1[i] != bidx2[i]) {
					System.out.printf("#%d DIFF\n", tc);
					continue TC;
				}	
				if(oidx1[i] != oidx2[i]) {
					System.out.printf("#%d DIFF\n", tc);
					continue TC;
				}	
			}
			System.out.printf("#%d SAME\n", tc);
		}
	}
}
