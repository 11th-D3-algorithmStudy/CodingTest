import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_10163_이윤주 {
	public static void main(String[] args) throws Exception {
		// 색종이
		// 색종이를 놓으면 색종이 범위에 색종이 번호를 덮어씌운다
		// 1의 개수 -> 첫번째 색종이 보이는 면적
		// 2의 개수 -> 두번째 색종이 보이는 면적...
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] grid = new int[1001][1001];

		int N = Integer.parseInt(br.readLine());


		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			//입력받아서 격자에 색종이 범위에 색종이 번호 넣기
			for(int j = x; j < x + width; j++) {
				for(int k = y; k < y + height; k++) {
					grid[j][k] = i;
				}
			}
		}
		
		//각 색종이의 면적을 담을 배열
		int[] area = new int[N + 1];
		
		//격자에서 각 색종이의 번호를 확인해서 면적에 +
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				area[grid[i][j]]++;
			}
		}
		
		//출력
		for(int i = 1; i < N + 1; i++) {
			System.out.println(area[i]);
		}
	}
}
