import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10163_정다운 {
	public static void main(String[] args) throws IOException{
		// 백준 색종이
		
		// 모든 위치가 0인 평면
		// 색종이가 얹어지면 해당 면적의 숫자를 바꾼다
		// 1번째 색종이는 1로
		// 2번째 색종이는 2로
		// ...
		// n번째는 n으로!
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 색종이 개수
		int T = Integer.parseInt(br.readLine());
		// 각 색종이의 면적 저장할 배열 (결과)
		int[] areaArr = new int[T];
		
		// 2차원 평면 배열
		// 최대 면적으로 설정
		int[][] arr = new int[1001][1001];
		
		// 평면에 색종이 얹기
		for (int i=1; i<=T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			// 시작 행 좌표
			int sr = Integer.parseInt(st.nextToken());
			// 시작 열 좌표
			int sc = Integer.parseInt(st.nextToken());
			// 끝 행 좌표 : 시작 행 + 길이 - 1
			int er = sr+Integer.parseInt(st.nextToken())-1;
			// 끝 열 좌표 : 시작 열 + 길이 - 1
			int ec = sc+Integer.parseInt(st.nextToken())-1;
			
			// 색종이 면적 만큼 순서에 맞는 숫자로 바꾸기
			for (int r=sr; r<=er; r++) {
				for (int c=sc; c<=ec; c++) {
					arr[r][c] = i;
				}
			}
		}
		
		// 최종 평면에서 각 숫자 개수 구하기
		// 1부터 시작하고 결과 배열에 넣을때는 0부터
		for (int i=1; i<=T; i++) {
			int area = 0;
			for (int r=0; r<1001; r++) {
				for (int c=0; c<1001; c++) {
					if (arr[r][c] == i) {
						area++;
					}
				}
			}
			areaArr[i-1] = area; // idx는 0부터
		}
		
		// 한줄씩 출력
		for (int i=0; i<T; i++) {
			System.out.println(areaArr[i]);
		}	
	}
}
