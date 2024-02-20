import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798_이윤주 {
	public static void main(String[] args) throws Exception {
		//풀었던 문제
		//시작 12시 35분 12시 43분 -> 풀이시간 : 8분
		
		//브루트 포스
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] cards = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
	
		int best = 0;
		
		//모든 경우 확인해보기
		for(int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for(int k = j + 1; k < n; k++) {
					int sum = cards[i] + cards[j] + cards[k];
					
					//더한 값이 m보다 크면 skip
					if(sum > m)
						continue;
					
					//더한 값이 best보다 크면 best 갱신
					if(sum > best)
						best = sum;
				}
			}
		}
		
		System.out.println(best);
	}
}
