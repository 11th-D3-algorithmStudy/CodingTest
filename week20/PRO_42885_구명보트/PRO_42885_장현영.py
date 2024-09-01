# limit 이내이면 첫, 마지막 모두 태우고
# limit 밖이면 무거운 사람 먼저 태워야 가장 많이 태움

def solution(people, limit):
    answer = 0
    left,right =0,len(people)-1
    
    people.sort()
    
    while(left<=right):
        if people[left] + people[right] <= limit:
            left += 1
            right -= 1
        else:
            right -= 1
        answer +=1
    
    return answer