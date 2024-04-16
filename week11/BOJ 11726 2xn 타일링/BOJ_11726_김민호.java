import java.util.Scanner;

public class BOJ_11726_김민호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 입력값 받기

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;
        // 초기값 넣기

        // 1부터 가능한 모양을 그리다보면
        // 홀수 번호에는 홀수 세로 한 칸짜리를 넣을 수 있고
        // 짝수 번호에는 짝수 개에 세로 한 칸짜리 두 개, 혹은 가로 한 칸짜리 두 개를 추가할 수 있으므로
        // dp[i] = dp[i - 1] + dp[i - 2] 이다.
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        System.out.println(dp[n]);
    }
}