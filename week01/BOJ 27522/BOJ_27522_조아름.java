package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_27522_조아름 {
	public static void main(String[] args) throws IOException {
		int[][] speedArr = new int[2][8];//완주 기록과 팀의 이름을 담을 2*8 배열 선언
		int [] scoreArr = {10,8,6,5,4,3,2,1}; // 성적 값이 담긴 배열
		int [] resultArr = new int[8];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 8;
		
		for(int c=0;c<T;c++) {//2차원 배열에 입력값 채우기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<2;i++) {
				speedArr[i][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(speedArr, (o1, o2)->{//속도 순으로 정렬
			return o1[0]-o2[0];
		});
		
		for(int i=0;i<T;i++) {
			resultArr[i] = speedArr[1][i] * scoreArr[i];
		}
		
		
	}
}
