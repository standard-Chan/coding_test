'''
# 문제 파악
n개의 정수들을 +, - 연산을 해서 목표하는 숫자를 만드는 방법 개수 찾기

input : n개의 정수들 (n <= 20)
output : 목표하는 숫자 
return : 만드는 방법의 개수

# 문제 풀이
만드는 방법의 개수 -> 모든 경우 중에 해당 조건만 만족하는 경우 찾기 -> 완전 탐색
완탐을 했을 때, 시간이 초과되지는 않는가? 20^2개 = 1024 * 1024 < 1,000,000 * 10  => 문제 없음 
따라서 완전 탐색으로 푼다.

dfs 로 푼다.
종료 조건은 depth == 5. dpeth를 numbers의 index로 사용
[+, -] 를 arr로 설정

'''

def solution(numbers, target):
    return dfs(0, numbers, target, 0)

def dfs(depth, numbers, target, result):
    cnt = 0
    if depth == len(numbers):
        if result == target: return 1
        return 0
    
    for i in range(2):
        if i == 0:
            cnt += dfs(depth+1, numbers, target, result + numbers[depth])
        else:
            cnt += dfs(depth+1, numbers, target, result - numbers[depth])
    
    return cnt
    
    
