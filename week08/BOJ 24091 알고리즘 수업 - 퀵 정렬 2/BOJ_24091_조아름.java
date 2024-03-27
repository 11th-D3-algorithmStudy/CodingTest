package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24091_조아름 {
	static int N, K;
	static int cnt = 0;
	static int[] A;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		QuickSort(A, 0, N - 1);

		if (cnt < K) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }

	}

	static void QuickSort(int[] a, int left, int right) {
		if (left < right) {
			int pivot = partition(A, left, right);
			QuickSort(A, left, pivot - 1);
			QuickSort(A, pivot + 1, right);
		}
	}

	 static int partition(int[] a2, int left, int right) {
	        int pivot = a2[right];
	        int i = left - 1;
	        for (int j = left; j < right; j++) {
	            if (a2[j] <= pivot) {
	                i++;
	                swap(a2, i, j);
	            }
	        }
	        if(i+1 != right) { // 그리고 이부분,, 수업시간엔 안나온 부분이고
	        					// 문제 요구사항에 나온 부분인데 이부분 처리가 안되면
	        					// 틀렸다고 나옴.
	        	swap(a2, i + 1, right);
	        }

	        return i + 1;
	    }

	    static void swap(int[] a, int i, int j) { // swap을 따로 빼내니까 됐다... 허무..
	        int tmp = a[i];
	        a[i] = a[j];
	        a[j] = tmp;
	        cnt++;
	        if (cnt == K) {
	            for (int x : A) {
	                sb.append(x);
	                sb.append(" "); // 그리고 조건문을 굳이 main에서 cnt값과 함께 걸어줄 필요 없이 swap안에서 걸어주는 것이 더 깔끔하다.
	            }

	        }
	    }

}
