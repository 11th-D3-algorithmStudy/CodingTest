package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789_조아름_간식드리미 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Queue<Integer> q = new LinkedList<>(); // 현재 줄서있는 곳
		Stack<Integer> s = new Stack<>(); // 새로운 줄 서는 곳
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			q.offer(Integer.parseInt(st.nextToken()));
		}
		
		int start = 1; // 간식 먹는 순서
		
		while(!q.isEmpty()) {
			if(q.peek()==start) { // 간식 받아야 하는 순서와 대기열이 같다면
				q.poll();
				start++;
			}else if(!s.isEmpty() && s.peek()==start) { // 추가 대기열이 비어있지 않고 간식을 받아야 하는 사람과 같다면
				s.pop();
				start++;
			}else {
				s.push(q.poll()); // 대기열, 추가대기열 모두 간식을 받아야 하는 사람과 다르다면 대기열 사람을 추가 대기열로 이동
			}
		}
		while(!s.isEmpty()) {
			if(s.peek()==start) { // 추가 대기열에 남아있는 사람들 체크
				s.pop();
				start++;
			}else {
				System.out.println("Sad"); // 추가대기열 같지 않다면 Sad 반환
				return;
			}
		}
		System.out.println("Nice"); // 여기까지 성공했으면 간식을 순서대로 받은 것이므로
	}

}
