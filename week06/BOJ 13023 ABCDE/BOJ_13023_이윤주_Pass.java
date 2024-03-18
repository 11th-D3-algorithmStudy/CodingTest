

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023_이윤주_Pass {
    /*
     * Q. ABCDE
     * #문제요약
     *     사람의 수 N, 친구 관계의 수 M이 주어지고
     *     ABCDE 같은 친구관계가 존재하면 1 없으면 0을 출력한다.
     * #풀이시간 : 2시간 이상
     * #메모리/시간 : 16879kb/284ms
     * #메인접근법
     *     1. dfs로 탐색해서 level이 4가 되면 존재하는 것
     * => 자꾸 시간초과 났는데, ArrayList로 바꾸고, break 넣으니까 통과
     */
    
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static boolean possible;
    static int level;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        //0~N-1까지 초기화
        graph = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        
        
        //친구 관계 연결
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        
        possible = false;
        visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            level = 0;
            dfs(i);
            if(possible)
            	break;	//시간초과 나서 추가한부분
        }
        
        if(possible) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
        
    }
    
    //node : 현재 노드
    private static void dfs(int node) {
        //기저조건
        if(level == 4) { //ABCDE 찾으면 탈출
            possible = true;
            return;
        }
//        //리프노드
//        if(node != 0 && graph.get(node).size() == 1) {
//            return;
//        }
        
        //현재 노드에서 할 일
        visited[node] = true; //방문체크
                
        //재귀부분
        for(int i = 0; i < graph.get(node).size(); i++) {
            int newNode = graph.get(node).get(i);
            
            if(!visited[newNode]) {
                level++;
                dfs(newNode);
                level--;
            }
        }
        visited[node] = false; //방문체크 초기화
    }
}