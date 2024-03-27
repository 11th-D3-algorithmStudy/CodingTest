import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14888_김민호 {
    // 백트래킹으로 해야할 것 같다고 생각해서
    // 숫자 배열과 기호 배열을 한 배열로 만들고
    // 그 배열을 기반으로 계산을 하려고 했으나..
    // 하다보니 너무 복잡해지고, 산으로 가는 것 같아서
    // 인터넷을 참고해보니 새로운 배열을 만들 필요가 없었다..
    // 좀 더 공부가 필요할 것 같다..
    static int n;
    static int[] iArr;
    static int[] calArr;
    static int max;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());

        iArr = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            iArr[i] = Integer.parseInt(st.nextToken());
        }

        calArr = new int[4];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 4; i++) {
            calArr[i] = Integer.parseInt(st.nextToken());
        }
        // 입력값 받기..

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        int count = 0;
        sol(iArr[count], count);
        System.out.println(max);
        System.out.println(min);
    }

    public static void sol(int num, int count) {
        // 숫자 배열의 끝까지 오게되면 return
        if (count == n - 1) {

            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
            return;
        }

        // 기호 배열의 크기가 4 이므로
        // for문을 4 기준으로 돌리다가
        // 배열의 값이 1이상이면 해당 기호의 처리를 해주고
        // 재귀 호출
        // 재귀가 끝나면 해당 배열의 숫자 다시 원복
        for (int i = 0; i < 4; i++) {
            if (calArr[i] > 0) {
                calArr[i]--;
                if (i == 0) {
                    sol(num + iArr[count + 1], count + 1);
                } else if (i == 1) {
                    sol(num - iArr[count + 1], count + 1);
                } else if (i == 2) {
                    sol(num * iArr[count + 1], count + 1);
                } else {
                    sol(num / iArr[count + 1], count + 1);
                }
                calArr[i]++;
            }
        }
    }
}