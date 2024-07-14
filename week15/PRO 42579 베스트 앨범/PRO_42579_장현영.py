# 베스트 앨범 2시간 소요
# 사전 정렬 문법부분 힌트
# defaultdict, heapq으로 문제 품
# 노래는 고유 번호로 구분
# 장르, 노래, 고유번호 낮은 순으로 수록
# 순서 return 하기

from collections import defaultdict
import heapq

def solution(genres, plays):
    answer = []
    total_dict = defaultdict(int)

    seq_dict = defaultdict(list) # 장르끼리는 자동으로  순서으로 삽입
    for i in range(len(genres)):
        total_dict[genres[i]] += plays[i] # total
        # plays가 큰 순으로 정렬
        heapq.heappush(seq_dict[genres[i]], (-plays[i], i))
    
    # 장르 큰 순으로 집계
    # 사전 정렬
    sort_dict = {k: v for k, v in sorted(total_dict.items(),key=lambda x: -x[1])}
    print(seq_dict.items())
    for genre in sort_dict.keys():
        # 길이에 상관없이 최대 두번만 pop 진행
        cnt = min(len(seq_dict[genre]), 2) 
        for _ in range(cnt):
            # heappop해야함
            next = heapq.heappop(seq_dict[genre])[1]
            answer.append(next)

    return answer

genres = ["classic", "pop", "classic", "classic", "pop"]
plays = [500, 600, 150, 800, 2500]
print(solution(genres,plays))