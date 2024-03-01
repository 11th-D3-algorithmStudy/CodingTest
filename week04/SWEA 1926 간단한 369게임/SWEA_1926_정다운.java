package week04;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_1926_정다운 {
	public static void main(String[] args) throws Exception {
		// 1부터 입력받은 숫자까지 for문 돌면서
		// i를 charArr로 변환해서 각 자리수에 3의 배수가 포함되는지 확인한다
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i=1; i<=N; i++) {
			char[] checkArr = (i+"").toCharArray();
			
			int clapCnt = 0;
			
			// 자리수 확인
			for (int j=0; j<checkArr.length; j++) {
				int checkint = checkArr[j]-'0';
				// 3의 배수 확인, % 연산자 사용하기 때문에 0 제외시키기
				if (checkint % 3 == 0 && checkint != 0) {
					clapCnt++; // 박수 횟수 ++
				}
			}
			
			if (clapCnt == 0) {
				sb.append(i+" ");
			} else {
				for (int j=0; j<clapCnt; j++) {
					sb.append("-");
				}
				sb.append(" ");
			}
		}
		System.out.println(sb);
	}
}
