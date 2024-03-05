import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_27522_정다운 {
	public static void main(String[] args) throws IOException {
		// 백준 카트라이더: 드리프트
		
		// 8명의 레이서의 완주 기록+팀 정보를 입력받는다
		// -> M:SS:sss 팀
		// 기록 과 팀정보 를 같은 인덱스 순서로 각각 다른 배열에 넣기 
		// 추가로 순위 정보를 넣을 수 있는 배열 생성
		// 순위 점수 배열도 선언 및 초기화 해주어서 사용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 기록 정보 배열
		int[] timeArr = new int[8];
		
		// 팀 정보 배열
		String[] teamArr = new String[8];
		
		// 순위 점수 배열
		int[] scoreArr = {10, 8, 6, 5, 4, 3, 2, 1}; // 1위 -> 8위 순서
		
		// 8명의 선수 기록, 팀 정보 배열에 입력
		for (int i=0; i<8; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String time = st.nextToken();
			String team = st.nextToken();
			
			// 기록 replace ':' -> ''
			int timeInt = Integer.parseInt(time.replace(":", ""));
			
			timeArr[i] = timeInt;
			teamArr[i] = team;
		}
		
		// 기록 정보 배열 사용해서 순위 정보 배열의 요소값 초기화
		// 기록 낮을수록 순위가 높다
		int[] rankArr = new int[8];
		
		for (int i=0; i<8; i++) {
			int rank = 1;
			for (int j=0; j<8; j++) {
				if (timeArr[i] > timeArr[j]) {
					rank++; // 다른 선수보다 오래걸렸으면 등수+1
				}
			}
			rankArr[i] = rank;
		}
		
		// 순위정보 배열, 팀정보 배열, 점수 배열 사용해서 R팀과 B팀의 최종 점수 구하기
		int rScore = 0;
		int bScore = 0;
		for (int i=0; i<8; i++) {
			if (teamArr[i].equals("R")) {
				rScore += scoreArr[rankArr[i]-1]; // "1"위점수 = 점수배열["0"] 
			} else {
				bScore += scoreArr[rankArr[i]-1];
			}
		}

		// 이긴 팀~?
		if (rScore > bScore) {
			System.out.println("Red");
		} else {
			System.out.println("Blue");
		}
	}
}