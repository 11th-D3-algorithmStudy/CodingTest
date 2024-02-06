package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1296_조아름 {
	static int L = 0;
	static int O = 0;
	static int V = 0;
	static int E = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = br.readLine(); // LOVE
		int tc = Integer.parseInt(br.readLine());// 3
		count(name);
		
		String[] arr = new String[tc]; // 이름 저장하는 배열
		int[] pArr = new int[tc]; // 확률 값 저장하는 배열

		for (int c = 0; c < tc; c++) {// testcase만큼 반복
			arr[c] = br.readLine();
			count(arr[c]);
			
			int percentage = ((L+O) * (L+V) * (L+E) * (O+V) * (O+E) * (V+E)) % 100;
			pArr[c] = percentage;
		}
		
		int max = Integer.MIN_VALUE;
		int value = 0;
		for(int c=0; c<tc; c++) {// 해야할 것 : max 값이 같을 경우 문자열 순으로 출력
			if(max<pArr[c]) {
				max = pArr[c];
				value = c;
			}
		}
		
		System.out.println(arr[value]);
		
	}

	public static void count(String name) {//만약 문자열에 해당하는 값을 가질 경우 각 int 값 증가
		if(name.contains("L")==true){
			L++;
		}else if(name.contains("O")==true) {
			O++;
		}else if(name.contains("V")==true) {
			V++;
		}else if(name.contains("E")==true) {
			E++;
		}
		
	}


}

// 그나마 비슷한 코드 : https://comengin.tistory.com/299
