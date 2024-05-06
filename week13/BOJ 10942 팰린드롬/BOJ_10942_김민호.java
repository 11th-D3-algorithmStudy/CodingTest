import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942_김민호 {
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int t = Integer.parseInt(br.readLine());
        // 입력값 받기

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sol(a, b); // 팰린드롬 확인 메서드
        }

        // 최종값 출력
        System.out.println(sb.toString());
    }
    public static void sol(int a, int b) {
        // 처음에 왼쪽, 오른쪽 범위에 대한 arr를 새로 만들었다가
        // 시간초과가 나서 생각해보니
        // 값 그대로 사용해도 상관 없었다..

        int left = a;
        int right = b;

        // 왼쪽, 오른쪽 비교하면서 다르면 0
        // while 문을 빠져나가면 1
        while (left <= right) {
            if (arr[left] != arr[right]) {
                sb.append("0").append("\n");
                return;
            }
            left++;
            right--;
        }
        sb.append("1").append("\n");
        return;
    }
}
