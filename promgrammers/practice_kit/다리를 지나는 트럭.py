# while (True)
# 매초마다 넣을지 안넣을지 판단
# 넣는 경우 -> 맨 앞에 요소 제거 + 뒤에 추가
# 안 넣는 경우 -> 맨 앞 요소 제거
# 대기 트럭과 큐가 빈 경우 루프 종료

'''
컴파일 에러나 예상치 못한 문제가 많이 발생했다.
- 1. out of index
- 2. 무게 >= 인데, > 로 작성
- 3. 초기 무게가 weight 인데, 0으로 설정
- 4. 트럭이 올라가지 못할 때, 매 순간마다 append(0)을 해줘야하는데, 빼먹음

1의 경우에는 진행하면서 수정할 수 있다
2 => 초기 설계에서 등호까지 고려하여 설계를 진행해야겠다
3 => 초기 값을 설계하는 과정을 넣도록 하자
4 => 초기 설계에서 단위를 작게 나누지 못했음
    "# 안 넣는 경우 -> 맨 앞 요소 제거" 이렇게 설계를 했는데,
    "# 안 넣는 경우 -> 맨 앞 요소 제거 + 맨뒤 요소 추가" 이렇게 구체적으로 설계 필요



'''


from collections import deque

def solution(bridge_length, weight, truck_weights):
    queue = deque()
    # 다리 길이만큼 초기화
    for i in range(bridge_length):
        queue.append(0)
    limitWeight = weight
    curWeight = 0
    nextIdx = 0
    sec = 0
    while(True):
        # 매초마다 실행하는 로직
        sec += 1
        curWeight -= queue.popleft()

        # 매초마다 넣을지 안넣을지 판단
        if (nextIdx < len(truck_weights) and limitWeight >= curWeight + truck_weights[nextIdx]):
            queue.append(truck_weights[nextIdx])
            curWeight += truck_weights[nextIdx]
            nextIdx += 1
        else:
            queue.append(0)
        
        if (curWeight <= 0 and nextIdx >= len(truck_weights)):
            break
        
    return sec
