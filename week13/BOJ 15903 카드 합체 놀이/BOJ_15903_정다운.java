package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903_정다운 {
	public static void main(String[] args) throws Exception {
		// 우선순위 큐 사용해서 가장 작은 수 2개만 m번 합체하기
		// 수 범위가 클것 같다 long 사용?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 카드 개수
		int m = Integer.parseInt(st.nextToken()); // 합체 횟수
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		long res = 0; // 결과
		
		st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<n; i++) {
			pq.add((long) Integer.parseInt(st.nextToken()));			
		}
		
		// m번 합체 고고
		while (m > 0) {
			long x = pq.poll();
			long y = pq.poll();
			// 합체
			for (int i=0; i<2; i++) {
				long sum = x+y;
				pq.add(sum);
			}
			m--;
		}
		
		while(!pq.isEmpty()) {
			res += pq.poll();
		}
		
		System.out.println(res);
		
		
	}
}
