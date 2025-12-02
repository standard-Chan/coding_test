# stack을 통해 진행한다
'''
    중간에 값이 빠지는 경우를 고려하여 시간이 같이 저장되어야한다.
    stack <price, time>
    알고리즘
    stack에 <price, time> 을 넣는다
        1 - price가 큰 경우 => 그대로 stack에 넣는다
        2 - price가 작은 경우 => 
            => stack에서 빼고, 해당 index 값에 현재시간 - time을 
                계산하여 값으로 추가한다
    마지막 요소에는 계산시에는 동일한 시간으로 0을 넣어 계산한다
'''

# 핵심

# stack <price, time> 만들기
# price 순회
# stack에 값 넣기
# stack에 값 빼기
# 마지막 요소 한번 더 계산하기

def solution(prices):
    answer = [0 for i in range(len(prices))]
    stack = []
    
    for i in range(len(prices)):
        if (len(stack) > 0):
            while(len(stack) > 0 and stack[-1][0] > prices[i]) :
                # stack의 마지막 요소를 빼고
                _, time = stack.pop()
                # 해당 위치에 answer 저장
                answer[time] = i-time
        stack.append((prices[i], i))
    
    v = len(prices) - 1
    for _, time in stack:
        answer[time] = v - time
    
    return answer


# 초기 잘못 푼 문제 => 구현을 지나치게 복잡하게 함
# stack으로 상상을 해야하는데, 하다가 다르게 상상을 해버려서 발생한 문제
def solution2(prices):
    answer = []
    stack = []
    # answer 초기화
    answer = [0 for i in range(len(prices))]
    
    # price 순회
    for now in range(len(prices)):
        
        if (len(stack) == 0):
            stack.append((prices[now], now))
            print(f'stack : {stack}')
            continue
        # price가 큰 경우 스택에 값 넣기
        if (len(stack) > 0 and stack[-1][0] < prices[now]):
            stack.append((prices[now], now))
            print(f'stack : {stack}')
            continue
        
        elif len(stack) > 0:
            # loop문 : price가 클 때 까지
            p = stack[-1][0]
            while p > prices[now] :
                (p, time) = stack.pop()
                print(f'pop {(p,time)}')
                # answer[time] 에 now-time 값 저장
                answer[time] = now - time
                p = stack[-1][0]
            
    # 마지막에 (0, now) 주입해서 한번 더 실행
        
    print(stack)
    print(answer)
            
    return answer



