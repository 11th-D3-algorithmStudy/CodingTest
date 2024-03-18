import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2606_김민호 {
        // 1번 컴퓨터를 기점으로
        // 서로 연결되어 있는 개수를 파악하면 되므로
        // 그래프를 만들어서 dfs를 하면서
        // 새로운 노드를 방문할 때마다 count를 올려준다
        static List<List<Integer>> graph = new ArrayList<>();
        static boolean[] visited;
        static int count;
        public static void main(String[] args) throws IOException {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            int vertex = Integer.parseInt(br.readLine().trim());
            int edge = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < vertex + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < edge; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int fromVertex = Integer.parseInt(st.nextToken());
                int toVertex = Integer.parseInt(st.nextToken());

                graph.get(fromVertex).add(toVertex);
                graph.get(toVertex).add(fromVertex);
            }

            // 그래프 형태로 입력값 받기


            // 그래프 내부 정렬
            for (int i = 0; i < graph.size(); i++) {
                Collections.sort(graph.get(i));
            }
            visited = new boolean[vertex + 1];
            int start = 1;

            // 시작점을 1로 dfs 시작
            dfs(start);
            System.out.println(count);
        }

        public static void dfs(int node) {
            // dfs가 실행되면 방문 처리
            visited[node] = true;

            // 해당 노드 기준으로 노드에 연결된 노드들
            // 반복문 돌면서 방문 안했으면 dfs 실행 및 count 증가
            for (int i = 0; i < graph.get(node).size(); i++) {
                int newNode = graph.get(node).get(i);
                if (!visited[newNode]) {
                    visited[newNode] = true;
                    count++;
                    dfs(newNode);
                }
            }
        }
    }