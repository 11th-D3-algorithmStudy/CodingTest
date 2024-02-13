import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1037_이윤주 {
	public static void main(String[] args) throws Exception {
		//10시 16분 시작 - 10시 55분 종료 : 40분

		//패리티 성질 판단
		//각 행, 열의 합이 짝수인지 확인
		//짝수라면 OK 출력
		//짝수가 아니라면, 하나의 비트만 변경해서 패리티 성질을 가지는지 판단
		//가능 - Change bit (i,j) 출력
		//불가능 - Corrupt 출력
		//완전탐색으로 접근!!

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//행렬 크기 입력받기
		int n = Integer.parseInt(br.readLine());

		//행렬 입력받기
		int[][] arr = new int[n][n];
		for(int i = 0; i < n; i++){
		StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//패리티 성질 판단 성공했는지 확인하는 플래그
		boolean flag = true;

		//각 행마다 확인
		for(int i = 0; i < n; i++){
			int sum = 0;
			for(int j = 0; j < n; j++){
				sum += arr[i][j];
			}
			if(sum % 2 != 0){ //짝수가 아니면 플래그 false
				flag = false;
				break;
			}
		}
		//각 열마다 확인
		for(int i = 0; i < n; i++){
			int sum = 0;
			for(int j = 0; j < n; j++){
				sum += arr[j][i];
			}
			if(sum % 2 != 0){ //짝수가 아니면 플래그 false
				flag = false;
				break;
			}
		}

		//결과 담는 변수
		String result = "OK";

		//행, 열 모두 확인 후 성공이면 OK 출력
		if(flag){
			System.out.println(result);
			return;
		} 

		//짝수가 아니라면, 하나의 비트만 변경해서 패리티 성질을 가지는지 판단
		//완전탐색
		//바꾸는 비트 위치 저장하는 변수
		int idxI = 0;
		int idxJ = 0;
		//변경해서 패리티 성공하는지 확인하는 플래그
		boolean change = false;
		out:for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				//하나의 비트 변경
				if(arr[i][j] == 1){
					arr[i][j] = 0;
				} else {
					arr[i][j] = 1;
				}
				idxI = i + 1;
				idxJ = j + 1;
				//패리티 성질 판단 성공했는지 확인하는 플래그
				boolean parity = true;

				//각 행마다 확인
				for(int x = 0; x < n; x++){
					int sum = 0;
					for(int y = 0; y < n; y++){
						sum += arr[x][y];
					}
					if(sum % 2 != 0){ //짝수가 아니면 플래그 false
						parity = false;
						break;
					}
				}
				//각 열마다 확인
				for(int y = 0; y < n; y++){
					int sum = 0;
					for(int x = 0; x < n; x++){
						sum += arr[x][y];
					}
					if(sum % 2 != 0){ //짝수가 아니면 플래그 false
						parity = false;
						break;
					}
				}
				if(parity){ //패리티 성질 판단 성공
					result = "Change bit (%d,%d)";
					change = true;
					break out;
				} 

				//바꿨던 비트 다시 원상복귀
				if(arr[i][j] == 1){
					arr[i][j] = 0;
				} else {
					arr[i][j] = 1;
				}
			}
		}
		//비트바꿔서 성공하는 경우 출력
		if(change){
			System.out.printf(result, idxI,idxJ);
		} else {
			//안되는 경우
			result = "Corrupt";
			System.out.printf(result);

		}

	}
}