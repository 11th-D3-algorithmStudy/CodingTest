import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11725_김민호 {
    // 트리의 부모를 찾기 위해서
    // 무방향 그래프를 만들고,
    // 해당 그래프에서 시작을 1로 설정해서
    // 루트가 1인 트리와 같은 구조로 순회할 수 있도록 코드를 짰다.

    // 그래프 선언
    static List<List<Integer>> graph = new ArrayList<>();
    // 해당 인덱스의 자신의 부모를 저장할 수 있는 배열 선언
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int vertex = Integer.parseInt(br.readLine().trim());

        // 숫자가 1부터 있으므로 정점의 개수에 1 더해서 배열 초기화
        parent = new int[vertex + 1];
        // 그래프도 위와 같은 이유로 정점의 개수보다 1 더해서 초기화
        for (int i = 0; i < vertex + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프의 무방향으로 노드 넣어주기
        for (int i = 0; i < vertex - 1; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int fromVertex = Integer.parseInt(st.nextToken());
            int toVertex = Integer.parseInt(st.nextToken());

            graph.get(fromVertex).add(toVertex);
            graph.get(toVertex).add(fromVertex);
        }

        // 루트가 1이고, 1의 부모는 없으므로 -1로 parent 값 넣어주기
        dfs(1, -1);
        for (int i = 2; i < parent.length; i++) {
            System.out.println(parent[i]);
        }
    }

    // dfs를 돌면서 해당 노드를 인덱스로 parent배열에 parentNode를 저장하고
    // 그래프 내부의 값을 순회하면서, parent 배열에 해당 node가 아직 할당 된 적이 없다면
    // 해당 값을 기준으로 기존 노드를 부모 노드로, 위의 해당 노드를 인덱스 노드로 넣어서
    // 새로운 dfs 함수 호출
    public static void dfs(int node, int parentNode) {
        parent[node] = parentNode;

        for (int i = 0; i < graph.get(node).size(); i++) {
            int newNode = graph.get(node).get(i);

            if (parent[newNode] == 0) {
                dfs(newNode, node);
            }
        }
    }
}