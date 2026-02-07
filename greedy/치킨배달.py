N, M = map(int, input().split())

store = 0
info = []
dist = []
total_dist = []
for i in range(N):
    info.append(list(map(int, input().split())))

def arrDist(N):
    global info, dist, store
    for i in range(N):
        for j in range(N):
            if(info[i][j] == 2):
                store += 1
                dist.append(calDist(i, j, N))


def calDist(r,c, N):
    global info
    dist=[]
    for i in range(N):
        for j in range(N):
            if (info[i][j] == 1):
                dist.append(abs(r-i) + abs(c-j))
    return dist


def combinationSum (store, selected, start, m): # m=남아야 할 치킨집 수 - 1
    global dist
    global M
    for i in range(start, store-m):
        selected2 = selected[:]
        selected2.append(i)
        
        if (len(selected2) == M):
            #print("selected: ",selected2)
            #print("dist: ", dist)
            total_v = 0
            for j in range(len(dist[0])):
                min_dist = 999
                for k in selected2:
                    #print(min_dist, dist[k][j])
                    min_dist = min(min_dist, dist[k][j])
                #print(min_dist)
                total_v += min_dist
                        
            global total_dist
            total_dist.append(total_v)
            #print('------')
        
        else:
            combinationSum(store, selected2[:], i+1, m-1)

arrDist(N)
combinationSum(store, [], 0, M-1)

print(min(total_dist))


