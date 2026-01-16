from collections import deque

def solution(n, wires):
    answer = 999
    # wires 넣기
    maps = [[] for i in range(n+1)]
    for i in wires:
        maps[i[0]].append(i[1])
        maps[i[1]].append(i[0])
        
    for i in wires:
        visited = [0 for i in range(n+1)]
        visited[i[1]] = 1
        k = abs( n - 2*bfs(i[0], visited, maps))
        answer = min(answer, k)
    return answer


def bfs(start, visited, maps):
    dq = deque()
    visited[start] = 1
    cnt = 1
    dq.append(start)
    
    while(len(dq) > 0 ):
        cur = dq.pop()
        for next in maps[cur]:
            if visited[next]: continue
            visited[next] = 1
            cnt += 1
            dq.append(next)
    
    return cnt











