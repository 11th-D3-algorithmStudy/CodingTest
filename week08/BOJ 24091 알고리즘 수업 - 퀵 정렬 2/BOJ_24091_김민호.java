import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24091_김민호 {
    // 병합정렬과 같이 시험공부를 위해
    // 인터넷에서 검색해서 코드를 공부하고
    // 문제를 풀기 위해서 count와 flag를 추가했는데..
    // 매커니즘 이해를 정확히 하지 못 해서 문제를 풀지는 못했음..
    static int n, k;
    static boolean flag;
    static int[] arr;
    static int count;
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

        quickSort(0, arr.length - 1);
        if (flag) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(arr[i] + " ");
            }
            System.out.println(sb.toString().trim());
        } else {
            System.out.println("-1");
        }
    }
    public static void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            if (!flag) {
                quickSort(low, pivotIndex - 1);
                quickSort(pivotIndex + 1, high);
            } else {
                return;
            }
        }
    }
    private static int partition(int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                if (flag) {
                    return -1;
                }
            }
        }
        swap(arr, i + 1, high);
        if (flag) {
            return -1;
        }
        return i + 1;
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        count++;
        if (count == k) {
            flag = true;
            return;
        }
    }
}