import java.util.*; //임포트 빼먹지 말기

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        //못풀겠어서 풀이 참고함
        //https://jisunshine.tistory.com/169
        //장르 종류 100개 미만
        Map<String, Integer> genreCnt = new HashMap<>();
        //장르별 노래 저장 : 장르이름, <고유번호, 재생횟수>
        Map<String, Map<Integer, Integer>> musicByGenre = new HashMap<>();

        for(int i = 0; i < genres.length; i++){
            //장르 별 노래 재생횟수 카운트, 장르별 노래 저장
            if(!genreCnt.containsKey(genres[i])){ //처음 나오는 장르인 경우
                Map<Integer, Integer> tmp = new HashMap<>();
                tmp.put(i, plays[i]);
                musicByGenre.put(genres[i], tmp);
                genreCnt.put(genres[i], plays[i]);
            } else { //나왔던 장르인 경우
                musicByGenre.get(genres[i]).put(i, plays[i]);
                genreCnt.put(genres[i], genreCnt.get(genres[i]) + plays[i]);
            }
        }

        //장르별 재생 횟수 많은 것 내림차순 정렬
        List<String> keySet = new ArrayList(genreCnt.keySet());
        Collections.sort(keySet, (o1, o2) -> (genreCnt.get(o2) - genreCnt.get(o1)));

        //정답을 담을 리스트, 정답: 고유번호 배열
        List<Integer> answer = new ArrayList<>();

        for(String key : keySet){
            //장르별 노래 중에 재생횟수 많은 것 내림차순 정렬
            Map<Integer, Integer> map = musicByGenre.get(key);
            List<Integer> genreKeySet = new ArrayList(map.keySet()); //고유번호 키셋
            Collections.sort(genreKeySet, (o1, o2) -> (map.get(o2) - map.get(o1)));

            answer.add(genreKeySet.get(0));
            if(genreKeySet.size() > 1){ //장르에 노래 2곡 이상이면 2곡 선택
                answer.add(genreKeySet.get(1));
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}