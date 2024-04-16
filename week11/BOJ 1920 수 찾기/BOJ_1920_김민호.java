import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_김민호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 입력값 받기

        // 이분탐색을 위해서는 소팅을 해야한다.
        Arrays.sort(arr);

        // 찾아야하는 값들의 배열 입력 받기
        int m = Integer.parseInt(br.readLine().trim());
        int[] arr1 = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        // m개가 있는 배열을 탐색하면서
        // 원래 배열에서 이분탐색을 해서
        // 찾는 값이 있다면 1을
        // 찾는 값이 없다면 0을 출력
        for (int i = 0; i < m; i++) {
            boolean flag = false;
            int l = 0;
            int r = n - 1;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (arr1[i] < arr[mid]) {
                    r = mid - 1;
                } else if (arr1[i] > arr[mid]) {
                    l = mid + 1;
                } else if (arr1[i] == arr[mid]) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
