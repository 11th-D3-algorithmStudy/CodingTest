'''
수열 A가 주어졌을 때, 가장 긴 감소하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 30, 10, 20, 20, 10} 인 경우에 가장 긴 감소하는 부분 수열은 A = {10, 30, 10, 20, 20, 10}  이고, 길이는 3이다.

반대로 접근

1번째에서 1번째까지 본인보다 큰 숫자 부분 수열 최대 길이 dp에 넣고
2번째에서 1~2까지 본인보다 큰 숫자 수열 최대 길이 dp에 넣고
...
n번째에서 1 ~ n까지 증가하는 부분 수열 최대 길이 dp에 넣는 방식으로 접근하기
 
'''


import sys

input = sys.stdin.readline

#A = {10, 30, 10, 20, 20, 10}
n = int(input())
arr = [0] + list(map(int, input().split()))
dp = [1 for _ in range(n+1)] # 0은 더미
for i in range(1,n+1):
    for j in range(1,i+1):
        if arr[j] > arr[i]: # 비교하는 수가 더 크다면
            dp[i] = max(dp[i], dp[j]+1) # 단순 1개 더 커진 것과 지금까지 누적 계산과 max값 비교 

print(max(dp))

