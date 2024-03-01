import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_이규빈 {
	/*
	 *  Q.직사각형
	 *	# 풀이시간 : 1시간
	 *	# 메인 접근법
	 *		두 직사각형의 x좌표 범위를 "xRange", y좌표 범위를 "yRange"라 하고,
	 *		너비의 합을 "wSum", 높이의 합을 "hSum"이라 하자.
	 *		4가지 변수의 크기를 비교해 두 직사각형의 겹침 관계를 나타낼 수 있다.
	 */
	static int xRange,yRange, wSum, hSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 4; t++) {
			st = new StringTokenizer(br.readLine());
			
			// 배열에 좌표 저장
			int[] arr = new int[8];
			for (int i = 0; i < 8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// x범위 : |x1 - p2| 또는 |p1 - x2| 중 큰 값
			xRange = Math.max(Math.abs(arr[0] - arr[6]), Math.abs(arr[2] - arr[4]));

			// y범위 : |y1 - q2| 또는 |q1 - y2| 중 큰 값
			yRange = Math.max(Math.abs(arr[1] - arr[7]), Math.abs(arr[3] - arr[5]));
			
			// 너비의 합 : (p1 - x1) + (p2 - x2)
			wSum = (arr[2] - arr[0]) + (arr[6] - arr[4]);
			
			// 높이의 합 : (q1 - y1) + (q2 - y2)
			hSum = (arr[3] - arr[1]) + (arr[7] - arr[5]);
			
			if (wSum > xRange && hSum > yRange) { // 겹치는 부분이 '직사각형'
				System.out.println('a');
			} else if (wSum == xRange && hSum == yRange) { // 겹치는 부분이 '점'
				System.out.println('c');
			} else if (wSum < xRange || hSum < yRange) { // 겹치는 부분이 없음
				System.out.println('d');
			} else { // 겹치는 부분이 '선분' - 조건식이 복잡하므로 else로 처리
				System.out.println('b');
			}
		}
	}
}