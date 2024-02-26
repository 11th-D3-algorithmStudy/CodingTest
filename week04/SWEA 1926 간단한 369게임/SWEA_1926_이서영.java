
import java.util.Scanner;

public class SWEA_1926_이서영 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			int num = i;
			boolean is369 = false;
			while (num > 0) {
				int remainder = num % 10;
				if (remainder == 3 || remainder == 6 || remainder == 9) {
					sb.append("-");
					is369 = true;
				}
				num /= 10;
			}
			if (!is369) {
				sb.append(i);
			}
			sb.append(" ");
		}
		System.out.println(sb);
	}
}
