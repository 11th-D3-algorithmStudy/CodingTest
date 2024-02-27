import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		/*
		 * 풀이시간 : 240217 17:30 ~ 18:44
		 * 메인접근법 
		 *    - 입력값만큼 반복하며 색종이 범위에 해당하는 부분을 모두 1로 채움
		 *    - 기존 종이를 탐색하며 1인 부분을 찾는 조건을 넣어준다
		 *    - 1인 부분을 찾으면 1이면서 동시에 옆이나 위, 아래부분이 0이라면 카운트 
		 *    - 가로일 경우 : 1의 값일 때 위, 아래 중 하나라도 0이라면 카운트++
		 *    - 세로일 경우 : 1일 값일 때 좌, 우 중 하나라도 0이라면 카운트++
		 * 
		 * 막혔던부분
		 * 	  - 처음에는 테두리 부분만 1로 채우면 된다고 생각 
		 *           -> 종이가 겹치는 부분에도 1이 생겨 둘레 계산 이상해짐
		 *    - 값이 1일 때 상하좌우 하나라도 0이라면 카운트하는 방식으로 하였다가 값이 나오지 않았음
		 * 	  - 위의 방법으로 하였을 때 인덱스의 범위를 벗어났다고 에러남
		 * 
		 * 해결방법
		 *    - 모두 1로 채우고 그 주변을 탐색하여 가장자리만 판단하도록 함
		 *    - 가장자리 탐색할 때 가로, 세로의 경우를 나누어 판단
		 *    - 기존 배열의 범위를 101x101로 하여 해결
		 *    
		 * 메모리 : 14244 KB
		 * 시간 : 124 ms
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] paper = new int[101][101];
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			// 입력된 종이에 해당하는 부분은 모두 1로 채운다.
			for(int i=row; i<row+10; i++) {
				for(int j=col; j<col+10; j++) {
					paper[i][j] = 1;
				}
			}
		}
		
		// 가로 가장자리 탐색
		int count = 0;
		for(int i=1; i<100; i++) {
			for(int j=1; j<100; j++) {
				if(paper[i][j] == 1 
						&& (paper[i-1][j] == 0 || paper[i+1][j] == 0) ) {
					count++;
				}
			}
		}
		
		// 세로 가장자리 탐색
		for(int j=1; j<100; j++) {
			for(int i=1; i<100; i++) {
				if(paper[i][j] == 1 
						&& (paper[i][j-1] == 0 || paper[i][j+1] == 0) ) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
