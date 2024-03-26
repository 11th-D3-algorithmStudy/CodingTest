package Study;

import java.io.*;
import java.util.*;

public class SOFT_6250_이윤주 {
    static int N;
    static int[][] res, rank, count; 
    static int[] total, totRank, totCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        res = new int[3][N];
        // rank = new int[3][N];
        count = new int[3][1001]; //대회 3개 각각 점수 개수 카운트
        total = new int[N];
        totRank = new int[N];
        totCount = new int[3001]; //총점 점수 개수 카운트
        
        for(int i = 0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                res[i][j] = Integer.parseInt(st.nextToken());
                total[j] += res[i][j];
                count[i][res[i][j]]++;
            }
        }//입력 완료
        for(int i = 0; i < N; i++){
            totCount[total[i]]++;
        }
        StringBuilder sb = new StringBuilder();
        //각 대회 등수 구하기
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < N; j++){
                int score = res[i][j];
                int totScore = total[j];
                int upper = 1;
                int totUpper = 1;
                for(int k = score + 1; k <= 1000; k++){//나보다 점수가 큰 사람의 수 세기
                     upper = upper + count[i][k];       
                }
                for(int k = totScore + 1; k <= 3000; k++){
                    totUpper = totUpper + totCount[k];
                }
                // rank[i][j] = upper;
                sb.append(upper + " ");
                totRank[j] = totUpper;
            }
            sb.append("\n");
        }
        for(int i : totRank){
            sb.append(i + " ");
        }
        System.out.println(sb);        
    }
}
