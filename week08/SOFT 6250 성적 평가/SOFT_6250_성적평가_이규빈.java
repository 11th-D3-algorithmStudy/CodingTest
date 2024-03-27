package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SOFT_6250_성적평가_이규빈 {
	/*
	 * # 막힌 부분 해결
	 *  tc 21번부터 시간초과 
	 *  -> N이 최대 10만이다보니 N^2(= 최대 100억)번 계산하는 반복문 때문에 시간초과 나는 듯
	 *  -> 따라서 이중 for문을 사용하지 않아야 하고, 정렬을 사용해야 한다는 생각까지는 했지만...
	 *  -> 구현 방법이 떠오르지 않아 다른 사람 답 참고
	 * 
	 * # 메모리 / 실행시간
	 *  - 이중 for문 사용한 경우 : 36.95MB / 2085ms (시간 초과)
	 *  - 정렬과정으로 대체한 경우 : 64.12MB / 1203ms
	 *  -> 별도의 rankArr를 만들었기 때문에 메모리 면에서는 손해를 봤지만, 이중 for문을 제거해 실행시간을 획기적으로 감소시킬 수 있었다.
	 *  -> 이 문제를 통해 N값이 큰 경우 실행시간을 낮추기 위한 해결방안을 배울 수 있었다.
	 */
	
	// 대회를 나타내는 클래스
	// - 각 대회 별로 id를 부여하기 위해, 사람이 아닌 "대회"를 기준으로 클래스를 만든다.
	static class Contest {
		int id; // 점수 순으로 정렬하면 순서가 뒤섞이게 되므로, 원래 순번을 알아내기 위해 사용
		int score;
		
		 Contest(int id, int score) {
			 this.id = id;
			 this.score = score;
		 }
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Contest[][] contest = new Contest[4][N];
		
		// 1. 행 우선순회
		// - 점수 정보 저장
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				// **Contest 객체를 생성함과 동시에 id와 점수를 부여한다.**
				 contest[i][j] = new Contest(j, Integer.parseInt(st.nextToken()));
			}
		}
		
		
		// 2. 열 우선순회
		// - 0~2행의 점수를 더해, 해당 열의 3번 행에 저장
		for (int j = 0; j < N; j++) {
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += contest[i][j].score;
			}
			 contest[3][j] = new Contest(j, sum);
		}
		
		
		// 3. 성적순 내림차순 정렬
		// - 2차원배열의 각 행(= 1차원배열)에 대해 "점수를 기준으로" 내림차순 정렬을 한다.
		for (Contest[] c : contest) {
			Arrays.sort(c, new Comparator<Contest>() {
				@Override
				public int compare(Contest o1, Contest o2) {
					return o2.score - o1.score;
				}
			});
		}
		
		
		// 4. 별도의 배열에 등수를 저장
		int[][] rankArr = new int[4][N];
		for (int i = 0; i < 4; i++) {
			int rank = 0;
			int sameScoreCnt = 1; // 동점자 수 카운트
			int preScore = -1; // 이전에 탐색한 점수
			
			for (int j = 0; j < N; j++) {
				int curScore = contest[i][j].score; // 현재 탐색하는 점수
				
				// 이전 사람 점수와 같은 경우 (= rank 동일)
				if (curScore == preScore) {
					sameScoreCnt++;
					
					rankArr[i][contest[i][j].id] = rank; // 이전과 동일한 등수를 저장
				}
				
				// 이전 사람 점수와 다른 경우
				else {
					rank += sameScoreCnt; // 이전에 쌓여있던 동점자 수만큼 등수 올리기
					sameScoreCnt = 1; // 동점자 수 초기화

					rankArr[i][contest[i][j].id] = rank; // 올라간 등수를 저장
					preScore = curScore;
				}
			}
		}
		
		// 결과 출력
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(rankArr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
		// ----------------- 이하 시간초과 나온 코드 ----------------------
		
//		int N = Integer.parseInt(br.readLine());
//		int[][] score = new int[3][N];
//		int[] finalScore = new int[N]; // 최종 등수를 계산하기 위한 각 참가자의 점수 합
//		
//		// 점수 입력 - arr[대회][점수]
//		for (int c = 0; c < 3; c++) {
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < N; i++) {
//				score[c][i] = Integer.parseInt(st.nextToken());
//				finalScore[i] += score[c][i];
//			}
//		}
//		
//		// 각 참가자의 대회별 등수
//		for (int c = 0; c < 3; c++) {
//			for (int i = 0; i < N; i++) {
//				int cnt = 0; // 나보다 높은 점수 개수
//				
//				for (int j = 0; j < N; j++) {
//					if (score[c][i] < score[c][j])  cnt++;
//				}
//				
//				sb.append((cnt + 1) + " "); // 등수 = 나보다 높은 점수 개수 + 1
//			}
//			sb.append("\n");
//		}
//		
//		// 각 참가자의 최종 등수
//		for (int i = 0; i < N; i++) {
//			int cnt = 0;
//			
//			for (int j = 0; j < N; j++) {
//				if (finalScore[i] < finalScore[j])  cnt++;
//			}
//			
//			sb.append((cnt + 1) + " ");
//		}
//		
//		System.out.println(sb);
	}
}
