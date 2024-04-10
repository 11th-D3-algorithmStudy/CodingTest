package week09;

import java.io.*;
import java.util.*;

public class BOJ_1976_장현영 {
    // 가능여부 확인하기
    // bfs 진행
    // 도시의 수
    // 2차원 배열로 접근하니 25% 메모리초과발생(0,1 모두 담음)
    // 도움받은 내용 : arrListaylist로 접근후 1일 때만 담아주기
    // arrListaylist 2차원으로 접근하기
    // 1. 전체 크기 설정
    // 2. 각각은 가변크기로 진행
    
    // union-find로 푸는 방법도 존재
    static ArrayList<Integer>[] arrList;
    static int[] route;
    static int n,m;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         n = Integer.parseInt(br.readLine()); // 노드
         m = Integer.parseInt(br.readLine()); // 간선
         arrList = new ArrayList[n+1]; // 2차원으로 만들기 위해 우선 선정
         for(int i=1; i<=n; i++) {
             st = new StringTokenizer(br.readLine());
             arrList[i] = new ArrayList<>();
             for(int j=1; j<=n; j++) {
                 int temp = Integer.parseInt(st.nextToken());
                 if(temp == 1) {
                     arrList[i].add(j);
                 }
                 if(i==j) arrList[i].add(j);
             }
         }
        
         // route
         // 인접여부가 연결되어있는지 확인하면됨
         st = new StringTokenizer(br.readLine());
         int a = Integer.parseInt(st.nextToken());
         while(st.hasMoreElements()) {
             int b = Integer.parseInt(st.nextToken());
             if(!isConnect(a,b)) {
                 System.out.println("NO");
                 return;
             }
             a = b;
         }
         System.out.println("YES");
    }
    
    //start,finish
    public static boolean isConnect(int s, int f) {
        // 중간 루트가 있는지 확인하기
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited = new boolean[n+1];
        visited[s] = true;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(int i=0;i<arrList[node].size();i++) { // arrList가 가변사이즈라
                int nextNode = arrList[node].get(i); // node에 연결되어 있는 다음 노드
                if(nextNode == f) return true; // 처음 간 것이라면
                if(!visited[nextNode]) { // 한번도 안갔다면
                    queue.offer(nextNode);
                    visited[nextNode]=true;
                }
            }    
        }
        return false;
    }
}

