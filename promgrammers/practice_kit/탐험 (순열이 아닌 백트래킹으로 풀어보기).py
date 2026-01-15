
def solution(k, dungeons):
    answer = explore(k, 0, [0 for i in range(len(dungeons))], dungeons)
            
    return answer

# 던전 탐험
# 종료 조건, cnt
def explore(curF, cnt, visited, dungeons):
    maxCnt = cnt
    for i in range(len(dungeons)):
        if visited[i]: continue
        # 최소 피로도 만족 못하면 pass
        if curF < dungeons[i][0]: continue
        
        visited[i] = 1
        maxCnt = max(maxCnt, explore(curF-dungeons[i][1], cnt+1, visited, dungeons))
        visited[i] = 0
    
    return maxCnt
