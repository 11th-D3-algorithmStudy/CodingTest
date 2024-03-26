import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_5568_김민호 {
    static Set<String> set;
    static String[] arr;
    static boolean[] visited;
    static String[] res;
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        k = Integer.parseInt(br.readLine().trim());
        arr = new String[n];
        set = new HashSet<>();
        visited = new boolean[n];
        res = new String[k];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().trim();
        }

        // 입력값 받기

        int count = 0;
        sol(count);

        System.out.println(set.size());

    }
    public static void sol(int count) {
        // k만큼의 숫자가 채워졌을 시 저장 해놨던 배열에서
        // String 에 더해나가고,
        // set 자료구조를 이용해 중복 제거
        if (count == k) {
            String s = "";
            for (int i = 0; i < k; i++) {
                s += res[i];
            }
            set.add(s);
            return;
        }

        // 백트래킹을 활용해서 숫자 만들기
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res[count] = arr[i];
                sol(count + 1);
                visited[i] = false;
            }
        }
    }
}
