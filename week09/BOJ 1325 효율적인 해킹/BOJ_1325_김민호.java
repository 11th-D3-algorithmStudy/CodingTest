import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_김민호 {
    // 계속 시간초과가 나와서, 구글링해서 나온거
    // 다 시도해봤는데 다 시간초과..
    // 갓지영한테 코드 받아서 통과..
    // 가장 중요한 것은 Queue의 구현체를 LinkedList가 아닌
    // ArrayDeque로 하는 것이 포인트..

    static int n;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    static int[] count;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        count = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }
        // 배열 리스트 입력값 받기

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }
        // 단방향으로 정점 추가

        for (int i = 1; i < n + 1; i++) {
            visited = new boolean[n + 1];
            bfs(i);
        }
        // bfs로 각각의 해킹 가능한 개수 배열에 저장

        for (int i = 0; i < n + 1; i++) {
            maxCount = Math.max(maxCount, count[i]);
        }
        // 카운트 배열 중에 맥스값을 재할당

        for (int i = 0; i < count.length; i++) {
            if (count[i] == maxCount) {
                sb.append(i + " ");
            }
        }
        // 카운트 배열 중에 맥스값이랑 같은 값 스트링빌더에 담기

        System.out.println(sb);
        // 출력
    }

    public static void bfs(int node) {
        Queue<Integer> que = new ArrayDeque<>();
        visited[node] = true;
        que.add(node);

        while (!que.isEmpty()) {
            int pollNumber = que.poll();

            for (int availableList : list[pollNumber]) {
                if (visited[availableList])
                    continue;
                visited[availableList] = true;
                count[availableList]++;
                que.add(availableList);
            }

        }
    }
}