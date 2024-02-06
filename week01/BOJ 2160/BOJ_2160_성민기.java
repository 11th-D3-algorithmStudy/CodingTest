//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;
//import java.util.StringTokenizer;


public class BOJ_2160_성민기 {
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		int N = Integer.parseInt(br.readLine());
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 3차원 배열을 이용해서 N개의 5X7 배열을 만든다?
		char[][][] pic = new char[N][5][7];
		
		// N개 만큼 반복하며 5x7 배열을 만든다
		for(int k=0; k<N; k++) {
			for(int i=0; i<5; i++) {
//				String line = br.readLine();
				String line = sc.next();
				for(int j=0; j<7; j++) {
					pic[k][i][j] = line.charAt(j);
				}
			}
		}
//		int value = 0;
		// 서로 다른 값이 최소일 때 담을 인덱스값
		int simulIdx1 = Integer.MIN_VALUE;
		int simulIdx2 = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE; // min이 반복문 안에 있으면 계속 초기화된다.
		// k번째 그림과 k+1번째 비교를 위한 반복문
		for(int k=0; k<N-1; k++) {
			for(int r=k+1; r<N; r++) {
				// 서로다른 값을 구하기 위한 count 변수 선언
				int count = 0;
				for(int i=0; i<5; i++) {
					for(int j=0; j<7; j++) {
						// 그림에서 서로 다른 부분 존재한다면 count++;
						if(pic[k][i][j] != pic[r][i][j]) {
							count++;
						}
					}
				}
				// count의 최소값을 구하고
				if(min > count) {
					min = count;
					// 최소값일 때의 인덱스 k값과 r값을 구한다.
					simulIdx1 = k;
					simulIdx2 = r;
				}
			}
		}
		// 서로 다른 그림의 번호를 나타내기 위한 +1 더해준다.
		System.out.println((simulIdx1+1) + " " + (simulIdx2+1));
	}
}
