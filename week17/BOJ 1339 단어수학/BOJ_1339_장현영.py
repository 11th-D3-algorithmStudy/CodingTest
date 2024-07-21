"""
단어 중 가장 길이가 긴 단어부터 확인하여 9로 설정
단어는 key
길이로 몇 자리 수인지 value로 기록
value 합이 가장 큰 순서대로 key를 987순으로 할당하여 계산
"""

import sys

input = sys.stdin.readline

n = int(input())
word_list = []
d = dict()
for _ in range(n):
    tmp = input().rstrip()
    for i in range(len(tmp)):
        d.setdefault(tmp[i], list())
        d[tmp[i]].append(10**(len(tmp)-1-i))

for key, value in d.items():
    d[key] = sum(value)

sorted_d = sorted(d.items(), key=lambda x: -x[1])
ans = 0
i = 0
for num in range(9,9-len(sorted_d),-1):
    ans += sorted_d[i][1] * num
    i += 1

print(ans)




