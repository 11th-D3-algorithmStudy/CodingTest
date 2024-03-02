package preparing_im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 범위따지기
// 가로,세로가 겹치는 값을 매겼을 때
// 가로,세로 겹치는 값이 0,1,2 이상 이라고 할 떄
// 0이 하나라도 포함된다면 d
// 1-1 조합 : c
// 1-2이상 조합 : b
// 2이상-2이상 : a
public class BOJ_2527_장현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int[] arrR = new int[4];
			int[] arrC = new int[4];
			for (int j = 0; j < 8; j++) {
				if(j%2==1) arrR[j/2] = Integer.parseInt(st.nextToken());
				else arrC[j/2] = Integer.parseInt(st.nextToken());
			}	
			
			// 겹치기 파악
			int r = calc(arrR);
			int c = calc(arrC);
			char ans;
//			System.out.println("r: " + r);
//			System.out.println("c: " + c);
			if (r*c == 0) ans = 'd';
			else if(r*c == 1) ans = 'c';
			else if (r == 1 || c==1) ans ='b';
			else ans ='a';
			System.out.println(ans);
		
		}

	}
	public static int calc(int[] arr) {
		// a b c d
		// a,c 비교 후 적은 값을 맨 앞 값으로 판단하기!(a b c d or c d a b)
		if(arr[0] > arr[2]) {
			// c d a b로 바꾸기
			for(int idx=0;idx<2;idx++) {
				int temp = arr[idx];
				arr[idx] = arr[idx+2];
				arr[idx+2] = temp;
			}
		}
		// 겹치는 값 판단 
		// 점이면 1 안겹치면 0 선이면 2 로 return 하도록
		// a b c d에서 b-c한 값으로 겹치는 값을 판단하기
		int num = arr[1]-arr[2]; 		
		if(num <0) return 0;
		if(num == 0) return 1;
		return 2; 
	}
}
