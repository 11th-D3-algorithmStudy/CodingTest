import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_이윤주_pass {
    /*
     * Q. 효율적인 해킹
     * 방향 그래프, dfs로 연결된 노드 개수 세기
     * max 갱신하면서 리스트도 갱신
     * A -> B를 신뢰한다 : 단방향 그래프
     * 연결 방향 수정함
     * 직접 간접적으로 가장 많이 신뢰받는 노드를 찾는 것!!
     * -> 시간초과 : BFS로 수정...
     * -> 
     * 
     */
    static int N, M, max;
    static List<Integer>[] adjList; //인접리스트
    static boolean[] visited; //방문체크
    static int[] cnt;
    //각 노드마다 해킹할 수 있는 컴퓨터의 개수를 저장하는 배열
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); //노드 개수
        M = Integer.parseInt(st.nextToken()); //간선 개수
        
        adjList = new ArrayList[N + 1]; //노드 번호 1부터 N 까지
        for(int i = 1; i < N + 1; i++) {
            adjList[i] = new ArrayList<>(); //초기화
        }
        
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            //A -> B를 신뢰한다 : 단방향 그래프
            adjList[A].add(B);
        }//신뢰관계 입력 끝
        
        cnt = new int[N + 1];
        
        for(int i = 1; i < N + 1; i++) {
        	visited = new boolean[N + 1];
            bfs(i);
        }
        
        StringBuilder sb = new StringBuilder();
        
        //최대값 구하기
        max = -1;
        for(int i = 1; i < N + 1; i++) {
        	if(cnt[i] > max) {
        		sb.setLength(0); //초기화
        		sb.append(i + " ");
        		max = cnt[i];
        	}
        	else if(cnt[i] == max) {
        		sb.append(i + " ");
        	}
//            max = Math.max(max, cnt[i]);
        }
        
//        //최댓값인 컴퓨터 출력 - 오름차순
//        for(int i = 1; i < N + 1; i++) {
//            if(cnt[i] == max) 
//                sb.append(i + " ");
//        }
        System.out.println(sb);
    }
    private static void bfs(int v) {
    	Queue<Integer> queue = new ArrayDeque<>();
		
    	visited[v] = true;
    	queue.offer(v);
    	
    	while(!queue.isEmpty()) {
    		int now = queue.poll();
    		
    		for(int next : adjList[now]) {
    			if(visited[next]) continue;
    			visited[next] = true;
    			cnt[next]++;
    			queue.offer(next);
    		}
    	}
	}

}