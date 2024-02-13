import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399_김민호 {
    // 문제를 읽어보니 오름차순해서 예시 같이 더해주면 될 것 같다 생각
    // 처음에는 0번 인덱스 값을 뒤에 다 더해주고, 그 다음  1번 인덱스 값을 뒤에 다 더해주고
    // 하면 될 줄 알고 했는데 값이 크게 나와서 답이 안나왔는데
    // 생각해보니 뒤 인덱스에 앞에꺼만 더해주면 되는 거였다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine().trim());
        int[] waitTime = new int[n];
        int timeSum = 0;
        st = new StringTokenizer(br.readLine().trim());

        // 대기 하는 시간을 배열로 받아줌
        for (int i = 0; i < n; i++) {
            waitTime[i] = Integer.parseInt(st.nextToken());
        }
        // 오름차순으로 소팅
        Arrays.sort(waitTime);

        // 뒤에 인덱스의 값에 앞의 인덱스의 값을 더해주면서
        // timeSum의 값을 하나씩 더해간다.
        for (int i = 0; i < n - 1; i++) {
            waitTime[i + 1] += waitTime[i];
            timeSum += waitTime[i];
        }

        // 마지막 값은 위의 반복문에서 인덱스 오버로 인해서
        // 따로 더해준다.
        timeSum += waitTime[n - 1];

        // 처음에 정답 제출하니까 99% 에서 실패하길래 뭔가 했는데
        // 배열의 길이가 1일 때는 위의 반복문 자체가 돌지 않아서
        // 배열의 길이가 1일 때만 따로 처리 해줬다.
        if (n == 1) {
            System.out.println(waitTime[0]);
        } else {
            System.out.println(timeSum);
        }
    }
}
