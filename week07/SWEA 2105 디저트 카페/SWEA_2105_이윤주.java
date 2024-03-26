package Study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_이윤주 {
    //대각선 이동, 사각형을 그리며 출발한 카페로 돌아가야함
    //같은 숫자 또 밟으면 X
    //한 카페만 X
    //왔던 길 되돌아가기 X
    //가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수 출력
    //디저트 못 먹는 경우 -1 출력
    
    //1. 양옆, 밑에 2칸 있어야 사각형 만들 수 있음
	//2. 이동할 때 방금 왔던 방향이랑 같은 방향 or 다음 방향으로만 갈 수 있음
	
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int max;
    static boolean[] dessert; //이미 나왔던 숫자인지 확인
    
    //대각선 이동 델타배열 : 좌하, 우하, 우상, 좌상 순서!!
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {-1, 1, 1, -1};
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            
            for(int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            max = -1; //디폴트 -1
            
            //양 옆과 밑에 2칸이 있어야 사각형 가능
            for(int r = 0; r < N - 2; r++) {
            	for(int c = 1; c < N - 1; c++ ) {
            		visited = new boolean[N][N];
            		dessert = new boolean[101]; //1~100
            		visited[r][c] = true;
            		dessert[map[r][c]] = true;
            		dfs(r,c,r,c,0,1);
            		
            	}
            }
            
            System.out.printf("#%d %d\n", tc, max);
        }
    }

    //r,c:현재위치 sr,sc:초기위치 dir:이동방향 cnt:디저트개수
	private static void dfs(int r, int c, int sr, int sc, int dir, int cnt) {
		//이동할 때 방금 왔던 방향이랑 같은 방향 or 다음 방향으로만 갈 수 있음
		for(int d = dir; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			//범위 밖으로 나갈때
			if(nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			//초기 위치로 돌아왔을때! - 조건 만족
			if(nr == sr && nc == sc && cnt >= 4) {
				max = Math.max(max, cnt);
				return;
			}
			//안가본 곳이면서, 중복 아닌 곳으로 이동
			if(!visited[nr][nc] && !dessert[map[nr][nc]]) {
				visited[nr][nc] = true;
				dessert[map[nr][nc]] = true;
				dfs(nr, nc, sr, sc, d, cnt + 1);
				visited[nr][nc] = false;
				dessert[map[nr][nc]] = false;
			}
		}
	
	}
}