import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int[][] map;
    static int[][] cal;
    static boolean[] checked;
    static int N;
    static int max;
//    static int tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        cal = new int[N + 1][3];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cal[i][1] = Integer.parseInt(st.nextToken());
            cal[i][2] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            if (i + cal[i][1] <= N + 1) {
                for (int j = i + cal[i][1]; j <= N; j++) {
                    map[i][j] = 1;
                }
            }
        }
        max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            checked = new boolean[N + 1];
            DFS(i,0);
        }
        System.out.println(max);
    }

    static void DFS(int day, int tmp) {
        if (day > N) {
            max = Math.max(max, tmp);
            return;
        }
        if (!checked[day]) {
            checked[day] = true;
            if (day + cal[day][1] <= N+1) {
                DFS(day + cal[day][1], tmp + cal[day][2]);
            }
            checked[day] = false;
        }
        DFS(day+1, tmp);
    }
}
