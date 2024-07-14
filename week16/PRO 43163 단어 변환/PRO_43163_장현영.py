from collections import deque

def solution(begin, target, words):
    
    if target not in words : 
        return  0
    
    return bfs(begin, target, words)


# bfs
def bfs(begin, target, words):

    queue = deque()
    queue.append([begin, 0]) #시작 단어와 단계 0으로 초기화
    
    while queue:
        curr_word, step = queue.popleft()
        
        if curr_word == target:
            return step
        
        #단어를 모두 체크하면서, 해당 단어가 변경될 수 있는지 체크
        for word in words:
            count = 0
            for i in range(len(curr_word)): #단어의 길이만큼 반복하여
                if curr_word[i] != word[i]: #단어에 알파벳 한개씩 체크하기
                    count += 1
                    
            if count == 1: 
                queue.append([word, step+1])