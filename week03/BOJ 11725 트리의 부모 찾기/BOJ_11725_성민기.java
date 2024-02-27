import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11725_성민기 {
    static ArrayList<Integer>[] tree;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        parents = new int[N + 1]; // 해당 노드의 부모값 모음 배열
        visited = new boolean[N + 1]; // 방문 확인

        // 트리 구성
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 트리의 값 a, b를 서로 연결하는 작업        
            tree[a].add(b);
            tree[b].add(a);
        }

        // 루트 노드부터 DFS 수행하여 부모 노드 기록
        dfs(1);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int node) {
        // 방문 처리
    	visited[node] = true;
    	
    	// 방문한 노드에 인접한 노드 찾기
        for (int child : tree[node]) {
            // 인접한 노드가 방문한 적이 없다면 DFS 수행
        	if (!visited[child]) {
        		//노드의 부모가 뭔지 배열에 값 넣어준다.
                parents[child] = node;
                dfs(child);
            }
        }
    }
}
