'''
# n개의 송전탑. 연결 중 1개를 끊어서 2개의 집단으로 나누기
최대한 개수 차이가 적도록

# input
송전탑 연결 정보. 송전탑 개수 <= 100

# output
두 그룹 개수 차이

## 방법 1. 모든 연결을 끊어보고, 집단의 수 차이를 비교한다.
끊는 것 -> 최대 100
집단에 포함된 송전탑 수를 세는 방법! -> dfs 혹은 bfs로 개수 세기

a개와 N - a 개. |N-2a|


'''

# 여기에서 maps[i[n]].remove 하고 다시 붙이는 방식을 이용했는데, 해당 방식 말고, 
# 연결 부분 a, b 라고 하면, b를 visited true로 설정하고, a 노드에서 부터 연결된 모든 탑의 개수를 찾으면 된다.
def solution(n, wires):
    answer = 999
    # 연결 관계 map
    maps = [[] for i in range(n+1)]
    for i in wires:
        maps[i[0]].append(i[1])
        maps[i[1]].append(i[0])
    # 반복문 돌면서 1개씩 끊기
    for i in wires:
        # 임의로 1개 선택해서, 이것과 연결된 모든 탑 개수 찾기
        maps[i[0]].remove(i[1])
        maps[i[1]].remove(i[0])
        k = dfs(1, maps, [0 for i in range(n+1)])

        # | 전체 탑 - 2 * 위에서 구한 탑 |
        diff = abs(n - 2*k)
        if (diff < 0): diff = diff * (-1)
        # 위 값이 가장 작도록 answer 설정
        answer = min(diff, answer)
        maps[i[0]].append(i[1])
        maps[i[1]].append(i[0])
    return answer

def dfs (start, maps, visited):
    cnt = 0
    if start == 1:
        visited[1] = 1
        cnt += 1
    
    for i in maps[start]:
        if visited[i]: continue
        visited[i] = 1
        cnt += 1
        cnt += dfs(i, maps, visited)
        
    return cnt
    
