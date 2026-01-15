# 문자열 각 문자를 읽어서, 모든 경우의 수를 담은 배열 생성
# ㅁㅁㅁㅁㅁ
# 각 배열을 순회하면서, 소수인지 판별
def solution(numbers):
    answer = 0
    allCase = set()
    
    # 모든 경우 생성
    for i in range(1, len(numbers)+1):
        perm(0, i, [0 for n in range(len(numbers))], numbers, allCase, "")
    
    for i in allCase:
        if isPrime(i): answer += 1
    
    return answer

def perm(depth, end, visited, arr, allCase, result):
    if (depth == end):
        allCase.add(int(result))
        return
    
    for i in range(0, len(arr)):
        if visited[i]: continue
        visited[i] = 1
        perm(depth+1, end, visited, arr, allCase, result+arr[i])
        visited[i] = 0
        
def isPrime(n):
    if (n<2): return 0
    end = int(n**(1/2)) + 1
    for i in range(2, end):
        if n%i == 0: return 0
    return 1
