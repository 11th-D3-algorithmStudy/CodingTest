import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2160_이윤주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		//n개의 그림을 저장할 배열 선언
		char[][][] art = new char[n][5][7];
		
		//n개의 그림을 배열에 저장
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 5; j++) {
				String tmp = br.readLine();
				for(int k = 0; k < 7; k++) {
					art[i][j][k] = tmp.charAt(k);
				}
			}
		}
		
		
		//다른 칸의 개수가 가장 작은지 확인하는 변수
		int min = 40;
		
		//비슷한 그림의 번호를 저장
		int similar1 = 0;
		int similar2 = 0;
		
		//a번째 그림과 b번째 그림을 비교하기
		for(int a = 0; a < n - 1; a++) {
			for(int b = a + 1; b < n; b++) {
				//다른 칸의 개수를 세는 변수
				int diff = 0;
				
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 7; j++) {
						if(art[a][i][j] != art[b][i][j])
							diff++;
					}
				}
				//다른 칸의 개수가 더 적은 그림들을 저장, 최솟값 갱신
				if(diff < min) {
					min = diff;
					similar1 = a + 1;
					similar2 = b + 1;
				}
			} //그림 비교 끝
			
		}//모든 그림 비교 끝
		
		System.out.printf("%d %d",similar1, similar2);
		
		
		
	}
}
