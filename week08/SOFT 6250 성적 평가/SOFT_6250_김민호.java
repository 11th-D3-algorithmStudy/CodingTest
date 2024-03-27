import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SOFT_6250_김민호 {
    // 각 점수를 받아서 순위를 정하고
    // 각 점수를 다 더한 총합 점수 배열을 마지막에 순위를 정하는 것으로
    // 제출을 했을 때 시간초과가 나와서
    // 추후에 바이너리서치 메서드를 사용해서 제출 시도해보려고 했는데
    // 첫째로 바이너리서치를 하는데 값이 있는데도 자꾸 음수가 나와서 수정을 완료하지 못했고,
    // 둘째로 소프티어에 제출을 했는데 제출이 되지 않아서 정확한 확인을 못함..
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());

        int[] scores = new int[n];
        int[] sumScores = new int[n];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                // 입력값 받으면서 , 점수 총합 배열도 같이 구하기
                scores[j] = Integer.parseInt(st.nextToken());
                sumScores[j] += scores[j];
            }
            // 각 점수 별 순위 정하기
            getRank(scores);
        }
        // 총합 점수의 순위 정하기
        getRank(sumScores);
    }
    public static void getRank(int[] scores) {
       // 랭크 배열을 만들고, 1로 초기화 한후에
        int[] rank = new int[n];
        Arrays.fill(rank, 1);
        // 값들을 반복문으로 비교하면서
        // 본인의 값보다 큰 값이 있으면 ++ 해줌
        // 같은 값이면 ++을 안하니까 동순위도 구할 수 있음
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (scores[j] < scores[k]) {
                    rank[j]++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) {
            sb.append(rank[j] + " ");
        }
        System.out.println(sb.toString().trim());
    }
}