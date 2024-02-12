package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class queuestack_시간초과 {
	public static void main(String[] args) throws IOException {
		// 첫번째 줄에서 queue 또는 stack의 개수 확인 (N)
		// 두번째 줄에서 각 자료구조 queue인지 stack인지 확인
		// 세번째 줄은 각 자료구조에 이미 들어가 있는 원소
		// 네번째 줄은 삽입할 수열 길이 (M)
		// 다섯번째 줄은 삽일할 수열 (M개의 원소)
		
		// N개의 자료구조이지만 자료구조 당 1개의 원소만 있게 되므로
		// N의 길이를 가진 하나의 배열로 표현하자 ~ 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 자료구조 개수
		int N = Integer.parseInt(br.readLine());
		
		// 출력 스트링
		StringBuilder sb = new StringBuilder();
		
		// 배열 입력
		int[] typeArr = new int[N]; // 각 자료구조 type 배열
		int[] qsArr = new int[N]; // queuestack 배열 
		
		// 자료구조 type
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		// 각 자료구조 초기 요소
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N; i++) {
			typeArr[i] = Integer.parseInt(st1.nextToken());
			qsArr[i] = Integer.parseInt(st2.nextToken());
		}
		
		// 입력할 원소 배열
		int M = Integer.parseInt(br.readLine());
		int[] inpArr = new int[M];
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		
		for (int i=0; i<M; i++) {
			inpArr[i] = Integer.parseInt(st3.nextToken());
		}
		
		// M번 돌면서 원소 입력
		for (int i=0; i<M; i++) {
			// typeArr[j] : 자료구조, qsArr[j] : 기존 요소, input : 입력값
			// pop할때 나오는 원소의 조건을 자료구조 유형에 따라 다르게 주자..~
			int input = inpArr[i]; 
			int pop = inpArr[i]; // pop 요소 (= 다음 자료구조로 넘길 값, input의 임시변수)
			// 각 자료구조의 타입에 따른 pop 요소 찾기 
			for (int j=0; j<N; j++) {
				if (typeArr[j] == 0) { // queue -> 입력값 삽입, 기존 요소 pop 
					pop = qsArr[j]; // 기존 요소 pop
					qsArr[j] = input; // 입력값 삽입
					input = pop; // 다음 자료구조로 넘길 값 업데이트 
				} else { // stack -> 입력값 그대로 pop
					continue;
				}
			}
			// 최종 pop 출력 
			sb.append(pop+" ");
		}
		System.out.println(sb);
	}
}


