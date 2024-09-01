"""
180분 --> 5000, + 10분 600
181~190분 : 5600
0~180 : 5000
out이 없다면 23:59로 적용하기
최종 답은 차량번호가 작은 순으로 가격 출력하기
하루의 누적 시간을 계산해야 함
시간이 잘못 입력되진 않으므로
내역은 중요하지 않다고 판단(시간 받았을 때 길이가 짝수가 아니면 23:59 추가)
"""
from collections import defaultdict
import math

def solution(fees, records):
    d = defaultdict(list) #
    cnt = defaultdict(int)
    res = defaultdict(int)
    for each in records:
        time,num,io = each.split()
        d[num].append(time)
    for car in d.keys():
        if len(d[car]) %2: # 홀수
            d[car].append('23:59')
    print(d)
    for car,t_list in d.items():
        total = 0
        for i in range(1,len(t_list),2):
            h1,m1 = t_list[i].split(':')
            h0,m0 = t_list[i-1].split(':')
            mins = ((int(h1)-1)-int(h0))*60 + int(m1)+60- int(m0)
            total += mins
        cnt[car] = total
    # 계산된 total로 fee 계산하기
    for car,total in cnt.items():
        if total > fees[0]:
            money = int(fees[1] + (math.ceil((total-fees[0])/fees[2]))*fees[3])
        else:
            money = fees[1]
        res[car] = money
    answer = []
    for car in sorted(list(res.keys())):
        answer.append(res[car])
    return answer


fees = [120, 0, 60, 591]
records =["16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"]
solution(fees,records)