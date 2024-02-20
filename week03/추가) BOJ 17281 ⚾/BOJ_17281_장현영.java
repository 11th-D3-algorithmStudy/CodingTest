package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17281_장현영 {

	// 야구
	// 등번호 1번~9번 선수들의 성적
	// 등번호 1번 선수(0번 INDEX)는 반드시 4번타자
	// 가장 최대로 점수뽑는 경우를 찾자
	// idx로 타순을 계속 조정
	// 단 이닝마다 타순을 바꿀수는 없으니 최적의 점수를 뽑는 타순을 미리 짜야 함
	// 모든 경우 다 보기

	// 선택하고자 하는 대상 집합.
	//static int[] target = new int[] { 1, 2, 3,4,5,6,7,8 };
	// 대상 숫자를 선택했는지를 알려주는 집합.
	static boolean[] visited = new boolean[9];
	static int[] result = new int[9];
	static int maxScore = 0;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int inning = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[inning][9];
		for (int i = 0; i < inning; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permutation(arr, 0);
		System.out.println(maxScore);
		// 다보면 8!
	}

	// 1~8 줄 세우기 -> 8!

	// 3! = 3 2 1 = 6개
	// 1 2 3
	// 1 3 2
	// 2 1 3
	// 2 3 1
	// 3 1 2
	// 3 2 1

	// 비복원추출 구현해보기
	// 순열 메서드(cnt는 선택 횟수)
	// 순열 뽑은 리스트에서 3번 idx 자리에 0삽입
	// 총 9개 숫자를 계속 돌면서(while로 무한정 늘려주면서 idx는 -=9 활용하기)
	public static void permutation(int[][] arr, int cnt) {
		// 2개를 선택했으므로, 결과를 출력하고 재귀를 종료한다.
		if (cnt == 9) {
			//System.out.println(Arrays.toString(result)); // 8! + 0
			

			//System.out.println("temp: " + temp);
			
			System.out.println(Arrays.toString(result)); // chatgpt help me
			// 점수 계산까지
	
			int score = calculScore(arr);
			maxScore = Math.max(maxScore, score);
			
			return;
		}
		if(cnt == 3) {
			result[cnt] = 0;
			permutation(arr, cnt+1);
		}
		
		// 대상 집합을 순회하며 숫자를 하나 선택한다.
		for (int i = 1; i <= 8; i++) {
			// 이미 해당 숫자를 선택한 경우에는 스킵.
			if (visited[i]) {
				continue;
			}
			// 선택하지 않은경우, 선택했다는 표시를 해준다.
			visited[i] = true;
			// 숫자를 담는다.
			result[cnt] = i;
			// 자신을 재귀 호출한다.
			permutation(arr, cnt + 1);
			// 선택을 해제한다.
			visited[i] = false;
		}
	}
	public static int calculScore(int[][] arr) {
		int score = 0;
		int batter = 0;
		int outCnt = 0;
		int inning = arr.length;
		
		// 이닝을 반복하면서 각 이닝에서의 타자 순서를 처리함
	    for (int i = 0; i < inning; i++) {
	        // 현재 이닝에서 아웃 카운트 초기화
	    	outCnt = 0;
	    	boolean[] bases = new boolean[4]; // 루 상태도 초기화 
	        // 이닝이 종료될 때까지 아웃이 3번 발생할 때까지 반복함
	        while (outCnt < 3) {
	            int hit = arr[i][result[batter]]; // 현재 이닝에서의 타자 결과를 저장함
	            // 만약 아웃이면 아웃 카운트를 증가시킴
	            if (hit == 0) {
	            	outCnt++;
	            } else {
	                // 안타 혹은 홈런인 경우를 처리함
	                // 타자가 루에 있는 주자들을 이동시키고 점수를 계산함
	                for (int runner = 3; runner >= 1; runner--) {
	                    if (bases[runner]) { //
	                        int nextPos = runner + hit;
	                        if (nextPos >= 4) {
	                            score++; // 홈런인 경우 점수를 증가시킴
	                        } else {
	                            bases[nextPos] = true; // 주자를 다음 루로 이동시킴
	                        }
	                        bases[runner] = false; // 현재 루에는 주자가 없음을 표시함
	                    }
	                }
	                // 안타가 발생하면 해당 루에 타자를 배치함
	                if (hit < 4) {
	                    bases[hit] = true;
	                } else {
	                    score++; // 홈런인 경우 점수를 증가시킴
	                }
	            }
	            batter = (batter + 1) % 9; // 다음 타자로 이동함
	        }
	    }
	    return score; // 총 점수를 반환함
	}
}