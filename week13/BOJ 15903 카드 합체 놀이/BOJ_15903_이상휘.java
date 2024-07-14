import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15903_이상휘 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 카드의 장수
        int n = Integer.parseInt(st.nextToken());

        // 카드 합치는 횟수
        int m = Integer.parseInt(st.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());

        // 카드 리스트
        long[] list = new long[n];

        for (int i = 0; i < n; i++) {
            list[i] = Long.parseLong(st2.nextToken());
        }

        // m번 반복
        for (int test = 0; test < m; test++) {
            long min1 = Long.MAX_VALUE;
            long min2 = Long.MAX_VALUE;
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                min1 = Math.min(min1, list[i]);
            }
            for (int i = 0; i < n; i++) {
                // 최소값 인 경우 (min1)
                if(list[i] == min1) {
                    cnt++;
                } else { // min1 이외의 최솟값
                    min2 = Math.min(min2, list[i]);
                }
                if (cnt >= 2) {
                    min2 = min1;
                }
            }
            long tmp = min1 + min2;
            int check = 0;

            Arrays.sort(list);

            for (int i = 0; i < n; i++) {
                if (check == 2) {
                    break;
                }
                if (list[i] == min1 || list[i] == min2) {
                    list[i] = tmp;
                    check++;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += list[i];
        }
        System.out.println(ans);

    }
}
