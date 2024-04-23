package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전_이규빈 { // 풀이시간 : 2시간 30분
	static int M, A, res;
	static User userA, userB;
	static Charger[] bcArr;  // 충전기 정보 담을 배열
	
	// 델타배열
	// - 문제의 이동방향 정보와 일치시키기
	static int[] dr = {0, 0, 1, 0, -1};
	static int[] dc = {0, -1, 0, 1, 0};
	
	static class User {
		int r, c;
		int[] moveInfo;  // 이동 정보
		
		public User(int r, int c, int[] moveInfo) {
			this.r = r;
			this.c = c;
			this.moveInfo = moveInfo;
		}
	}
	
	static class Charger {
		int r, c;
		int cov;   // 충전범위 (coverage)
		int perf;  // 성능 (performance)
		
		public Charger(int r, int c, int cov, int perf) {
			this.r = r;
			this.c = c;
			this.cov = cov;
			this.perf = perf;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());  // 이동 시간
			A = Integer.parseInt(st.nextToken());  // 충전기 개수
			
			// 사용자 A 이동정보 입력
			// - 초기 위치(0초)부터 충전 가능한 것을 구현하기 위해, 0번 인덱스는 0으로 남겨둔다. 
			userA = new User(1, 1, new int[M + 1]);
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				userA.moveInfo[i] = Integer.parseInt(st.nextToken());
			}
			
			// 사용자 B 이동정보 입력
			userB = new User(10, 10, new int[M + 1]);
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				userB.moveInfo[i] = Integer.parseInt(st.nextToken());
			}			
			
			// 충전기 정보 입력
			bcArr = new Charger[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cov = Integer.parseInt(st.nextToken());
				int perf = Integer.parseInt(st.nextToken());
				
				bcArr[i] = new Charger(r, c, cov, perf);
			}
			
			// ----- 입력 끝 -----
			
			res = 0;
					
			solve(0, 0);
			
			sb.append("#" + tc + " " + res + "\n");
		} // 테스트케이스 끝
		System.out.println(sb);
	}

	
	// 
	static void solve(int time, int chargeSum) {
		// base case
		if (time > M) {
			res = chargeSum;
			return;
		}
		
		// 사용자 좌표 업데이트
		userA.r += dr[userA.moveInfo[time]];
		userA.c += dc[userA.moveInfo[time]];
		userB.r += dr[userB.moveInfo[time]];
		userB.c += dc[userB.moveInfo[time]];
		
		// 해당 시간에서의 최대 충전량을 찾는다.
		int max = 0;
		
		// 이중 for문을 이용해 중복조합 만들기
		// - 충전기 배열(bcArr)을 순회해, 사용자A가 i번 충전기로, 사용자B가 j번 충전기로 충전하는 경우를 구현
		for (int i = 0; i < A; i++) { 
			for (int j = 0; j < A; j++) {
				int tmp = 0;
				
				// 충전범위 내에 있는 경우만 충전하기
				if (inCoverage(userA, bcArr[i]))  tmp += bcArr[i].perf;
				if (inCoverage(userB, bcArr[j]))  tmp += bcArr[j].perf;
				
				// 둘 다 같은 충전기로 충전하는 경우, 충전량 합 1/2
				if (i == j && inCoverage(userA, bcArr[i]) && inCoverage(userB, bcArr[j])) {
					tmp /= 2;
				}
				
				max = Math.max(max, tmp);
			}
		} // 최댓값 갱신 완료
		
		solve(time + 1, chargeSum + max);
	}

	
	// 사용자와 충전기의 거리가 충전범위(cov) 이내인지 판단
	static boolean inCoverage(User user, Charger charger) {
		int D = Math.abs(user.r - charger.r) + Math.abs(user.c - charger.c);
		if (D <= charger.cov) {
			return true;
		}
		return false;
	}
}
