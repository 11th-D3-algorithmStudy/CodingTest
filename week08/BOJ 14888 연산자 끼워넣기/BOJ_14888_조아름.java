package week8;
import java.util.*;
import java.io.*;

public class BOJ_14888_조아름 {

	static int size;
	static int[] numArr;
	static ArrayList<Character> operList = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static char[] output;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());
		numArr = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < size; i++) { // 숫자 입력 받아 숫자 배열 만들기.
			numArr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) { // list에 연산자 값 입력받기
			int operSize = Integer.parseInt(st.nextToken());
			for (int j = 0; j < operSize; j++) {
				switch (i) {
				case 0: {
					operList.add('+');
					break;
				}
				case 1: {
					operList.add('-');
					break;
				}
				case 2: {
					operList.add('*');
					break;
				}
				default: {
					operList.add('/');
					break;
				}
				}
			}
		}
		int operSize = operList.size(); // 숫자배열을 순열로 돌리기 위해 사이즈 측정하기
		output = new char[operSize]; // 출력배열, 방문배열 만들기
		visited = new boolean[operSize];
		perm(operSize, 0); // 순열이지만 전체 크기만큼 뽑아야 하므로 operSize만큼 뽑음
		System.out.println(max); // 최대, 최소값 출력
		System.out.println(min);
	}

	static void perm(int n, int depth) {
		if (n == depth) { // n값과 깊이가 같아지면 calc에 output배열 입력
			calc(output);
			return;
		}
		
		for (int i = 0; i < n; i++) { // 연산자 순열배열 만들어주기
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = operList.get(i); // list니까 get으로 반환
				perm(n, depth + 1);
				visited[i] = false;
			}
		}
	}

	static void calc(char[] operArr) { // perm에서 만들어진 연산자 순열배열이 입력 됌.
		int answer = numArr[0]; // 숫자 배열의 첫 숫자가 answer에 저장 됌.
		for(int i = 1; i<size; i++) {
			switch(operArr[i-1]) { // 연산자 배열은 0부터 시작, size-1개 까지
			case '+':{
				answer += numArr[i]; // numArr[0]은 answer이므로 이 줄의 i는 1부터 시작
				break;
			}
			case '-':{
				answer -= numArr[i];
				break;
			}
			case '*':{
				answer *= numArr[i];
				break;
			}
			case '/':{
				answer /= numArr[i];
				break;
			}
			}
		}
		max = Math.max(max, answer);
		min = Math.min(min, answer);
	}
}
