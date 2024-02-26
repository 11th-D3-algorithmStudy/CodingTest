package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_정다운 {
	public static void main(String[] args) throws Exception {
		// 주어지는 직사각형 내부를 1로 채운다 (++)
		// 직사각형이 겹치는 부분은 2
		// 이때 2가 있는 영역의 가로 && 세로 길이가 2 이상이면 직사각형 (a)
		// 가로 || 세로 길이가 1이면 선분 (b)
		// 2가 1개면 점 (c)
		// 2가 없으면 d

		// 2차원 배열에 저장하고 살피려고 하니 메모리 에러 발생...
		// 저장하지 말고 좌표 사용해서 바로 비교 ㄱㄱ 
		
		// 조건문 너~~~~무 복잡.. 줄여서 다시 풀자

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 4개씩 주어진다
		for (int i = 0; i < 4; i++) {

			// 8개의 좌표 정보 (x1-1, y1-1, x1-2, y1-2, x2-1, y2-1, x2-2, y2-2)
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 직사각형 A 좌표정보
			int A_x1 = Integer.parseInt(st.nextToken());
			int A_y1 = Integer.parseInt(st.nextToken());
			int A_x2 = Integer.parseInt(st.nextToken());
			int A_y2 = Integer.parseInt(st.nextToken());

			// 직사각형 B 좌표정보
			int B_x1 = Integer.parseInt(st.nextToken());
			int B_y1 = Integer.parseInt(st.nextToken());
			int B_x2 = Integer.parseInt(st.nextToken());
			int B_y2 = Integer.parseInt(st.nextToken());

			// 좌표로 비교

			// 겹치는 가로 길이
			int x = 0;
			// 겹치는 세로 길이
			int y = 0;

			// x가 겹치지 않는 경우 일단 제외
			if (A_x1 > B_x2 || A_x2 < B_x1) {
			} // A가 더 오른쪽에 있는 경우 (좌표가 같은 경우는 0이 아니라 1이 되도록 +1 해줌)
			else if (A_x1 > B_x1) {
				x = B_x2 - A_x1 + 1;
			} // B가 더 오른쪽에 있는 경우
			else if (B_x1 > A_x1) {
				x = A_x2 - B_x1 + 1;
			} else {
				x = Math.min(A_x2, B_x2)-A_x1+1; // 1 차이 나는 경우 점이 아니라 선으로 표현되어야 해서 +1
			}

			// y가 겹치지 않는 경우 일단 제외
			if (A_y1 > B_y2 || A_y2 < B_y1) {
			} // A가 더 위에 있는 경우 (좌표가 같은 경우는 0이 아니라 1이 되도록 +1 해줌)
			else if (A_y1 > B_y1) {
				y = B_y2 - A_y1 + 1;
			} // B가 더 위에 있는 경우
			else if (B_y1 > A_y1) {
				y = A_y2 - B_y1 + 1;
			} else {
				y = Math.min(A_y2, B_y2)-A_y1+1;
			}
			

			if (x > 1 && y > 1) {
				sb.append("a" + "\n");
			} else if ((x == 1 && y > 1) || (x > 1 && y == 1)) {
				sb.append("b" + "\n");
			} else if (x == 1 && y == 1) {
				sb.append("c" + "\n");
			} else {
				sb.append("d" + "\n");
			}

		}
		System.out.println(sb);
	}
}
