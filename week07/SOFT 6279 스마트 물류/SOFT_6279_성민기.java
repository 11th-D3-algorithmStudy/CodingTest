package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class SOFT_6279_성민기 {
	public static void main(String[] args) throws Exception{
		
		/* 풀이시간 : 240315 23:05 ~ 23:40
		 * 메인접근법 :
		 *     - 좌우 탐색이기 때문에 델타배열 탐색을 사용
		 *     - 입력값 K만큼 곱하여 범위를 지정
		 *     - 방문처리를 한 후 방문된 곳만 카운트하여 출력함
		 *     
		 * 메모리 : 10.34 MB
		 * 실행 시간 : 79 ms
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력값
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String line = br.readLine();
		char[] KD = line.toCharArray();
		boolean[] visited = new boolean[N];
		
		// 좌 우
		int[] dc = {-1, 1};
		
		out:
		for(int i=0; i<N; i++) {
			// 인덱스 * K만큼의 값을 구함
			int left = i + dc[0]*K;
			int right = i + dc[1]*K;
			
			// P(로봇)일 경우
			if(KD[i]=='P') {
				// 왼쪽값이 0보다 작으면 0, 오른쪽 범위를 벗어났으면 N-1로 설정
				if(left < 0) left = 0;
				if(right >= N) right = N-1;
				
				// left ~ right까지 탐색
				for(int j=left; j<=right; j++) {
					// 방문처리 안됨 + 물류일 때
					if(visited[j]==false && KD[j]=='H') {
						// 방문처리 및 물건집었으니 continue out으로 탈출
						visited[j]=true;
						continue out;
					}
				}
			}
		}
		
		// 방문처리에서 true인 경우에만 카운트 후 출력
		int count = 0;
		for(int i=0; i<N; i++) {
			if(visited[i]) count++;
		}
		
		System.out.println(count);
	}
}
