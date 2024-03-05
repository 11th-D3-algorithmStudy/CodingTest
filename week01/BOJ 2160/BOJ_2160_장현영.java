package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 완전탐색
// array끼리 비교해서 가장 적은 거 답구하기
// list에 array형태의 값 담는거 생각하는데 꽤 걸림 

public class BOJ_2160_장현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		List<char[][]> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {

			char[][] arr = new char[5][7];

			for (int r = 0; r < 5; r++) {
				String str = br.readLine();
				arr[r] = str.toCharArray();
				//System.out.println(Arrays.toString(arr[i]));
			}
			//System.out.println();
			list.add(arr);
		}
		// n개와 나머지 만큼 비교하기
		int minValue = Integer.MAX_VALUE;
		int[] answer = new int[2];
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int res = calc(list.get(i),list.get(j));
				//System.out.println("res " + res);
				if(res < minValue) {
					minValue = res;
					answer[0] = i+1;
					answer[1] = j+1;
				}
			}
		}
		//System.out.println(minValue);
		for(int i=0;i<2;i++)
			System.out.print(answer[i]+" ");
	}

	public static int calc(char[][] arr1, char[][] arr2) {

		int result = 0; // 다른 만큼 총 합계

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				if (arr1[i][j] != arr2[i][j]) {
					System.out.println("다른 곳 좌표 "+i+" "+j);
					result++;
				}
			}
		}
		return result;
		
	}

}
