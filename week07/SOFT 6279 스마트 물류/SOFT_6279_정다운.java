package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SOFT_6279_정다운 {
	
	static int N, K;
	static char[] arr; // 공정 배열
	static boolean[] visit; // 이미 집어간 부품인지 확인용 배열 
	static int cnt = 0; // 부품 집을 수 있는 로봇의 수
	
	public static void main(String[] args) throws Exception {
		// 로봇의 위치에서 집을수 있는 부품을 확인
		// 로봇은 최대한 왼쪽에 있는 부품을 집는다 (이래야 최대한 많은 로봇이 집을거 같다...?)
		
		// 로봇에서 거리가 K 이내인 좌표 중 부품의 위치를 확인하고, 
		// 다른 로봇이 집어가지 않았고 & 가장 왼쪽에 있는 부품 집기
		// 집어갈때 visit 처리 하자~
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new char[N];
		visit = new boolean[N];
		
		arr = br.readLine().toCharArray();
		
		label:
		for (int i=0; i<N; i++) {
			if (arr[i] == 'P') { // 로봇일 때
				for (int j=i-K; j<=i+K; j++) { // 로봇이 팔 뻗을 수 있는 범위 (왼쪽부터 확인)
					// 일단 경계조건
					if (j>=0 && j<N) {
						if (arr[j] == 'H' && !visit[j]) { // 부품이고 아직 안집어갔을때
							cnt++; // 집어가고
							visit[j] = true; // 방문처리
							continue label;
						}
					}
				}
			}
		}
		
		System.out.println(cnt);
	
	}
}