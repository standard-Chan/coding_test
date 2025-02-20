package me.jeong.dataStructure;
/*
25분 시작
제한 시간 2.6초
500만 개 => O(N)
1,000,000,000 => long으로 설정
152362373526
111222223322

알고리즘 슬라이딩 윈도우
1. 변수 선언
    최솟값
    구간 start
    구간 end == i

2. 입력값 받기
    N = 총 string 길이
    L = 구간 길이

3. 슬라이딩 윈도우 알고리즘
    (편의를 위해서 index 1부터 시작함.)
    예제 테스트. 구간 길이 3
    1 5 2 3 6 2 3 7 3 5 2 6
    초기
    if (index < L 경우)
        index : 1 ~ index 까지 중에 최솟값 찾기
        1 1
    else if (index >= L)
    while(index < N(끝지점))
        슬라이딩 윈도우 적용
        remove(string.charAt(start));
        start++;

        end ++;
        add(string.charAt(end));

        start에 있는 수 제거
        start ++

    함수
    !! (해결) 최솟값이 여러개 중복으로 있을 경우 해결해야함.
    최솟값의 개수를 minCnt에 적어놓자.

@!@!!@ 아래를 해결할 수 없어서 답을 보았음.@!@!@!@!@!@
    !! 최솟값이 계속해서 remove되는 경우
    ex) 123456789인 경우, 매번 최솟값을 검색해야함. => O(N^2) 문제점.
        remove(char C){
            if(C == 현재 최솟값)
                minV = 그 다음 최솟값
        }

@!!@!@!@!-------------------------------@!@!@!@!@!@!
        add(char C)

4. 출력


// 슬라이딩 윈도우와 Deque를 합쳐서 사용 => 최소 최대값 문제 해경

 */

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class findMinValue11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int L = Integer.parseInt(stringTokenizer.nextToken());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        stringTokenizer = new StringTokenizer(bf.readLine());

        Deque<Node> deque = new ArrayDeque<>();

        for (int i=0; i<N; i++) {
            // 처리 순서
            int value = Integer.parseInt(stringTokenizer.nextToken());
            // 새로운 값을 Deque에 넣는다.
            // 작을 경우 삭제
            while (!deque.isEmpty() && deque.getLast().value >= value){
                deque.removeLast();
            }
            // 가장 뒤에 추가
            deque.addLast(new Node(i, value));

            // 구간 밖의 노드 제거. 앞에서부터 확인한다. L 길이 밖의 index 값을 삭제한다.
            if (deque.getFirst().index < i-L+1) {
                deque.removeFirst();
            }
            // 가장 앞에있는 최소값을 버퍼에 write한다.
            bw.write(deque.getFirst().value + " ");
        }

        bw.flush();
        bw.close();

    }

    static class Node {
        public int index;
        public int value;
        Node (int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
