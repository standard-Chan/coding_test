'''
# 문제 파악
네트워크 정의 : 연결되어서 도달할 수 있는 범위
그래프 구조에서, 상호 연결되어 도달할 수 있는 범위까지를 하나의 네트워크라고 할때, 네트워크의 수를 구하는 문제

# input : 컴퓨터 연결 정보. 컴퓨터의 수 = n <= 200
[y][x] 가 연결되어있으면, computers[y][x] == 1 임

# output : 네트워크 그룹 개수

# 풀이 방법
- dfs로 visit 처리 하면서, 하나의 네트워크 모두 방문처리
네트워크 방문이 끝나면 cnt + 1

- 이미 방문되어있는 경우 dfs 시작안하고 cnt 변화 없음

위 과정을 모든 computer에 진행.

dfs는 내부에서 세는것이 목적이 아니고, visited 처리해서 네트워크 방문 처리를 하는것이 목적

sudo code

for i in all_computer:
    if 방문 안했을 경우 : 
        dfs()
        cnt += 1
    


'''

def solution(n, computers):
    answer = 0
    visited = [0 for i in range(n)]
    for cur in range(n):
        if not visited[cur]:
            visited[cur] = 1
            answer += 1
            dfs(cur, visited, computers, n)
    return answer

def dfs(cur, visited, computers, n):

    for next in range(n):
        if cur==next: continue
        if computers[cur][next] == 1 and not visited[next]:
            visited[next] = 1
            dfs(next, visited, computers, n)
        
