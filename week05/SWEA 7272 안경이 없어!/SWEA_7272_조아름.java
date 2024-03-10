package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7272_조아름 {
	// 될까 싶었는데 생각보다 간단하게 풀린 문제. 이전에 우리 스터디 하면서 정규표현식과 replaceAll을 사용했던 적이 있는데
	// 이 문제에서도 해당하는 문자일 때 공통 문자로 변경해주고 두 string이 동일한 지 판단해서 답을 내줬다.

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int c=0;c<T;c++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			String txt1 = st.nextToken();
			String txt2 = st.nextToken();
			
			if(txt1.length()!=txt2.length()) { // 길이가 다를 때는 DIFF
				System.out.println("#"+(c+1)+" "+"DIFF");
				continue;
			}else { // 정규표현식과 replaceAll 을 사용해서 동그라미가 하나도 없는 문자일 때는 문자를 C로 변경, 하나일 때는 A로 변경한다.
				String newT1 = txt1.replaceAll("[CEFGHIJKLMNSTUVWXYZ]", "C").replaceAll("[ADOPQR]", "A");
				String newT2 = txt2.replaceAll("[CEFGHIJKLMNSTUVWXYZ]", "C").replaceAll("[ADOPQR]", "A");
				
				if(newT1.equals(newT2)) { // 이후 두 문자열이 같은지 다른지 비교해서 출력
					System.out.println("#"+(c+1)+" "+"SAME");
				}else {
					System.out.println("#"+(c+1)+" "+"DIFF");
				}
			}
		}
	}

}
