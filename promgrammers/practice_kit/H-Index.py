'''
# 문제 파악
H-Index : 다음을 만족하는 최대 h값. 여러 논문 중에서 h번 이상 인용된 논문이 h개 이상을 만족하는 h.

[3, 0, 6, 1, 5]
h=1 4개 만족 O
h=2 3개 만족 O
h=3 3개 만족 O
h=4 2개 만족 X 따라서 H-Index는 3

# input 
인용된 논문 정보

# output
H-Index 값

# 문제 풀이
## 방법 1
brute force로 하는 경우 -> n*h = 10,000,000. 충분

## 방법 2
정렬 후 계산
h=1 부터 차례대로 올라가기
for 반복문 : 배열 순회 -> 조건에 해당 안되는 순간 종료 -> h값과 조건에 해당하는 값 k 비교 -> h < k 인경우 등록
계속 반복
최대 h 찾기

'''
def solution(citations):
    answer = 0
    citations.sort(key=lambda x: -x)
    maxC = max(citations)
    for h in range(1, maxC+1):
        cnt = 0
        for i in citations:
            if (h > i): break
            cnt += 1
        if h <= cnt: answer = max(answer, h)        
    return answer
