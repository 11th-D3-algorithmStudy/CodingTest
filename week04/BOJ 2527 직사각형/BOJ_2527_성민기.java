package another;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2527_직사각형 {
	public static void main(String[] args) throws IOException {
		
		/*
		 * 처음 접근 방법 
		 *     - 직사각형에 해당하는 범위 모두 +1
		 *     - 두 직사각형이 겹치는 부분은 2가되어서 2가되는 부분을 확인
		 *     - 여기서 문제발생 -> 배열의 크기? 때문인지 메모리초과
		 *     
		 * 다르게 접근
		 *     - 배열 사용 X
		 *     - 범위로 겹치는 부분을 생각
		 *     - x값, y값, p값, q값 각각의 값을 비교
		 *     - maxX, maxY, minP, minQ를 구하게 되면
		 *       그림을 그렸을 때 겹치는 범위를 알 수 있다.
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<4; t++) {
			st = new StringTokenizer(br.readLine());
			// 첫번째 직사각형의 좌표
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());
			
			// 두번째 직사각형의 좌표
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());
			
			//겹치는 부분 확인
			int startX = Math.max(x1, x2);
			int startY = Math.max(y1, y2);
			int endX = Math.min(p1, p2);
			int endY = Math.min(q1, q2);
			
			// d(공통부분X) 조건 -> startX가 endX보다 크다면 겹치는 부분이 발생할 수가 없다.
			// c(점) 조건 -> 구한 좌표값들이 모두 같다면 점으로 접하는 것
			// a(직사각형) 조건 -> startX < endX && startY < endY 조건에 충족해야만
			//                 직사각형을 그릴 수 있다.
			// b(선분) 조건 -> 그 외
			if(startX > endX || startY > endY) sb.append('d').append("\n");
			else if(startX == endX && startY == endY) sb.append('c').append("\n");
			else if(startX < endX && startY < endY) sb.append('a').append("\n");
			else sb.append('b').append("\n");
		}
		System.out.println(sb);
	}
}
