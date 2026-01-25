N = 0

count = 0
N = int(input())

pos = [] #index = r, value = c
for i in range(N+1):
    pos.append(0)


def promising(r) :
    for i in range(1, r):
        if ((pos[r] == pos[i]) or (abs(pos[r]-pos[i]) == abs(r-i))):
            return False
    return True

def NQueens(r):
    global count
    global pos
    if (r == N):
        count += 1
        return
    
    for i in range(1, N+1): # columns
        pos[r+1] = i
        if(promising(r+1)):
            #print(pos[1:])
            NQueens(r+1)

NQueens(0)
print(count)
