package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1296_조아름_correction {
	static int L = 0;
	static int O = 0;
	static int V = 0;
	static int E = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String teamName = br.readLine(); // LOVE
		int tc = Integer.parseInt(br.readLine());// 3

		String[] arr = new String[tc]; // 이름 저장하는 배열
		int[] pArr = new int[tc]; // 확률 값 저장하는 배열

		for (int c = 0; c < tc; c++) {// testcase만큼 반복
			arr[c] = br.readLine();

		}

		Arrays.sort(arr); // 이름 배열을 알파벳 순으로 정렬 , max 값이 같을 경우 문자열 순으로 출력이 자동으로 됌

		int max = Integer.MIN_VALUE;
		
		for (int c = 0; c < tc; c++) { 
			pArr[c] = count(teamName, arr[c]);
			max = Math.max(max, pArr[c]);
		}
		
		for(int c=0;c<tc;c++) {
			if(max == pArr[c]) {
				System.out.println(arr[c]);
				break;
			}
		}


	}

	public static int count(String teamName, String name) {// 만약 문자열에 해당하는 값을 가질 경우 각 int 값 증가
		int L = 0;
		int O = 0;
		int V = 0;
		int E = 0;

		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i)=='L') {
				L++;
			} else if (name.charAt(i)=='O') {
				O++;
			} else if (name.charAt(i)=='V') {
				V++;
			} else if (name.charAt(i)=='E') {
				E++;
			}
		}

		for (int i = 0; i < teamName.length(); i++) {
			if (teamName.charAt(i)=='L') {
				L++;
			} else if (teamName.charAt(i)=='O') {
				O++;
			} else if (teamName.charAt(i)=='V') {
				V++;
			} else if (teamName.charAt(i)=='E') {
				E++;
			}
		}
		
		return ((L+O)*(L+V)*(L+E)*(O+V)*(O+E)*(V+E)) % 100;
	}

}

