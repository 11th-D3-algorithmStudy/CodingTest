package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_이윤주2 {
    static int N, answer;
    static int[][] table;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new int[2][N+1]; //1~N까지 사용
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            table[0][i] = Integer.parseInt(st.nextToken());
            table[1][i] = Integer.parseInt(st.nextToken());
        }

        findMax(1, 0);

        System.out.println(answer);
    }

    public static void findMax(int idx, int price){
        //기저조건
        if(idx > N){
            answer = Math.max(answer, price);
            return;
        }

        //현재 idx에서 할 일
        if(idx + table[0][idx] <= N + 1){
        //현재 상담 하기
            findMax(idx+table[0][idx], price + table[1][idx]);
        }
        else {
            //퇴사해서 못하는 경우
            findMax(idx+table[0][idx], price);
        }
            //상담 안하기
            findMax(idx+1, price);
    }
}
