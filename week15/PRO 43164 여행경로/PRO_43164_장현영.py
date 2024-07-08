# 여행 경로
# icn 출발 주어진 항공권 모두 사용
# 가능한 경로 2개이상인 경우 알파벳 순서가 앞서는 경로로 return
# edge 알려주면 반드시 edge를 활용한 경로를 보여라
# 가능한 경로가 2개 이상이면 알파벳 순서가 앞서는 경로를 return
# queue 활용해서 bfs로 경로 찾기
# 3차원 queue로 방문까지 넣어서 확인
# 사전 활용해서 갈 수 있는 경우 다 파악하기

from collections import deque

def solution(tickets):
    answer = [] # 배열 내 배열이 계속 삽입됨.
    queue = deque()
    queue.append(('ICN',['ICN'],[]))
    length = len(tickets)
    while queue:
        curr, temp, visited = queue.popleft()
        if len(visited) == length: # 모든 티켓을 다 사용했을 때!
            answer.append(temp) # 갈 수 있는 정답지

        # i로 접근해야 티켓간의 겹침 발생을 방지함.(모두 돌기 위함)
        for i,edge in enumerate(tickets):
            if edge[0] == curr and not i in visited: 
                queue.append((edge[1], temp+ [edge[1]], visited+[i]))
    answer.sort()
        
    return answer[0]

tickets1 = [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]
tickets2 = [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]

