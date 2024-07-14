package AlgorithmStudy;
import java.io.*;
import java.util.*;

public class BOJ_16918_성민기 {
	
	/* 풀이시간 : 240423 22:00 ~ 240424 01:00 + 17:20 ~ 17:40
	 * 메인풀이법
	 *      - 주기마다 반복하는 것을 활용
	 *      - 폭탄의 위치값들을 리스트안의 배열 선언으로 활용하였음
	 *      - while문을 통해 시간에 다다를 때까지 반복
	 *      - 메소드를 활용
	 *      
	 * 막힌 부분
	 *      - 주기가 4초인 것을 계산하지 못함
	 *      - 처음에 bomb1 %= 3으로 계속하다가 틀려서 코드가 틀린줄알고
	 *        몇시간 낭비 -> 재확인 후 4로 수정
	 * 
	 * 메모리 : 89872 KB, 시간 : 344 ms
	 */
	
	
	static int R, C, N;
	static char[][] map;
	static List<int []> list; // 처음 설치된 폭탄의 위치들 저장
	static List<int []> list3second; // 나중 설치된 폭탄 위치 저장
	static int[] dr = {-1, 1, 0, 0}; 
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
	
		map = new char[R][C];
		list = new ArrayList<>();
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}// 입력완료
		
		// 처음에 폭탄설치된 위치값들 저장
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='O') list.add(new int[] {i, j});
			}
		}
		
		// N이 1이면 바로 출력 후 종료
		if(N==1) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			return;
		}
		
		int second = 0; // 시간 비교
		int bomb1 = 0; // 첫번째 폭탄 시간
		int bomb2 = -2; // 두번째 설치 폭탄 시간
		while(second < N) { // second가 N에 도달하기 전까지 반복
			bomb1++; bomb2++; 
			
			// 2초일 때는 폭탄설치
			if(bomb1 == 2 || bomb2 == 2) installBomb();
			
			// 3초일 때는 
			if(bomb1 == 3) {
				// 폭탄 터지고
				bomb();
				// 터진 후의 남은 폭탄 위치들 저장
				delta1();
			}
			if(bomb2 == 3) {
				bombAnother();
				delta2();
			}

			// 4초를 주기로 반복
			if(bomb1 == 4) bomb1 %= 4;
			if(bomb2 == 4) bomb2 %= 4;
			second++;
		}
		
		// 최종 출력
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	// 폭탄 설치 메소드
	public static void installBomb() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='.') {
					map[i][j] = 'O';
				}
			}
		}
	}
	
	// 폭탄 터짐 메소드
	public static void bomb() {
		for(int k=0; k<list.size(); k++) {
			int[] now = list.get(k);
			map[now[0]][now[1]] = '.';
			for(int d=0; d<4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				
				if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
					map[nr][nc] = '.';
				}
			}
		}
	}
	
	// 나중 설치된 폭탄 터짐 메소드
	public static void bombAnother() {
		for (int k = 0; k < list3second.size(); k++) {
			int[] now = list3second.get(k);
			map[now[0]][now[1]] = '.';
			for (int d = 0; d < 4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					map[nr][nc] = '.';
				}
			}
		}
	}
	
	// 메소드 호출마다 리스트 새롭게 선언(초기화) 후 폭탄 위치 값들 저장
	public static void delta1() {
		list3second = new ArrayList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='O') list3second.add(new int[] {i, j});
			}
		}
	}
	
	public static void delta2() {
		list = new ArrayList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='O') list.add(new int[] {i, j});
			}
		}
	}
}