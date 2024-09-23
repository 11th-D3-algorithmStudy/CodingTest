import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] adjlist;
    static boolean[] visit;
    static int[] scores;
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        scores = new int[n+1]; //점수 저장

        //인접리스트 초기화
        adjlist = new List[n+1];
        for(int i = 1; i<n+1;i++){
            adjlist[i] = new LinkedList<>();
        }
        //입력값이 -1이 올때까지 입력받음
        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1) break;
            adjlist[a].add(b);
            adjlist[b].add(a);
        }


        //최소 점수
        int min = 51;
        for(int i = 1; i<n+1;i++){
            visit = new boolean[n+1];
            visit[i]= true;
            dfs(i);
            scores[i] = score;
            min = Math.min(score,min);
        }

        //우선순위큐 1.점수 2.사람 번호
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1,arr2) -> {
            if(arr1[1]==arr2[1]){
                return arr1[0]-arr2[0];
            }
            return arr1[1] - arr2[1];
        });
        for(int i = 1; i<n+1;i++){
            pq.add(new int[]{i,scores[i]});
        }

        //정답 출력
        List<Integer> list= new LinkedList<>();
        while(!pq.isEmpty() && pq.peek()[1] ==min){
            list.add(pq.peek()[0]); // min 인 회원 번호 담음
            pq.poll();
        }

        System.out.println(min+" "+list.size());

        for(int i = 0; i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
    }

    static void dfs(int start){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start,0});
        visit[start] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int now = cur[0];
            int nowDep = cur[1];
            for(int i=0; i<adjlist[now].size(); i++){
                //start의 최종 score 업데이트
                score = nowDep;
                int next = adjlist[now].get(i);
                if(visit[next]) continue;
                visit[next] = true;
                queue.add(new int[]{next,nowDep+1});
            }

        }
    }

}
