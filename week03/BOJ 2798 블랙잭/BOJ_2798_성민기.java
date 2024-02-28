import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2798_성민기 {

	/* 풀이시간 : 240220 기준 28일전이라 소요 시간은 모릅니다.
	 * 메인 접근법 :3개의 경우의 수를 모두 접근하여 합을 구하면서 동시에
	 *           M을 넘지 않는 조건을 만족하도록함
	 *           3중 반복문을 사용하였고, 모두 같은 값을 가리킬때는 continue
	 * 막힌 부분 : 주석 처리된 부분처럼 max값을 구하면서도 M에 가장 가까운 경우를 구하는
	 * 			방법에 대한 생각이 나질 않았던 부분
	 * 해결 방법 : if(max < sum && sum <= M) 동시 만족하는 조건으로 해결
	 */
	
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int[] JACK = new int[N];
        for(int i=0; i<N; i++) {
        	int mm = sc.nextInt();
        	JACK[i] = mm;
        }
   
        int result = 0;
        int max = Integer.MIN_VALUE;
        
        for(int j=0; j<N; j++) {
        	for(int k=0; k<N; k++) {
        		for(int r=0; r<N; r++) {
        			if(j==k || k==r || r==j) {
        				continue;
        			} else {
        				int sum = JACK[j] + JACK[k] + JACK[r];
        				if(max < sum && sum <= M) {
        					max = sum;
        				}
//        				if(M < sum) {
//        					break;
//        				} else if (M >= sum) {
//        					result = sum;
//        					if (max < sum) {
//        						max = result;
//        					}
//        				}
        			}
        		}
        	}
        }
        System.out.println(max);
    }
}

