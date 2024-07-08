import java.util.*;

class PRO_17680_이윤주 {
    public int solution(int cacheSize, String[] cities) {
        int HIT = 1;
        int MISS = 5;

        if(cacheSize == 0){
            return cities.length * MISS;
        }

        //queue 이용
        for(int i = 0; i < cities.length; i++){
            cities[i] = cities[i].toLowerCase();
        } //대소문자 구분 없으니까 일단 모두 소문자로 바꿈

        Queue<String> queue = new ArrayDeque<>();

        int time = 0; //실행시간


        for(int i = 0; i < cities.length; i++){
            if(queue.isEmpty()){
                //아직 아무것도 안들어있는 경우 miss
                time += MISS;
                queue.offer(cities[i]);
            } else if(queue.contains(cities[i])) {
                //큐(캐시)에 들어있는 경우
                time += HIT;
                queue.remove(cities[i]);
                queue.offer(cities[i]); //다시 큐의 맨 뒤에 넣기
            } else {
                //큐에 안 들어있는 경우
                time += MISS;
                if(queue.size() < cacheSize){
                    //캐시에 자리 있는 경우
                    queue.offer(cities[i]);
                }else {
                    //캐시에 자리 없는 경우
                    queue.poll();
                    queue.offer(cities[i]);
                }
            }
        }

        return time;
    }
}