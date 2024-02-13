import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789_김민호 {
    // 문제 그림을 보니까 위에는 큐, 밑에는 스택으로 하면 될 것 같다는 생각을 함
    // 그 외는 구현을 하면서 상황에 맞게 분기를 하고
    // boolean으로 성공과 실패를 true, false로 나누어
    // Nice와 Sad가 나오게 구현했다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 대기줄 큐와, 임시줄 스택 선언 및 초기화
        Queue<Integer> waitLine = new LinkedList<>();
        Stack<Integer> tempLine = new Stack<>();

        // 스택과 큐에서 전부다 빠져나왔나 확인하기 위한
        // check 와 flag 선언 및 초기화
        int check = 1;
        boolean flag = true;
        int n = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        // 처음 줄을 큐에 넣어준다.
        for (int i = 0; i < n; i++) {
            waitLine.add(Integer.parseInt(st.nextToken()));
        }

        while (check <= n) {
            if (!waitLine.isEmpty() && waitLine.peek() == check) {
                // 대기줄의 번호가 검사하는 번호와 같으면
                // poll 후에 검사하는 번호 check ++
                waitLine.poll();
                check++;
            } else if (!tempLine.isEmpty() && tempLine.peek() == check) {
                // 대기줄에 없다면 임시줄에서 위를 반복
                tempLine.pop();
                check++;
            } else if (!tempLine.isEmpty() && !waitLine.isEmpty() && tempLine.peek() < waitLine.peek()) {
                // 위의 상황이 아닌데 임시줄의 번호가 대기줄의 번호보다 작다면
                // 스택은 추후에 먼저인 번호가 나갈 수 없으므로 실패 Sad
                flag = false;
                break;
            } else {
                // 위의 상황들이 아니라면 대기줄에서 임시줄로 이동
                tempLine.add(waitLine.poll());
            }
        }
        // flag를 통해 성공했다면 Nice 출력
        // 실패 했다면 Sad 출력
        if (flag) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
