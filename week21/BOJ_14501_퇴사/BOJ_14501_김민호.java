import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_김민호 {
    static int n;
    static int[][] map;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(max);

    }
    public static void dfs(int index, int sum) {
        if (index == n) {
            max = Math.max(max, sum);
        }

        if (index > n) {
            return;
        }

        for (int i = index; i < n; i++) {
            dfs(i + map[i][0], sum + map[i][1]);
            dfs(i + 1, sum);
        }
    }
}
