import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_24511_이서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int seqCount = Integer.parseInt(br.readLine());
		StringTokenizer seq = new StringTokenizer(br.readLine()); // 주어진 스택&큐 배열
		StringTokenizer seqElem = new StringTokenizer(br.readLine()); // 각 자료구조에 들어갈 원소의 배열
		int pushCount = Integer.parseInt(br.readLine());
		Stack<String> expected = new Stack<>(); // 예상결과값 스택
		int curr = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < seqCount; i++) {
			if (seq.nextToken().equals("0")) { // 만약 큐이면
				curr++; // 큐 갯수 증가시키고
				expected.push(seqElem.nextToken()); // 스택에 푸시
			}else {
				seqElem.nextToken(); // 스택이면 변화없으므로 무시
			}
		}
		
		for (int i = 0; i < Math.min(curr, pushCount); i++) { // 큐 갯수와 푸시해야하는 수 비교후 작은 수 만큼 스택에서 팝해서 저장 
			sb.append(expected.pop() + " ");
		}
		if (pushCount > curr) { // 만약 스택을 비우고도 푸시해야할 숫자가 남았다면
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < (pushCount - curr); i++) {
				sb.append(st.nextToken() + " "); // 숫자 주어진 숫자대로 스트링에 저장
			}
		}
			
		System.out.println(sb.toString().trim());
	}
}
