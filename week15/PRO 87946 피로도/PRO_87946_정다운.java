public class PRO_87946_정다운 {
    static int[][] dgArr;
    static boolean[] visit;
    static int max = 0;

    public int solution(int k, int[][] dungeons) {
        dgArr = dungeons;
        visit = new boolean[dungeons.length];

        dfs(0, k);

        int answer = max;
        return answer;
    }

    // num : 방문한 던전 개수, k : 피로도
    static void dfs(int num, int k) {
        // 리턴조건 ,, 어케줌
        // 끝까지 갔다고 리턴하면 안되고
        // 뒤쪽 던전 먼저 갔다가 앞에도 방문 가능한 던전이 있다면 방문해야함,,!!!
        // -> 던전 방문은 for문 돌면서 ㄱㄱ

        // 리턴 안해!

        for (int i=0; i<dgArr.length; i++) {
            // 방문한적 없고 입장 가능할때만 ㄱㄱ
            if (!visit[i] && dgArr[i][0] <= k) {
                visit[i] = true;
                // 피로도 소모
                dfs(num+1, k-dgArr[i][1]);
                // 방문처리 원복
                visit[i] = false;
            }
        }
        // 소모 피로도가 현재 피로도보다 낮은지 확인하지 않음!
        // 다음 dfs로 넘어가는 k가 음수가 될 수 있으나....
        // 어차피 음수로 넘어가면 if문 조건에 걸려서 for문 돌지않고
        // 지금까지 방문한 던전 수(num)로 max값 업데이트 함

        max = Math.max(max, num);
    }
}
