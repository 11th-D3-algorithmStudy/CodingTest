# 1,1에서 5,5로 가는 최단 거리

from collections import deque

dr = [0,1,0,-1]
dc = [1,0,-1,0]

def bfs(maps):
    n = len(maps)
    m = len(maps[0])
    visited = [[0]*m for _ in range(n)]
    queue = deque()
    queue.append((0,0,1))
    while queue:
        r,c,dist = queue.popleft()
        if (r == n-1 and c == m-1):
            return dist
        for i in range(4):
            nr = r+dr[i]
            nc = c+dc[i]
            if (0<= nr < n and 0<= nc < m and maps[nr][nc] and not visited[nr][nc] ):
                visited[nr][nc] = 1
                queue.append((nr,nc,dist+1))
                print('here')
    return -1

def solution(maps):  
    return bfs(maps)

maps =[[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]
print(solution(maps))