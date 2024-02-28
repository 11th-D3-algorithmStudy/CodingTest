package _0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1959_김민호 {
    // 두 개의 배열을 받은 후
    // 한 개의 배열을 한 칸 씩 이동하면서
    // 곱한 값의 최대값 재할당
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine().trim());
        int[] aArr;
        int[] bArr;
        int sum;
        int max;
        int maxNM;
        int minNM;
        boolean lagerNM;

        for (int i = 1; i < testNum + 1; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            maxNM = 0;
            minNM = 0;
            sum = 0;
            max = Integer.MIN_VALUE;
            lagerNM = true;

            aArr = new int[n];
            bArr = new int[m];

            // a 배열 값 입력
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                aArr[j] = Integer.parseInt(st.nextToken());
            }

            // b 배열 값 입력
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                bArr[j] = Integer.parseInt(st.nextToken());
            }

            // 두 배열의 길이 비교
            if (n > m) {
                maxNM = n;
                minNM = m;

            } else {
                maxNM = m;
                minNM = n;
                lagerNM = false;
            }

            // 배열 두 개 중 긴 것을 기준으로
            // 작은 배열을 한 칸씩 이동하면서
            // 각각의 값을 곱해줌
            for (int j = 0; j <= maxNM - minNM; j++) {
                for (int k = j; k < j + minNM; k++) {
                    if (lagerNM) {
                        sum += aArr[k] * bArr[k - j];
                    } else {
                        sum += aArr[k - j] * bArr[k];
                    }
                }
                // 최대값 재할당
                if (sum > max) {
                    max = sum;
                }
                sum = 0;
            }
            System.out.println("#" + i + " " + max);
        }
    }
}