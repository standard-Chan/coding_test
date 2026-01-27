'''
# 문제 파악
다음 변환 조건을 거쳐서 target 단어로 변환하는데 까지 걸리는 최소 단계 구하기.
조건
1. 주어진 단어로의 변환만 허용
2. 1개의 글자씩만 변환 가능
ex. 시작 단어 : hit, 목적 단어 : cog 
주어진 단어 사전 : hot, dot ...


# input
- 단어는 항상 소문자. 
- 3 <= 단어 개수 <= 50  (중복 없음)
- 3 <= 길이 <= 10. 단어의 길이는 항상 동일
- 시작단어 != 끝 단어

# output
- 최소 단계 반환
- 변환 불가능한 경우 0 반환

문제 파악하는데 소요된 시간 : 4분 10초

# 문제 풀이
우선 패턴을 찾기보다는 모든 경우의 수를 찾아내는 문제라는 것을 알 수 있는데,
그 이유는 변환 불가능한 경우 0 을 반환하는 조건 때문. 
변환 불가능한지 알기 위해서는 기본적으로 모든 경우의 수를 다 확인해봐야한다. 
(패턴을 찾을 수도 있겠지만 그보다 이게 더 빠를 것 같다)

따라서 백트래킹 or dfs or bfs로 구현한다.
dfs 백트래킹로 구현하자. 종료 조건이 target으로 변환 시니까.
가장 짧은 단계를 찾는 거니까 visited를 사용하자.


다음 tree를 그리자
     start
     /   \
  수정1  수정2 ...
  /
target

target이 되었을 경우, 정답 배열에 추가


## 출력값에 1이 들어가있음.
왜 들어갔을까? => 종료 조건을 다시 봄
pass 시에 changeable을 사용하지 않음. 즉, 주석에 적은 내용을 실수로 구현하지 않고 빼먹음

풀이 시간 29분 소요
문제의 원인 -> 문제를 제대로 이해하지 못함.
target이 words 배열에 없는 경우는 절대 target으로 변경될 수 없음.
나는 target이 words에 자동으로 들어가는 줄 알았음.

!! 앞으로는 테스트 케이스에 나와있는 예시를 직접 머리로 굴려서 답을 확인해보자

'''


def solution(begin, target, words):
    answer = 0
    result = []
    # if not (target in words):
    #     words.append(target)
    dfs(1, len(words), target, begin, result, [0 for i in range(len(words))], words, 0)
    
    if len(result) == 0 : 
          return 0
    answer = min(result)
    print(result)
    return answer

def dfs(depth, maxDepth, target, curWord, result, visited, words, cnt):
    # 종료 조건 (depth or target)
    if curWord == target:
        result.append(cnt)
        return
    if depth == maxDepth: return
    
    for i in range(len(words)):
        # pass 조건 - 방문 했거나, 변환 불가능한 경우
        if visited[i]: continue
        if not isChangeable(curWord, words[i]): continue
        visited[i] = 1
        dfs(depth+1, maxDepth, target, words[i], result, visited, words, cnt+1)
        visited[i] = 0
    
def isChangeable(cur, target):
    notEqual = 0
    for i in range(len(cur)):
        if cur[i] != target[i]: 
            notEqual +=1
    
    if notEqual == 0 or notEqual > 1 : return False
    return True
    
