package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_정다운 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        
        // N
        int N = Integer.parseInt(br.readLine());
        StringTokenizer nToken = new StringTokenizer(br.readLine());
        int[] nArr = new int[N];
        for (int i=0; i<N; i++) {
            nArr[i] = Integer.parseInt(nToken.nextToken());
        }
        
        // M
        int M = Integer.parseInt(br.readLine());
        StringTokenizer mToken = new StringTokenizer(br.readLine());
        int[] mArr = new int[M];
        for (int i=0; i<M; i++) {
            mArr[i] = Integer.parseInt(mToken.nextToken());
        }
        
        // 이진탐색을 위한 정렬
        Arrays.sort(nArr);
        
        // Arrays.binarySearch 메소드 사용해서 이진탐색ㄱㄱ
        for (int i=0; i<M; i++) {
            int idx = Arrays.binarySearch(nArr, mArr[i]);
            if (idx < 0) {
                sb.append(0+"\n");
            } else {
                sb.append(1+"\n");
            }
        }
        
        System.out.println(sb);
    }
}
