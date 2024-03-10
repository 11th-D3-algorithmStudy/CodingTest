package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA_4047_조아름 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 테스트 케이스 값 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		// 테스트 케이스만큼 반복해서 String값으로 입력 받기
		label : for (int tc = 0; tc < T; tc++) {
			String txt = br.readLine();

			// 길이 3만큼 쪼개서 ArrayList에 넣기
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i <= txt.length() - 3; i += 3) {
				list.add(txt.substring(i, i + 3));
			}

			int[] arr = { 13, 13, 13, 13 };
			boolean isError = false;

			// Arraylist를 확인하면서 같은 이름일 경우 ERROR 반환
			for (int i = 0; i < list.size(); i++) {
				for (int j = i + 1; j < list.size(); j++) {
					if (list.get(i).equals(list.get(j))) {
						System.out.println("#" + (tc + 1) + " " + "ERROR");
						isError = true;
						break;// 출력이 끝났으므로 멈춰준다. 
					}
				}
				if(isError) {
					continue label; // label로 가면 tc 증가
				}
			}

			// Arraylist의 각 첫 값을 확인하고 SDHC에서 빼주기

			for (int i = 0; i < list.size(); i++) {
					if (list.get(i).charAt(0) == 'S') {
						arr[0] -= 1;
					} else if (list.get(i).charAt(0) == 'D') {
						arr[1] -= 1;
					} else if (list.get(i).charAt(0) == 'H') {
						arr[2] -= 1;
					} else if(list.get(i).charAt(0) == 'C'){
						arr[3] -= 1;
					}
				}

			System.out.println("#" + (tc + 1) + " " + arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]);

		}

	}

}
