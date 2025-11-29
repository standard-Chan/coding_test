def solution(s):
    answer = True
    stack = []
    for p in s:
    # ( 인 경우에 append
        if (p == '('):
            stack.append(p)
        elif (p == ')'):
             # ) 인 경우 -> list 마지막이 ( 이면 pop, else false 반환
            if (len(stack) > 0 and stack[-1] == '('):
                stack.pop()
            else:
                return False
    # list 길이가 0이면 true, 아니면 false    
    if (len(stack) > 0): return False
    return True
