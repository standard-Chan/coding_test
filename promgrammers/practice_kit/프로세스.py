# 루프문 처리
# 가장 우선순위가 큰 값 처리

def solution(priorities, location):
    answer = 0
    sortCursor = 0
    i = 0
    
    # priorities 정렬한 배열 생성
    sort = sorted(priorities, reverse = True)
    # 정렬한 배열을 처음부터 하나씩 읽으면서
    while(True):
        if (i >= len(priorities)):
            i=0
        
        if (priorities[i] == sort[sortCursor]):
            priorities[i] = -1
            sortCursor += 1
            answer += 1
            if (i == location):
                break;
            
        i += 1
    print(sort)
    # location 에 맞춰 
    return answer

'''
  모든 일을 한번에 처리하려고 하지 말고, 정말 하나의 단위로 나눈 후에 처리하도록 하자.
  예를 들어서 위의 경우에는, 루프문 + 우선수위 처리 등을 한번에 하지 말고
  루프문 먼저 처리한 이후에 다른거 처리하고 이런식으로 하나의 작은 단위들을 먼저 처리해나가자
'''
