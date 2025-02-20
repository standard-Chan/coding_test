package me.jeong.search;

/*
방향 없는 그래프 -> 연결요소

방법 1
모두 연결한 이후, 타고 들엉가면서 집합 개수 구하기
각 집합의 root node를 사용해서 저장하기

방법 2
연결되어있는 노드를 모두 방문할때, 시작하는 횟수를 구하면 된다. (모두 연결되어있으므로)
ex. 모두 연결되어있으면 1번의 시작
    2개의 연결 요소로 나뉘어져있으면 2번의 시작. => 2개의 연결요소

시간 복잡도 = T/F검사 : O(N) + 방문 : O(E+V)

수도코드
입력받기
각 연결을 배열에 저장.

N개에 요소를 순회하면서 False인 지점을 시작점으로 하여 해당 연결요소를 모두 방문하여 TRUE로 변경시킨다.
시작점의 개수를 CNT로 출력


6 5
1 2
2 5
5 1
4 6
3 4


1 2 3 4 5 6
0 1 0 0 1 4

 */

/*
슈도코드
n(노드개수), m(에지 개수)
A(그래프 데이터 저장 - 인접리스트)
visited (방문 여부 저장 리스트)
for (n번)
    A 각 index ArrayList로 초기화
for (m번)
    A에 그래프 저장

for (n번){
    if (방문 안했으면)
        cnt ++
        DFS로 방문
}

DFS (현재 노드) {

    현재 노드와 연결된 노드 구하기

    for 연결된 노드 {
        if (추가된 노드가 방문되지 않았으면)
            추가된 노드 방문 처리
            DFS(추가된 노드)
    }
}
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class ps11724 {
    public static void main(String[] args) throws IOException {
        int cnt = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());


        int N = Integer.parseInt(stringTokenizer.nextToken());
        int E = Integer.parseInt(stringTokenizer.nextToken());

        // 배열 생성
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            arr.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[N+1];

        for (int i=0; i<E; i++) {
            int a, b;
            stringTokenizer = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(stringTokenizer.nextToken());
            b = Integer.parseInt(stringTokenizer.nextToken());

            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        // DFS로 순회
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i=1; i< arr.size(); i++) {
            // 방문한 경우
            if (visited[i])
                continue;
            cnt ++;
            deque.addLast(i);
            visited[i] = true;
            while(!deque.isEmpty()) {
                int cur = deque.peekLast();
                deque.removeLast();
                for (Integer v : arr.get(cur)) {
                    if (visited[v])
                        continue;
                    deque.addLast(v);
                    visited[v] = true;
                }
            }
        }

        System.out.println(cnt);


    }
}

