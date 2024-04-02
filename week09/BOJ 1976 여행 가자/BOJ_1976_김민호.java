import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_김민호 {
    // 유니온파인드로 대표자 찾아서
    // 첫번째 도시의 대표자와 그 뒤 도시들의 대표자가 같다면
    // 모두 연결되어 있으면서, 대표자가 같은 것이므로
    // 해당 순서대로 여행을 할 수 있다..
    static int v, n;
    static int[][] map;
    static int[] arr;
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        v = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        map = new int[v + 1][v + 1];

        for (int i = 1; i < v + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < v + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 인접행렬 입력값 받기

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        } // 여행 순서 입력값 받기

        p = new int[v + 1];

        for (int i = 1; i < v+1; i++) {
            p[i] = i;
        } // makeSet

        for (int i = 1; i < v + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (i < j) {
                    if (map[i][j] == 1) {
                        union(i, j);
                    }
                }
            }
        } // 양방향이므로 한 쪽 방향에서의 연결된 곳에서만
        // union 해줘도 무방하므로, i < j 일 때 union 해주기

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (find(arr[i]) != find(arr[0])) {
                flag = true;
                break;
            }
        } // 여행 순서의 각각의 findSet 과 첫 번째 도시의 findSet이
        // 같다면 해당 순서로 여행이 가능.. -> 중복으로 왔다갔다 가능하니까
        // 대표자가 다르다면 연결이 안 되어 있거나, 순서에 맞게 여행이 불가능

        if (flag) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
    private static int find(int x) {
        if (p[x] == x) {
            return x;
        }
        return find(p[x]);
    }
    private static void union(int x, int y) {
        p[find(y)] = find(x);
    }
}