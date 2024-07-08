'''
맨해튼 거리 1 --> 한칸차이
맨해튼 거리 2 --> 같은 행 또는 열에서 두칸 차이 or 대각선
그 이상은 거리두기 준수한 것
'''
from collections import deque

def able(each):
    arr = [list(each[i]) for i in range(5)]
    q = deque()
    visited = [[0]*5 for _ in range(5)]
    for i in range(5):
        for j in range(5):
            if arr[i][j] == 'P': #응시자
                q.append((0,i,j))
    while q:
        print(q)
        cnt,x,y = q.popleft()
        visited[x][y] = 1
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < 5 and 0 <= ny < 5:
                if arr[nx][ny] == 'P' and not visited[nx][ny]:
                    return 0
                elif arr[nx][ny] =='O' and cnt == 0: # 빈 테이블
                    q.appendleft((cnt+1,nx,ny))

    return 1

def solution(places):
    ans = []
    for each in places:
        ans.append(able(each))
    return ans

# 거리두기에 걸리는 경우
dx = [-1,1,0,0]
dy = [0,0,1,-1]