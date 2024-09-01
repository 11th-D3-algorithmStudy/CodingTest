import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }
        int[] count = new int[n];

        // 기본값 1로 채워둠 
        Arrays.fill(count, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 수치 비교후, 앞에보다 수치가 크면, 앞에 최대로 담긴거랑, 내가 담고있는거랑 비교함
                if (list[i] > list[j]) {
                    count[i] = Math.max(count[i], count[j] + 1);
                }
            }
        }
        // 최대 갯수 찾기
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            maxCount = Math.max(maxCount, count[i]);
        }

        System.out.println(maxCount);
    }
}
