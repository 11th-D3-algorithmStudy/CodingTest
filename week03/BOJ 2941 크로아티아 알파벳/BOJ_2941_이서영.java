import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941_이서영 {
	// 처음에는 앞에서부터 읽어서 모든 경우의 수를 찾았는데 코드가 너무 복잡해지고 가독성 떨어짐
	// 뒤에서부터 읽으면 첫번째 알파벳으로 고려할 문자가 현저히 줄어 이 방법 선택
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		char[] arr = text.toCharArray();
		int count = 0;
		for (int i = arr.length - 1; i >= 0; i--) { // 뒤에서부터 읽어옴
			char alph = arr[i];

			if (alph == '=' && i > 0) {
				if (i > 1 && arr[i - 1] == 'z' && arr[i - 2] == 'd') { 
					i -= 2; // 2칸 앞으로 넘어감
				} else if (arr[i - 1] == 'c' || arr[i - 1] == 's' || arr[i - 1] == 'z') {
					i--; // 1칸 앞으로 넘어감
				}
			} else if (alph == '-' && i > 0) {
				if (arr[i - 1] == 'c' || arr[i - 1] == 'd') {
					i--;
				}
			} else if (alph == 'j' && i > 0) {
				if (arr[i - 1] == 'l' || arr[i - 1] == 'n') {
					i--;
				}
			}
			count++; // i와 별개로 카운트 해 i를 스킵한만큼 arr.length에서 줄어든 값
		}
		System.out.println(count);
	}
}
