import java.io.*;
import java.util.*;

public class SOFT_6279_이윤주 {
    //부품을 집을 수 있는 로봇의 최대 수 구하기
    //라인 길이 N, 부품 집을 수 있는 거리 K
    //로봇 찾아서, 근처부터 확인해서 거리 내에 있는 부품 리스트에 넣기
    //P:로봇, H:부품
    //좌우 탐색(left~right), 방문확인해서 안간 곳 체크 하기
    //방문체크된 것의 개수를 세면 최대 로봇 수!!
    //너무 복잡하게 생각하고 있었다...
    
    static int N, K;
    static String line;
    static boolean[] visited;
    static int count;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        line = br.readLine();
        visited = new boolean[N];
        count = 0;

        for(int i = 0; i < N; i++){
            //로봇 찾으면 2방 탐색
            if(line.charAt(i) == 'P'){
                int left = i - K;
                int right = i + K;

                //범위 넘어가는 경우 수정
                if(left < 0)
                    left = 0;
                if(right >= N)
                    right = N - 1;

                for(int j = left; j <= right; j++){
                    if(!visited[j] && line.charAt(j) == 'H'){
                        //방문하지 않은 부품인 경우 방문표시 후 다음으로
                        visited[j] = true;
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++){
            //방문표시된거 개수 세기
            if(visited[i])
                count++;
        }

        System.out.println(count);

    }

}
