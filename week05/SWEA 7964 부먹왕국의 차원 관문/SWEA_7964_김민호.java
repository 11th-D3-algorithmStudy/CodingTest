package zz;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7964_김민호 {
    static int cnt;
    static int n;
    static int limit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());
        int[] cities;
        int count;

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());

            n = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());
            cities = new int[n];
            count = 0;

            st = new StringTokenizer(br.readLine().trim());

            for (int j = 0; j < n; j++) {
                cities[j] = Integer.parseInt(st.nextToken());
            }

            // 배열을 입력받은 후
            // 해당 값이 0 이면 count++
            for (int j = 0; j < n; j++) {
                if (cities[j] == 0) {
                    count++;
                }
                // 배열의 값이 1이고, count가 이동거리 제한 안이면
                // count 는 0으로 재할당
                if (cities[j] == 1 && count < limit) {
                    count = 0;
                }

                // count가 이동제한 거리랑 같으면
                // count 0 재할당
                // 추가 건물 필요 개수 cnt++
                if (count == limit) {
                    count = 0;
                    cnt++;
                }
            }
            System.out.println("#" + i + " " + cnt);
            cnt = 0;
        }
    }
}