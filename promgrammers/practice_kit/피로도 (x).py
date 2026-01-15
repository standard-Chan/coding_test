'''
# 조건
- 최소 필요 피로도 : 던전 시작 전에 필요한 에너지
- 소모 피로도 : 탐험 후 소모되는 피로도

# input
피로도 : 1 <= k <= 5000
던전 배열 : [[최소 필요 피로도, 소모 피로도], ...],  길이 <= 8

# output
탐험할 수 있는 최대 던전 수

# 풀이 1

# 완전탐색
## 어중간하게 수학으로 답을 찾을 수 있을 것 같은거 -> 완탐으로 풀어야함
총 경우의 수가 250만을 안넘음.  -> 시간 복잡도 통과

1. 진행할 수 있는 모든 경우의 수를 찾기 (순열)
2. 위에서 찾은 모든 순서를 직접 시뮬레이션
3. 나온 결과 중, 가장 큰 값을 반환

'''

def solution(k, dungeons):
    answer = -1
    allCase = []
    perm(0, len(dungeons), [0 for i in range(0,len(dungeons))], allCase, [])
    
    # 위에서 찾은 모든 순서를 직접 시뮬레이션
    for seq in allCase:
        cur = k
        cnt = 0
        for i in seq:
            # 필요 피로도 확인
            minf = dungeons[i][0]
            if (cur < minf): break
            # 피로도 감소
            fatigue = dungeons[i][1]
            cur -= fatigue
            
            cnt += 1
        # answer 보다 클 경우에 저장
        if cnt > answer: answer = cnt
            
    return answer

# end 는 배열의 길이, depth는 0부터 시작, result=[]
def perm (depth, end, visited, allCase, result):
    if depth == end:
        allCase.append(result)
        return
    
    for i in range(end):
        if visited[i]: continue
        visited[i] = 1
        nextResult = result[:]
        nextResult.append(i)
        perm(depth+1, end, visited, allCase, nextResult)
        visited[i] = 0
        
