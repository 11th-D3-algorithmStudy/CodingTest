package week05;

import java.io.*;
import java.util.*;

public class SWEA_7964_장현영 {
	// 문제 이해하는데 시간 다소 소요
	// 관문을 꽂으면 그 관문 +-(왼쪽과 오른쪽)dist만큼의 이동이 가능
	// 즉 1을 모두 연결하려면 관문(+-거리)들로 0들을 커버해야함
	// 1 0 0 0 0 1
	// 1이면 안 건들고 새로 재건
	// 0이면 연속 재건에 필요한 횟수 세고 연속 재건이 거리에 도달하면 해당 자리에 관문 설치하는 개념
	// 배열 필요없이 개수세기
	// 풀이시간 40분
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int answer =0;
			int zeros=0;
			for(int i=0;i<n;i++) {
				int number = Integer.parseInt(st.nextToken());
				if(number == 1) { // 재건필요 x
					zeros=0; // 연속 zero 초기화
				}else {
					zeros++;
					if(zeros == dist) {
						answer++; // 관문 설치 필요
						zeros = 0; // 설치 후 연속 zero 초기화
					}
				}
			}
			
			System.out.println("#"+t+" "+answer);
			
		}
	}
}
