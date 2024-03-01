package week04;


import java.util.*;
import java.io.*;


public class SWEA_1926_장현영 {
	/*
	 * 간단한 입력이라 Scanner 사용
	 * 숫자에 3,6,9가 있는 지 확인하고 박수 -로 cnt 세주기
	 * replace함수를 사용해서 박수개수 * -로 대체해도 괜찮을 것 같음
	 * 박수를 쳐야하는 경우/ 안 쳐야 하는 경우 나눠서 숫자 or -을 stringbuilder에 담아서 출력
	 * 풀이시간 15분
	 */
	public static void main(String args[]) throws Exception{ 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   
        StringBuilder sb = new StringBuilder();
        int clapCnt=0;
        boolean flag = true; // flag: 박수말고 번호인 경우 true
        for(int num=1; num <=n ; num++){
            flag = true;
            String str = Integer.toString(num);
            clapCnt = 0;
            for(int i=0; i<str.length();i++){
                if (str.charAt(i) == '3' || str.charAt(i) == '6' || str.charAt(i) == '9')
                    clapCnt++;
            }
            while(clapCnt > 0){ // 박수를 쳐야하는 경우
                flag = false;
                sb.append("-");
                clapCnt--;
            }
            if(flag) // 숫자 담아야 하는 상황
                sb.append(num);         
            sb.append(" "); // 칸 띄우기
        }
        System.out.println(sb);
    }

}
