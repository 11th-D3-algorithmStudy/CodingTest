import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 우선순위큐
        //1. 담겨있는 상자 개수 순
        //2. 크기순
        PriorityQueue<Set> pq = new PriorityQueue<>();

        pq.offer(new Set(1, arr[0]));
        for (int i = 1; i < arr.length; i++) {
            //우선순위큐를 순회
                List<Set> temp = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                Set now = pq.peek();
                //담을 수 있는 크기가 있다면 담고 반복문 빠져나옴
                if (arr[i] > now.max) {
                    pq.offer(new Set(now.cnt + 1, arr[i]));
                    break;
                }
                temp.add(pq.poll()); // 임시 리스트에 저장
                if(temp.size()==i){
                    pq.offer(new Set(1, arr[i])); // 우선순위큐를 다 돌았는데도 담을 상자가 없으면 추가해줌

                }
            }
            //우선순위큐를 다 돌거나 담을 상자를 찾은경우 다시 우선순위큐로 넣어줌
            pq.addAll(temp);

        }
        System.out.println(pq.poll().cnt);

    }

    static class Set implements Comparable<Set> {
        int cnt; //담긴 상자 수
        int max; //제일 큰 상자 크기

        public Set(int cnt, int max) {
            this.cnt = cnt;
            this.max = max;
        }


        @Override
        public int compareTo(Set o) {
            if (this.cnt == o.cnt) {
                return o.max - this.max; //담긴상자수가 같다면 제일 큰 상자로 내림차순
            }
            //담긴 상자 수로 내림차순
            return o.cnt - this.cnt;
        }
    }
}
