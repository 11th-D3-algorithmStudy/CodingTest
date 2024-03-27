package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_24061_이윤주 {
	/*
	 * Q. 병합정렬2 
	 * 병합정렬로 배열을 오름차순 정렬할 경우 
	 * 원소가 K번 변경된 직후의 배열 A를 출력하라
	 * 
	 */
	static int N, K, count;
	static int[] A;
	static int[] tmp; // 임시저장용 배열
	static int[] res; // 결과저장 배열
	static boolean find;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		tmp = Arrays.copyOf(A, N); //A배열 복사

		count = 0; // 변경횟수 카운트
		find = false;
		mergeSort(0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		
		if(find) {
			for (int i : res) {
				sb.append(i+" ");
			}			
			System.out.println(sb);
		} else {
			System.out.println(-1);			
		}
	}

	// right, left
	private static void mergeSort(int left, int right) {
		if(find) {
			return;
		}
		
		int mid = (right + left) / 2;

		if(left < right) {
			mergeSort(left, mid);
			mergeSort(mid+1, right);
			merge(left, mid, right);
		}
	}

	private static void merge(int left, int mid, int right) {
		int L = left;
		int R = mid + 1;
		int idx = left; //임시 배열에 저장할 위치
		
		//왼쪽 구간과 오른쪽 구간을 비교한다
		while(L <= mid && R <= right) { 
			if(A[L] <= A[R]) {
				tmp[idx++] = A[L++];
			}
			else {
				tmp[idx++] = A[R++];
			}
			count++;
			if(count == K) {
				res = Arrays.copyOf(tmp, N);
				find = true;
			}
		}
		
		//왼쪽구간에 남은게 있을 때
		if(L <= mid) {
			for(int i = L; i <= mid; i++) {
				tmp[idx++] = A[i];
				count++;
				if(count == K) {
					res = Arrays.copyOf(tmp, N);
					find = true;
				}
			}
		} else { //오른쪽 구간에 남았을 때
			for(int i = R; i <= right; i++) {
				tmp[idx++] = A[i];
				count++;
				if(count == K) {
					res = Arrays.copyOf(tmp, N);
					find = true;
				}
			}
		}
		//정렬 결과를 다시 원본에 덮어쓰기
		for(int i = left; i <= right; i++) {
			A[i] = tmp[i];
 		}
	}
}
