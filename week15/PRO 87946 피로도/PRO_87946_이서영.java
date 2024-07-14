package algorithm;


class PRO_87946_이서영 {
	static boolean[] visited;
    static int max = -1;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        for (int i = 0; i < dungeons.length; i++){
            permutation(dungeons, k, dungeons.length, 0);
        }
        System.out.println(max);
        return max;
    }
    
    public void permutation(int[][] arr, int curr, int len, int depth){
        max = Math.max(max, depth);
        for(int i = 0; i < len; i++){
            if (!visited[i]){
                visited[i] = true;
                //System.out.println(Arrays.toString(visited));
                int required = arr[i][0];
                int consume = arr[i][1];
                // System.out.println("time: " + i + " curr: " + curr + " depth: " + depth);
                if (curr >= required){
                    permutation(arr, curr - consume, len, depth+1);
                }
                visited[i] = false;
            }
        }
    }
}
