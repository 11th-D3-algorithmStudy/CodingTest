package AlgorithmStudy;

import java.io.*;
import java.util.*;

public class SWEA_2117_성민기 {

	/* 풀이시간 : 240318 19:40 ~ 22:24
	 * 메인접근법
	 *     - 완전탐색으로 모든 경우 체크
	 *     - 마름모 형태로 체크하는 메소드를 활용
	 * 
	 * 막힌 부분
	 *     -> k의 범위
	 *        - 처음에는 k의 범위를 N-(N/2)까지인 줄 알았음
	 *        - 맵 전체를 덮을때까지의 k의 범위를 설정했어야 함을 깨달음
	 *        - N-1까지인줄 알았지만 N이 짝수일 때 정사각형을 포함할 수 있는 k는 N+1이라고함
	 *        - (k가 홀수일 땐 가장 작은 정사각형 N=k / k가 짝수일 땐 가장 작은 정사각형이 N=k-1)
	 *        - 따라서 k의 범위는 N+1까지
	 *     
	 *     -> 혼자 힘으로는 풀지 못했음
	 *        - k의 범위를 어떻게 해야할 지 문제 댓글의 링크를 참고함
	 *        - 완전탐색의 경우인지를 체크받음 (GPT)
	 *        - 더 놓친 부분은 코드 안 주석으로 작성하도록 하겠음
	 *  
	 * 메모리: 38,288 kb
	 * 실행시간: 314 ms
	 */
	
    static int[][] map;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	map = new int[N][N];
        	
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	int max = 0;
        	//완전탐색
        	// 배열의 크기만큼 반복
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			
        			// k의 반복
        			for(int k=1; k<=N+1; k++) {
       					int OpEx = k * k + (k-1) * (k-1);
       					int home = homeCount(r, c, k);
       					if((M*home - OpEx) >= 0) {
       						max = Math.max(max, home);
        				}
        			}
        		}
        	}
        	sb.append("#" + t + " " + max + "\n");
        }
        System.out.println(sb);
    }
    
    // 마름모 형태를 체크하면서 집인 경우를 체크
    public static int homeCount(int r, int c, int k) {
    	int left = c;
    	int right = c;
    	int count = 0;
    	
    	// 여기에서 r-k로만 하다가 틀렸었음.
    	// r-k+1로 해야 문제에서 주어진 칸으로 마름모를 그리며 반복함
    	// 이곳이 가장 크게 놓친 부분
    	for(int R=r-k+1; R<=r+k-1; R++) {
    		for(int C=left; C<=right; C++) {
    			if(R >= 0 && R < N && C >= 0 && C < N
    					&& map[R][C]==1) count++;
    		}
    		if(R<r) {
    			left--; right++;
    		} else {
    			left++; right--;
    		}
    	}
    	return count;
    }
}
