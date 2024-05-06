package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		int M = Integer.parseInt(info[1]);
		PriorityQueue<Long> pq = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
		
		for (int j = 0; j < M; j++) {
			Long num1 = pq.poll();
			Long num2 = pq.poll();
			pq.offer(num1+num2);
			pq.offer(num1+num2);
		}
		
		Long sum = 0l;
		while (!pq.isEmpty()) {
			sum += pq.poll();
		}
		System.out.println(sum);
	}
}
