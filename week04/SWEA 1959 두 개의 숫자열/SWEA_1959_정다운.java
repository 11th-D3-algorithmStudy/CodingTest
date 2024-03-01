package week04;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959_정다운 {
	public static void main(String[] args) throws Exception {
		// 긴 숫자열 쪽의 idx를 바꿔가면서 탐색하기
		
		// 패턴매칭
		// 중복되는 코드가 많다..... 줄여서 다시 풀어보기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int max = 0;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			StringTokenizer stN = new StringTokenizer(br.readLine());
			StringTokenizer stM = new StringTokenizer(br.readLine());
			
			// 어느 숫자열이 더 짧은지 확인
			if (N < M) { // 짧은 배열 : N
				int[] shortArr = new int[N];
				int[] longArr = new int[M];
				for (int n=0; n<N; n++) {
					shortArr[n] = Integer.parseInt(stN.nextToken());
				}
				for (int m=0; m<M; m++) {
					longArr[m] = Integer.parseInt(stM.nextToken());
				}
				
				max = getMax(shortArr, longArr);
				
			} else if (N > M) { // 짧은 배열 : M 
				int[] shortArr = new int[M];
				int[] longArr = new int[N];
				for (int m=0; m<M; m++) {
					shortArr[m] = Integer.parseInt(stM.nextToken());
				}
				for (int n=0; n<N; n++) {
					longArr[n] = Integer.parseInt(stN.nextToken());
				}
				
				max = getMax(shortArr, longArr);
				
			} else { // 길이가 같을 때
				for (int i=0; i<N; i++) {
					max += Integer.parseInt(stN.nextToken())*Integer.parseInt(stM.nextToken());
				}
			}
			
			sb.append("#"+t+" "+max+"\n");
		}
		
		System.out.println(sb);
	}
	
	static int getMax(int[] shortArr, int[] longArr) {
		int max = Integer.MIN_VALUE;
		int longIdx = 0;
		
		// 두 배열의 차이보다 작거나 같을 동안만
		while (longIdx <= longArr.length-shortArr.length) {
			int sum = 0;
			// 짧은 배열의 idx 만큼만 돈다
			for (int i=0; i<shortArr.length; i++) {
				int s = shortArr[i];
				int l = longArr[i+longIdx];
				
				sum += s*l;
			}
			if (sum > max) {
				max = sum;
			}; 
			
			longIdx++;
		}
		
		return max;
	}
}
