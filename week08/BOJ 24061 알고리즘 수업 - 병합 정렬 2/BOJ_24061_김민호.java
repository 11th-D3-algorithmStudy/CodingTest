import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24061_김민호 {
    // 시험 공부를 위해서 인터넷에서
    // 병합 정렬 관련 코드를 찾았고..
    // 문제를 풀기 위해서 count 와 flag 를 추가해서
    // 적절한 답을 찾음...

    static int n, k;
    static int[] arr;
    static int count;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(arr, 0, arr.length - 1);
        if (!flag) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(arr[i] + " ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    public static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            if (!flag) {
                merge(arr, p, q, r);
            } else {
                return;
            }
        }
    }

    public static void merge(int[] arr, int p, int q, int r) {
        int i = p, j = q + 1, t = 0;
        int[] tmp = new int[r - p + 1];

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            }

            else {
                tmp[t++] = arr[j++];
            }
        }

        while (i <= q) {
            tmp[t++] = arr[i++];
        }
        while (j <= r) {
            tmp[t++] = arr[j++];
        }

        for (int l = 0; l < tmp.length; l++) {
            arr[p + l] = tmp[l];
            count++;

            if (count == k) {
                flag = true;
                break;
            }
        }
    }
}