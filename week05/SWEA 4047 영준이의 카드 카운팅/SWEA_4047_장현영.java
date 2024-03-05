package week05;

import java.io.*;
import java.util.*;

public class SWEA_4047_장현영 {
	// 숫자놀이
	// 13 13 13 13 초기세팅하고
	// 중복이 있다면 해당 열에서 값 뺴기
	// 52개의 cnt배열만들고 S D H C 순으로
	// 2가 되는 순간 ERROR띄우기 (중복검사 먼저)
	// 그 후에 값만큼 빼기
	// 다 풀고 나서 보니 for문 인덱스가 너무 복잡함 ㅠ
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Map<Character, Integer> map = new HashMap<>();
		map.put('S', 0);
		map.put('D', 13);
		map.put('H', 26);
		map.put('C', 39);
		for (int t = 1; t <= T; t++) {
			arr = new int[53]; // 0은 더미 인덱스 1부터 사용
			String temp = br.readLine();
			int len = temp.length(); // 보유 카드수
			boolean flag = false; // error가 나면 true로 전환
			for (int i = 0; i < len; i += 3) { // 0 3 6 9
				int index = map.get(temp.charAt(i));
				for (int j = i + 1, cnt = 10; j <= i + 2; j++) { // 1 2 4 5..
					index += (temp.charAt(j) - '0') * cnt;
					cnt/=10;
				}
				arr[index]++;
				if (arr[index] > 1) {
					flag = true;
					break;
				}
			}
			if(flag) { 
				System.out.println("#" + t + " " + "ERROR");
				continue; 
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#"+t+" ");
			System.out.println(Arrays.toString(arr));
			for(int start =1; start<52;start+=13) { // 1 14 27 40
				sb.append(calc(start)).append(" ");
			}
			System.out.println(sb);
		}
		
	}

	public static int calc(int start) { // 
		int sum = 0;
		for (int i = start; i < start + 13; i++) {// 13번
			sum += arr[i];
		}
		return (13 - sum);
	}
}
