package week14;

import java.util.Scanner;

public class BOJ_21758_조아름 {
	static long[] rightTotal; // 왼쪽 -> 오른쪽 누적 합
	static long[] leftTotal; // 오른쪽 -> 왼쪽 누적 합
	static long total; //모든 장소들의 꿀 양 합
	static int[] arr;
	static int N;
	static long max;
	
	public static void main(String[] args) {
		// 벌이 있는 공간은 꿀을 딸 수 없음.
		// 시작한 공간에서도 꿀을 딸 수 없음.
		
		// 설정 아무거나 .. 벌통, 벌 자리. 
		
		// 경우의 수
		
		// 벌(고정), 벌, 벌통(고정) : 벌 왼쪽 고정, 꿀통 오른쪽 고정
		// 벌통(고정), 벌, 벌(고정) : 벌 오른쪽 고정, 꿀통 왼쪽 고정
		// 벌(고정), 벌통, 벌(고정) : 벌 양쪽 고정, 꿀통 움직일 때
		
		Scanner sc = new Scanner(System.in);
		N  = sc.nextInt();
		
		long tmp = 0;
		
		arr = new int[N]; 
		rightTotal = new long[N];
		leftTotal = new long[N];
		for(int i=0;i<N;i++) {
			arr[i] = sc.nextInt();
			
			tmp += arr[i];
			rightTotal[i] = tmp;
		}
		
		tmp = 0;
		for(int i=N-1;i>=0;i--) {
			tmp += arr[i];
			leftTotal[i] = tmp;
		}
		
		total = rightTotal[N-1];
		
		case1();
		case2();
		case3();
		
		System.out.println(max);
		
	}

	// case 1 : 벌 왼쪽 고정, 꿀통 오른쪽 고정
	private static void case1() {
		long bee1, bee2;
		
		for(int i=1;i<=N-2;i++) {
			bee1 = total - arr[0] - arr[i];
			bee2 = total - rightTotal[i];
			max = Math.max(max, bee1 + bee2);
		}
	}
	
	// 벌통(고정), 벌, 벌(고정) : 벌 오른쪽 고정, 꿀통 왼쪽 고정
	private static void case2() {
		long bee1, bee2;
		
		for(int i=N-2; i>=1; i--) {
			bee1 = total - arr[N-1] - arr[i];
			bee2 = total - leftTotal[i];
			max = Math.max(max, bee1 + bee2);
		}
		
	}
	
	private static void case3() {
		long bee1, bee2;
		
		for(int i = 1; i<=N-2;i++) {
			bee1 = rightTotal[i] - arr[0];
			bee2 = leftTotal[i] - arr[N-1];
			max = Math.max(max, bee1 + bee2);
		}
	}
}
