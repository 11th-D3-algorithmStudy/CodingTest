import java.util.Scanner;

public class BOJ_10163_이규빈 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// N 입력
		int N = sc.nextInt();
		
		// 격자평면을 2차원배열으로 생성
		int[][] grid = new int[1001][1001];
		
		// 격자평면에 색종이 놓기
		// - i번쨰 색종이의 경우, 격자에 i를 넣기. 이미 0이 아닌 다른 숫자가 있다면 i로 바꾸기.
		// - N번째 색종이까지 반복하기
		for (int i = 1; i <= N; i++) {  // N장의 색종이
			int xCoord = sc.nextInt();  // 가장 왼쪽 아래 칸의 x좌표 (Coordinate)
			int yCoord = sc.nextInt();  // 가장 왼쪽 아래 칸의 y좌표
			int width = sc.nextInt();
			int height = sc.nextInt();
			
			for (int x = xCoord; x < xCoord + width; x++) {
				for (int y = yCoord; y < yCoord + height; y++) {
					grid[x][y] = i;
				}
			}
		}
		
		// 결과 출력
		// - (0,0)에서 (1000,1000)까지 순회하며, 해당 숫자가 채워진 갯수를 출력
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			
			for (int x = 0; x < 1001; x++) {
				for (int y = 0; y < 1001; y++) {
					if (grid[x][y] == i)
						cnt++;
				}
			}
			
			System.out.println(cnt);
		}
		
	}
}
