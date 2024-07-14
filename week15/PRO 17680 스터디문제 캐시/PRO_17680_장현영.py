# 풀이시간 40분 소요 정답!

# 캐시크기와 도시이름 배열
# 캐시교체 알고리즘 lru 사용 least recently used
# hit : 1(유지), miss : 5(교체)
# 페이지 크기 지정
# deque 사용해서 선입 선출시키기
# lru이므로 최근에 사용한 것이 있다면 맨 뒤로 보내기!
# 대소문자 구분 안함! upper로 통일시키기
# 캐시가 다 안 채워졌을 때 또 같은 값이 들어올 경우, 그 값을 제일 최상단으로 옮기기


from collections import deque

def solution(cacheSize, cities):
    if cacheSize==0:
        return len(cities)*5
    q = deque(maxlen=cacheSize)
    cnt = 0
    curr_size = 0
    for city in cities:
        each = city.upper() # 대문자로 통일시키기
        # 아직 사이즈가 비어있을 때
        if cnt < cacheSize:
            if each not in q:
                q.append(each)
                curr_size += 5
                cnt+=1
            else:
                q.remove(each) # queue의 첫번째 요소 remove 진행
                q.append(each)
                curr_size += 1
            continue
        # 사이즈가 꽉 차 있을 떄!
        if each not in q:
            q.append(each)
            curr_size += 5
        else:
            q.remove(each) # queue의 첫번째 요소 remove 진행
            q.append(each)
            curr_size += 1
                    
    
    print(q)
    return curr_size


cacheSize = 5
cities = 		 ["a", "b", "c", "b"]
print(solution(cacheSize,cities))