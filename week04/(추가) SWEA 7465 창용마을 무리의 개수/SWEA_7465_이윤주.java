import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int num;
    List<Node> connect;

    Node() {
    };

    Node(int num) {
        this.num = num;
        this.connect = new ArrayList<>();
    }

}

public class SWEA_7465_이윤주 {
    /*
     * Q. 창용 마을 무리의 개수
     * 
     * #문제요약 1~N번까지 사람번호 서로 알고 있는 관계 or 몇 사람 거쳐서 알 수 있는 관계 -> 묶어서 무리 무리의 개수를 구하기
     * 
     * #풀이시간 : 5 25 
     * #메모리/시간 : 
     * #메인 접근법 
     *  1. LinkedList 구현 
     *  2. DFS
     *
     * #런타임 에러 남...
     */

    // 사람 배열
    static Node[] person;
    // 사람 수 
    static int N;
    // 방문 확인
    static boolean[] visited;
    // 무리 개수
    static int group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트케이스
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 사람 수 N, 관계 수 M
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            //사람번호 1~N까지 - 0번 더미
            person = new Node[N + 1];
            visited = new boolean[N + 1];

            // M개의 연결 관계 입력
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                person[a] = new Node(a);
                person[b] = new Node(b);
                person[a].connect.add(person[b]);
                person[b].connect.add(person[a]);
                

            }

            // 무리 개수
            group = 0;

            dfs(1);
            
            System.out.printf("#%d %d\n", tc, group);
        } // 테스트케이스 끝

    }

    public static void dfs(int idx) {
//        // base case
//        if(visited[idx] || person[idx].connect == null) {
//            group++;
//            return;
//        }
//        if(idx == N + 1) {
//            return;
//        }    

        // recursive case
        for(int i = idx; i <= N; i++) {
            if(visited[i]) {
//                group++;
                continue;
            }
            
            visited[i] = true;
            
            for(Node n : person[i].connect) {
                if(!visited[n.num]) { //연결된 노드 중 방문하지 않은 곳이면 이동
                    dfs(n.num);
                    group++;
                }
            }
        }
    }
}