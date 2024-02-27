package swea;

import java.util.Scanner;

public class SWEA_1926_조아름 {

	// 54분 소요

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int N = sc.nextInt();
	    
	    String[] arr = new String[N+1];
	    
	    for(int i=1;i<=N;i++) {
	        arr[i] = Integer.toString(i);
	    }
	    
	    for(int i=1;i<=N;i++) {
	        if(arr[i].contains("3")||arr[i].contains("6")||arr[i].contains("9")) {
	            arr[i] = arr[i].replaceAll("[369]","-").replaceAll("\\d", "");
	            // 정규 표현식 활용
	            // 앞의 replaceAll 은 3,6,9가 포함된 단어를 -로 변경, \\d -> 는 숫자를 공백으로 변환 
	        }
	    }
	    
	    for(int i=1;i<=N;i++) {
	        if(arr[i]==arr[N]) {
	            System.out.print(arr[i]);
	        }else {
	            System.out.print(arr[i]+" ");
	        }
	        
	    }
	}

}
