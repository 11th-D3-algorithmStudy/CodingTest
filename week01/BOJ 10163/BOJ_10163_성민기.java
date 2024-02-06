import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_10163_색종이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int N = Integer.parseInt(br.readLine());
		// 색종이를 덮을 종이 사이즈 배열
		int[][] A4 = new int[1001][1001];
		
		// 색종이 순서대로 보이는 면적 구하기 위한 배열
		int[] size = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 행 열 높이 너비 순으로 입력
			// 배열에서 반시계방향 90도 회전한 형태가 문제의 종이 번호이기 때문
			// 너비 <-> 높이 를 바꿔주었다.
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			
			// 주어진 범위를 색종이의 번호로 채운다.
			// 다음 종이가 기존 종이를 덮은 부분은 다음 종이의 번호로 덮어준다.
			// (이 생각을 못했네 와.)
			for(int r=row; r<row+height; r++) {
				for(int c=col; c<col+width; c++) {
					A4[r][c] = i + 1;
				}
			}	
		}
		
		for(int i=0; i<N; i++) {
			// 카운트 초기화
			int count = 0;
			
			// 종이를 탐색하면서
			for(int r=0; r<A4.length; r++) {
				for(int c=0; c<A4[r].length; c++) {
					
					// 종이의 번호와 같은 부분만 카운트
					if(A4[r][c] == (i+1)) {
						count++;
					}
				}
			}
			// 카운트한 결과는 순서대로 size 배열에 기입
			size[i] = count;
		}
		
		// 최종 출력
		for(int i=0; i<size.length; i++) {
			System.out.println(size[i]);
		}
	}
}
