import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_27522_성민기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 시간기록과 팀 내용을 입력받을 문자배열 record
		char[][] record = new char[8][10];
		// record배열에서 숫자들만 가져와 String형태로 사용하기 위해 선언
		String tmp = "";
		// B, R을 입력위한 문자배열 team
		char[] team = new char[8];
		// tmp에 입력된 String값을 정수형으로 바꾼 내용을 입력한 배열
		int[] time = new int[8];
		// tmp에 입력된 값을 정수형으로 변환하여 배열에 넣어주고 정렬하기 위한 배열 
		int[] rank = new int[8];
		// 점수를 넣기 위한 정수형 배열
		int[] score = new int[8];
		
		// 입력값 받기
		for (int i = 0; i < record.length; i++) {
			String min = br.readLine();
			for (int j = 0; j < record[i].length; j++) {
				record[i][j] = min.charAt(j);
			}
		}
		
		// record 배열의 숫자만 있는 문자들을 tmp에 입력
		for (int i = 0; i < record.length; i++) {
			tmp += record[i][0];
			tmp += record[i][2];
			tmp += record[i][3];
			tmp += record[i][5];
			tmp += record[i][6];
			tmp += record[i][7];
			// String -> int 변환된 결과 tmp를 순서대로 rank배열에 넣어준다
			rank[i] = Integer.parseInt(tmp);
			// tmp 내용 초기화
			tmp = "";
		}
		
		// rank에 입력된 값을 time배열에 그대로 넣어준다
		for(int i=0; i<rank.length; i++) {
			time[i] = rank[i];
		}
		
		// rank 배열의 값을 sort를 통해 순서대로 정렬
		Arrays.sort(rank);
		
		// B, R의 값인 record의 9번째 배열값들을 순서대로 team배열에 값 입력
		for(int i=0; i<team.length; i++) {
			team[i] = record[i][9];
		}
		
		// 점수 설정
		int win = 10;
		
		for(int i=0; i<rank.length; i++) {
			for(int j=0; j<rank.length; j++) {
				// 작은 순서대로 정렬된 rank배열의 값과 
				// 정렬 전 time배열의 값을 비교하여
				// 같을 경우 랭크에 따라 점수를 입력해준다
				if(rank[i]==time[j]) {
					// i==0 -> 1위일경우
					if(i==0) {
						// 10점 입력
						score[j] = win;
					// i==1 -> 2위일경우
					} else if(i==1) {
						// 8점 입력 후 6점으로 win값 설정
						win = 8;
						score[j] = win;
						win = 6;
					// 그 외의 순위는 순차적으로 낮게 설정
					} else {
						score[j] = win--;
					}
				}
			}
		}
		
		// 각 팀의 점수를 받을 int형 변수 선언
		int scoreBlue = 0;
		int scoreRed = 0;
		
		for(int i=0; i<score.length; i++) {
			// Blue팀일 때의 인덱스를 score배열의 인덱스로 사용하여
			// 블루팀 점수를 합산
			if(team[i] == 'B') {
				scoreBlue += score[i];
			// Red팀일 때의 인덱스를 score배열의 인덱스로 사용하여
			// 레드팀 점수를 합산
			} else if(team[i] == 'R') {
				scoreRed += score[i];
			}
		}
		
		// 점수 비교 후 점수가 더 큰 팀의 이름 출력
		if(scoreRed > scoreBlue) {
			System.out.println("Red");
		} else if(scoreRed < scoreBlue) {
			System.out.println("Blue");
		}
		
	}
}
