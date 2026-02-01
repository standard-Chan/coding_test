from collections import deque

# M = 세로, N = 가로
M, N, K = map(int, input().split())

graph = [[0 for i in range(N)] for j in range(M)]
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
result = []
data = []
for i in range(K):
    data.append(list(map(int, input().split())))

for d in data:
    for i in range(d[0], d[2]):
        for j in range(d[1], d[3]):
            graph[j][i] = 1

def bfs():
    queue = deque()
    
    for y1 in range(M):
        for x1 in range(N):
            if (graph[y1][x1] == 1):
                continue
            count = 1
            queue.append([y1,x1])
            graph[y1][x1] = 1
            while(queue):
                fy, fx = queue.popleft()
                for i in range(4):
                    y = fy + dx[i]
                    x = fx + dy[i]
                    if ((x>=0 and y>=0 and x<=(N-1) and y<=(M-1)) and graph[y][x] == 0):
                        graph[y][x] = 1
                        count += 1
                        queue.append([y,x])
            result.append(count)

    return result
                
result = bfs()
result.sort()
print(len(result))
for i in result:
    print(i, end = ' ')
