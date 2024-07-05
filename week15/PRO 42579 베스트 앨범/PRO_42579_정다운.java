import java.util.*;

public class PRO_42579_정다운 {
    public int[] solution(String[] genres, int[] plays) {
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
                s.totPlays += play;
                s.pq.add(new Song(i, play));
            }
        }

        Songs[] allSongs = map.values().toArray(new Songs[map.size()]);

        Arrays.sort(allSongs);

        List<Song> res = new ArrayList<>();
        for (int i = 0; i < allSongs.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (!allSongs[i].pq.isEmpty()) {
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

    static class Songs implements Comparable<Songs> {
        String genre;
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

    static class Song implements Comparable<Song> {

        int idx;
        int plays;

        public Song(int idx, int plays) {
            this.idx = idx;
            this.plays = plays;
        }

        @Override
        public int compareTo(Song o) {
            return o.plays - this.plays;
        }
    }
}
