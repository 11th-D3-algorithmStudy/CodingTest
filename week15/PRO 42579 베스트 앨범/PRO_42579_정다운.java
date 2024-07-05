import java.util.*;

public class PRO_42579_정다운 {
    public static int[] solution(String[] genres, int[] plays) {
        // map 사용! key에 장르 저장, value에 Songs(해당 장르 총 재생횟수 & Song(각 노래 고유번호&재생 횟수) 저장
        int N = genres.length;

        HashMap<String, Songs> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String genre = genres[i];
            int play = plays[i];

            if (!map.containsKey(genre)) {
                PriorityQueue<Song> pq = new PriorityQueue<>();
                pq.add(new Song(i, play));
                map.put(genre, new Songs(play, pq));
            } else {
                Songs s = map.get(genre);
                s.totPlays += play; // 총 재생 횟수 +
                s.pq.add(new Song(i, play)); // pq에 곡 정보 추가
            }
        }

        Songs[] allSongs = map.values().toArray(new Songs[map.size()]);

        Arrays.sort(allSongs);

        List<Song> res = new ArrayList<>();
        for (int i = 0; i < allSongs.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (!allSongs[i].pq.isEmpty()) { // 한 장르에 노래가 2개 미만인 경우 처리
                    res.add(allSongs[i].pq.poll());
                }
            }
        }

        int[] answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i).idx;
        }

        return answer;

    }

    // 같은 장르의 노래끼리 묶어서 Songs로 보관
    // totPlays: 해당 장르의 총 재생 횟수
    // pq: 해당 장르의 노래(Song)들 보관/정렬용 pq
    static class Songs implements Comparable<Songs> {
        int totPlays;
        PriorityQueue<Song> pq;

        @Override
        public int compareTo(Songs o) {
            return o.totPlays - this.totPlays; // 내림차순
        }

        public Songs(int totPlays, PriorityQueue<Song> pq) {
            this.totPlays = totPlays;
            this.pq = pq;
        }
    }

    // 각 노래 고유번호, 재생 횟수 Song으로 저장
    static class Song implements Comparable<Song> {
        int idx;
        int plays;

        public Song(int idx, int plays) {
            this.idx = idx;
            this.plays = plays;
        }

        @Override
        public int compareTo(Song o) {
            return o.plays - this.plays; // 내림차순
        }
    }
}
