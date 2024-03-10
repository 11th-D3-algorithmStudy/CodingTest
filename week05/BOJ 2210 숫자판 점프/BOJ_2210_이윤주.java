import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2210_이윤주 {
	/*
	 * Q. 숫자판 점프
	 *  #문제요약
	 *  	숫자판 임의의 위치에서 시작 상하좌우 랜덤으로 5번 이동
	 *  	이동했던곳 다시 방문 가능 
	 *  	만들 수 있는 서로 다른 여섯 자리 수들의 개수를 구하기
	 *  	입력 : 5*5 정수 숫자판
	 *  	출력 : 만들 수 있는 수들의 개수 출력
	 *  #풀이시간 : 2시간
	 *  #메모리/시간 : 24928kb/248ms
	 *  #메인접근법
	 *  	1. 상하좌우 4방탐색
	 *  	2. 서로 다른 6자리수를 어떻게 저장하지? -> hashset에 넣어서 중복제거!!(지영 힌트)
	 *  	3. 4^5 * 25 = 102400 -> 시간제한에 걸리지 않는다고 판단해서 완전탐색으로!! (지영 힌트)
	 *  	4. 백트래킹으로 생각해보려고 했지만, 왔던곳을 다시 갈 수 있기 때문에 모르겠다!!!
	 *  	5. 스트링에 int 더하면 뒤에 숫자추가됨
	 *  	6. lastIndexOf(int ch) -> 문자의 int 값이므로 입력값에 주의!!(이걸로 30분날림)
	 */
	static int[][] board;
	static int N = 5;
	static int count;
	static String num;
	static Set<String> numList;
	//델타배열 상하좌우
	static int[] dr = {-1, 1, 0 ,0};
	static int[] dc = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][N];
		
		//숫자판 입력
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count = 0;
		num = new String("");
		numList = new HashSet<>();
		
		//숫자판 모든 곳에서 6자리 숫자 만들기
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				make6num(r,c);
			}
		}
		//해쉬셋으로 중복제거해서 숫자 개수 세기
		count = numList.size();
		
		System.out.println(count);
	}

	public static void make6num(int r, int c) {
		//기저조건
		if(num.length() == 6) {
			numList.add(num); //해쉬셋에 넣으면 자동으로 중복제거
			return;
		}
		
		//재귀부분
		//상하좌우 4개 num에 추가하기	
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
				//일단 내꺼 자리에 추가해
				num += board[r][c];
				make6num(nr, nc); //다음 자리 추가해줘~
				int idx = num.lastIndexOf((char)board[r][c] + '0'); //lastIndexOf(int ch) -> int 형이므로 입력값에 주의!! (이걸로 30분날림)
				num = num.substring(0, idx); //추가한거 다시 없애줌
			}
		}
	}
}
