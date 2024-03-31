package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SOFT_6250_성민기 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] score = new int[N][3];
        int[][] rank = new int[N][3];
        // 점수합 배열, 최종 랭크 배열
        int[] sumScore = new int[3];
        List<Integer> sortedScore = new ArrayList<>();
        int[] finalRank = new int[3];
        

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            int rowSum = 0;
        	for(int j=0; j<3; j++){
                rowSum += score[i][j];
                
            }
        	sumScore[i] = rowSum;
        	sortedScore.add(rowSum);
        }

        for(int i=0; i<N; i++){
            List<Integer> sorted = new ArrayList<>();
            for(int j=0; j<3; j++){
                sorted.add(score[i][j]);
            }
            Collections.sort(sorted);
            Collections.reverse(sorted);

            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    if(score[i][j] == sorted.get(k)){
                        // 순위 체크
                        rank[i][j] = k+1;
                    }
                }
            }
        }
        Collections.sort(sortedScore);
        Collections.reverse(sortedScore);
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(sumScore[i] == sortedScore.get(j)) finalRank[i] = j+1;
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<3; j++){
                System.out.print(rank[i][j] + " ");
            }
            System.out.println();
        }

        for(int i=0; i<3; i++){
            System.out.print(finalRank[i] + " ");
        }
    }
}