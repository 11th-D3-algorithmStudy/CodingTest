package _0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_7465_김민호 {
    // 두 사람간의 관계의 개수를 세는 것이므로
    // dfs를 사용해서 덩어리마다의 개수를 세는 것으로 풀어야 곘다고 생각함
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            // 정점과 간선 수 입력
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            // 그래프와 방문이력 배열 선언 및 초기화
            graph = new ArrayList<>();
            visited = new boolean[vertex + 1];
            count = 0;

            for (int j = 0; j < vertex + 1; j++) {
                graph.add(new ArrayList<>());
            }

            // 각각의 정점을 연결
            for (int j = 0; j < edge; j++) {
                st = new StringTokenizer(br.readLine().trim());

                int fromVertex = Integer.parseInt(st.nextToken());
                int toVertex = Integer.parseInt(st.nextToken());

                graph.get(fromVertex).add(toVertex);
                graph.get(toVertex).add(fromVertex);
            }
            // 그래프의 크기만큼 순회를 하면서
            // 방문하지 않은 곳을 기준으로 dfs 실행
            // dfs를 빠져나왔다면 무리 한 개를 전체 순회 한 것이므로
            // 무리의 개수 증가
            for (int j = 1; j < graph.size(); j++) {
                if (!visited[j]) {
                    dfs(j);
                    count++;
                }
            }
            System.out.println("#" + i + " " + count);
        }
    }
    public static void dfs(int node) {
        // dfs 시작하면 해당 노드는 방문 처리
        visited[node] = true;

        // 해당 노드를 기준으로 그래프의 값을 순회하면서,
        // 관계가 있는 사람들만 순회하면서
        // 방문 이력 체크 후 방문 하지 않았다면
        // dfs 실행
        for (int i = 0; i < graph.get(node).size(); i++) {
            int newNode = graph.get(node).get(i);

            if (!visited[newNode]) {
                visited[newNode] = true;
                dfs(newNode);
            }
        }
    }
}
