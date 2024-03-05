import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_12789_이서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		String[] nums = br.readLine().split(" ");
		Stack<Integer> ogLine = new Stack<>();
		Stack<Integer> newLine = new Stack<>();
		for (int i = count - 1; i >= 0; i--) { // 뒤에서부터 스택에 더해줌
			ogLine.push(Integer.parseInt(nums[i]));
		}
		int curr = 1;
		while (true) {
			if (ogLine.empty()) { 
				break;
			}
			if (!ogLine.empty() && ogLine.peek() == curr) { // 원래 줄의 가장 앞이 현재 순서일 경우 팝
				ogLine.pop();
				curr++;
			}else if (!newLine.empty() && newLine.peek() == curr) { // 새로 생긴 줄의 앞이 현재 순서일 경우 팝
				newLine.pop();
				curr++;
			}
			else { // 어떤 경우도 해당되지 않을때 원래 줄에서 새로생긴 줄로 이동
				newLine.push(ogLine.pop());
			}
		}
		boolean isPossible = true;
		while (!newLine.isEmpty()) { // 새로생긴 줄이 빌때까지 혹은 순서가 맞지 않을때까지 순회하며 확인
			if (newLine.peek() == curr) {
				newLine.pop();
				curr++;
			}else {
				isPossible = false;
				break;
			}
		}
		if (isPossible) {
			System.out.println("Nice");
		}else {
			System.out.println("Sad");
		}
	}
}
