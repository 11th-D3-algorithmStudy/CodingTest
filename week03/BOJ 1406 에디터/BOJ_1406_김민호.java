import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1406_김민호 {
    // 총 3가지 방법으로 하다가 시간초과가 계속 나와서
    // 인터넷에서 검색해보니까 stack을 쓰면 된다는 키워드를 보고
    // 최종적으로 stack으로 교체해서 문제를 품

    // 1. String
    // String 자체를 charAt과 subString을 이용해서 명령 수행하기
    // 2. LinkedList
    // edit를 LinkedList를 사용해서 명령 수행하기
    // 3. StringBuilder
    // StringBuilder에 담고, insert, delete로 명령 수행하기 (제출 시, 55% 까지만 동작이 됨)
    // 3가지 중 StringBuilder가 가장 빠름
    // 4. Stack
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 커서의 왼쪽과 오른쪽을 기준으로 스택을 두 개 생성
        Stack<Character> leftCursor = new Stack<>();
        Stack<Character> rightCursor = new Stack<>();
        StringBuilder sb = new StringBuilder();

        String edit = br.readLine().trim();

        // 처음 수정을 할 문장 입력
        for (int i = 0; i < edit.length(); i++) {
            leftCursor.push(edit.charAt(i));
        }

        int editNum = Integer.parseInt(br.readLine().trim());

        // 수행 횟수 만큼 반복문 돌리고
        // 각 명령에 따라 수행
        for (int i = 0; i < editNum; i++) {
            String command = br.readLine().trim();
            // L 일 시 왼쪽으로 한 칸 이동이므로
            // 왼쪽에 있는 것을 오른쪽 스택으로 이동
            if (command.charAt(0) == 'L') {
                if (!leftCursor.isEmpty()) {
                    rightCursor.push(leftCursor.pop());
                }
                // D 일 시 오른쪽으로 한 칸 이동이므로
                // 오른쪽에 있는 것을 왼쪽 스택으로 이동
            } else if (command.charAt(0) == 'D') {
                if (!rightCursor.isEmpty()) {
                    leftCursor.push(rightCursor.pop());
                }
                // B 일 시 왼쪽 스택에서 한 개 빼주기
            } else if (command.charAt(0) == 'B') {
                if (!leftCursor.isEmpty()) {
                    leftCursor.pop();
                }
                // P 일 시 왼쪽 스택에 입력 받은 값 넣어주기
            } else if (command.charAt(0) == 'P') {
                leftCursor.add(command.charAt(2));
            }
        }
        // 명령 개수의 반복문이 완료되면, 왼쪽에 있는 모든 것을
        // 오른쪽 스택에 넣기
        while (!leftCursor.isEmpty()) {
            rightCursor.push(leftCursor.pop());
        }
        // 오른쪽 스택에 있는 것을 StringBuilder에 저장하기
        while (!rightCursor.isEmpty()) {
            sb.append(rightCursor.pop());
        }
        // 출력
        System.out.println(sb.toString());
    }
}