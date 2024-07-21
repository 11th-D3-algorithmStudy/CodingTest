import java.util.*;
import java.io.*;

//참고 : https://velog.io/@richsubin/%EB%B0%B1%EC%A4%80-11722%EB%B2%88-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%EA%B0%90%EC%86%8C%ED%95%98%EB%8A%94-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4-JAVA
public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int result = 0;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] D = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
            D[i] = 1;  // 자기 자신. 없으면 1이기에 1로 초기화
        }

        for(int i=0;i<N;i++) { //기준값
            for(int j=0;j<i;j++) { //이전값들
                if(A[j] > A[i]) {
                    D[i] = Math.max(D[i], D[j]+1);
                }
            }
            result = Math.max(result, D[i]);
        }

        bw.write(result+"");
        bw.flush();
        bw.close();
    }
}
