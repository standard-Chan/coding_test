'''
- 대기큐 정렬 (소요시간, 요청시각, 번호)

- 출력 ()ms 시점에 ()ms가 소요되는 ()번 작업 요청
- return : 평균 시간 (작업종료시각 - 요청시각)/N

# 하면서 생각하지 못한 문제점
처음에 pq에 모두 넣어서 처리하려고 했는데, 
아직 들어오지 않은 job인데, 우선순위가 높아서
들어온 job이 처리되지 못하는 문제가 있다
따라서 정확한 시각에 넣어야한다.

처리할 것
- [ ] 대기큐 정렬
- [ ] 매 초마다 ms 증가
- [ ] if lock 해제된 경우, 요청 시각 검사하여 대기큐에 넣기
- [ ] 작업 시작시 lock 획득
- [ ] 작업 종료시 lock 해제

'''
import heapq

def solution(jobs):
    answer = 0
    sec = 0
    lock = False
    endTime = 0       # 현재 작업 종료 시각
    startTime = 0     # 현재 작업 시작 시각

    pq = []
    completeCnt = 0
    idx = 0
    totalJobs = len(jobs)

    # 요청 시각 정렬
    jobs.sort(key=lambda x: x[0])

    while completeCnt < totalJobs:
        
        # 도착한 작업 pq에 추가
        while idx < totalJobs and jobs[idx][0] <= sec:
            req, dur = jobs[idx]
            heapq.heappush(pq, (dur, req))   # (소요시간, 요청시간)
            idx += 1

        # 현재 작업 종료
        if lock and endTime == sec:
            lock = False
            completeCnt += 1
            answer += (endTime - startTime)   # (종료 - 요청)
        
        # 새 작업 시작
        if not lock and pq:
            dur, req = heapq.heappop(pq)
            lock = True
            startTime = req
            endTime = sec + dur

        sec += 1

    return answer // totalJobs
