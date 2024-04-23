package Study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_이윤주 {
	// 활주로 건설

	// 활주로를 건설할 수 있는 경우의 수를 구하라

	static int T, N, X;
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("활주로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //한변의크기 6~20
			X = Integer.parseInt(st.nextToken()); //경사로길이 2~4
			
			map = new int[N][N]; //지형정보 저장 (높이 : 1~6)
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//지형정보 입력 완
			
			answer = 0;
			
			//행우선순회
			for(int r = 0; r < N; r++) {
				if(check_row(r))
					answer++;
			}
			//열우선순회
			for(int c = 0; c < N; c++) {
				if(check_col(c))
					answer++;
			}
			
			System.out.printf("#%d %d\n", tc, answer);
			
		}//tc
	}//main

	//col:현재 열에 활주로 건설가능한지 확인하는 메서드
	private static boolean check_col(int col) {
		boolean[] check = new boolean[N]; //경사로 설치했는지 표시하는 배열
		int cnt = 1;
		
		for(int row = 1; row < N; row++) {
			int before = map[row - 1][col];
			int now = map[row][col];
			
			//차이가 2이상이면 불가능!
			if (Math.abs(now - before) >= 2) return false;
			
			if(now == before) { 
				//직전과 같은 높이
				cnt++;
			} else if(now == before + 1) {
				//직전보다 1 높은 경우 
				//지금까지 카운트한게 X 이상이면 경사로 설치하기
				if(cnt >= X) {
					cnt = 1;
					for(int i = row - X; i < row; i++) {
						if(check[i]) return false; //이미 경사로가 놓여있다...불가능!
						check[i] = true;
					}
					continue;
				}
				return false; //길이 부족해서 불가능!!
		
			} else if(now == before - 1) {
				//직전보다 1 낮은 경우
				if(row + X - 1 >= N) { //row~row+X-1 까지 X길이의 경사로 놓아야됨
					//경사로 놓을 공간이 없다!!
					return false;
				}
				
				//경사로 놓을 수 있는지 확인
				for(int i = row + 1; i <= row + X - 1; i++) {
					if(check[i] || now != map[i][col]) return false; //이미 경사로가 놓여있거나, 높이가 달라지면...불가능!!
					
					check[i] = true;
				}
				
				cnt = 1;
				row = row + X - 1; //다음위치부터 확인~
			} 
			
		}
		//여기까지 왔다는 건,,,이번 열은 활주로 놓을 수 있다는 것
		return true;
	}
	//row:현재 행에 활주로 건설가능한지 확인하는 메서드
	private static boolean check_row(int row) {
		boolean[] check = new boolean[N]; //경사로 설치했는지 표시하는 배열
		int cnt = 1;
		
		for(int col = 1; col < N; col++) {
			int before = map[row][col - 1];
			int now = map[row][col];
			
			//차이가 2이상이면 불가능!
			if (Math.abs(now - before) >= 2) return false;
			
			if(now == before) { 
				//직전과 같은 높이
				cnt++;
			} else if(now == before + 1) {
				//직전보다 1 높은 경우 
				//지금까지 카운트한게 X 이상이면 경사로 설치하기
				if(cnt >= X) {
					cnt = 1;
					for(int i = col - X; i < col; i++) {
						if(check[i]) return false; //이미 경사로가 놓여있다...불가능!
						check[i] = true;
					}
					continue;
				}
				return false; //길이 부족해서 불가능!!
				
			} else if(now == before - 1) {
				//직전보다 1 낮은 경우
				if(col + X - 1 >= N) { //row~row+X-1 까지 X길이의 경사로 놓아야됨
					//경사로 놓을 공간이 없다!!
					return false;
				}
				
				//경사로 놓을 수 있는지 확인
				for(int i = col + 1; i <= col + X - 1; i++) {
					if(check[i] || now != map[row][i]) return false; //이미 경사로가 놓여있거나, 높이가 달라지면...불가능!!
					
					check[i] = true;
				}
				
				cnt = 1;
				col = col + X - 1; //다음위치부터 확인~
			} 
			
		}
		//여기까지 왔다는 건,,,이번 열은 활주로 놓을 수 있다는 것
		return true;
	}
}

