import java.util.*;
import java.io.*;

public class Main {

    static class Sangdam {
        int T; // 걸리는 기간
        int P; // 비용

        Sangdam(int T, int P) {
            this.T = T;
            this.P = P;
        }
    }

    static int N;
    static Sangdam[] sdArr;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sdArr = new Sangdam[N];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            sdArr[i] = new Sangdam(t, p);
        }

        dfs(0, 0);

        System.out.println(max);
    }

    // n : idx, p : 비용
    static void dfs(int n, int p) {
        if (n >= N-1) {
            if (n == N-1 && sdArr[n].T == 1) {
                p += sdArr[n].P;
            }
            max = Math.max(max, p);
            return;
        }

        if (sdArr[n].T <= N-n) {
            dfs(n+sdArr[n].T, p+sdArr[n].P);
        }
        dfs(n+1, p);
    }
}
