import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15903_김민호 {
    static int n, m;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        } // 입력값 받기

        // m개만큼 반복문하는데
        // 처음에 오름차순으로 해주고
        // 맨 처음 두 개를 합해서 0, 1 인덱스에 재할당해준다.
        for (int i = 0; i < m; i++) {
            Arrays.sort(arr);
            long tmp = arr[0] + arr[1];
            arr[0] = tmp;
            arr[1] = tmp;
        }
        // arr의 값을 합해서 출력
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        System.out.println(sum);
    }
}
