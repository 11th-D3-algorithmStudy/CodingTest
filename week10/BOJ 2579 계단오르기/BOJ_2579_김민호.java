import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579_김민호 {
    static int[] map;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        map = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            map[i] = Integer.parseInt(br.readLine());
        } // 입력값 받기

        // dp의 첫 번째 값 저장
        dp[1] = map[1];

        // 계단이 1일 수도 있으므로 계단 개수가 2 이상이면
        // dp의 두 번째 값 저장
        if (n >= 2) {
            dp[2] = map[1] + map[2];
        }

        // 두 칸 전의 거라면 두 칸 전의 것 + 현재 위치의 값 더해주고
        // 한 칸 전의 것이라면 한 칸 전 것 + 두 칸 전 것을 더해 준 후 현재 위치의 값 더해줌
        // dp값은 이미 최대값이 저장된 것이므로
        // 한 칸 전 + 두 칸 전 것의 경우, 한 칸 전의 것은 해당 계단의 값 자체로 하고
        // 나머지 값은 dp 값을 이용해서 비교해준다.
        for (int i = 3; i < n + 1; i++) {
            dp[i] = Math.max(map[i - 1] + dp[i - 3], dp[i - 2]) + map[i];
        }
        System.out.println(dp[n]);
    }
}