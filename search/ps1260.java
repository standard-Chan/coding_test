package me.jeong.search;

/*
슈도코드
Deque 사용

    n(정점), e(간선), s(시작 노드)
    arr(2차원 ArrayList - 인접 그래프)
    for (n번)
        arr 초기화
    for (e번)
        arr 연결 지점 양방향으로 저장


    DFS
    stack 선언

    초기 방문 지점 stack 에 저장
    방문처리

    while(stack에 없을 때 까지){
        stack에서 pop
        for (pop한 지점과 연결된 점){
            if (방문 안되어있으면) {
                stack에 넣기
                방문처리
                해당 노드 출력
            }
        }
    }


    BFS
    queue 선언
    초기 값 queue에 넣기
    초기 지점 방문처리
    while(queue가 빌 때까지) {
        queue에서 pop
        for (pop한 지점과 연결된 지점) {
            if (방문 안했으면){
                방문처리
                queue에 넣기
                해당 노드 출력
            }
        }
    }
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ps1260 {
    public static void main(String[] args) throws IOException {
        int n, e, s;


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        e = Integer.parseInt(stringTokenizer.nextToken());
        s = Integer.parseInt(stringTokenizer.nextToken());

        boolean[] visitedDFS = new boolean[n+1];
        boolean[] visitedBFS = new boolean[n+1];

        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i=0; i<n+1; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i=0; i<e; i ++) {
            int a, b;
            stringTokenizer = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(stringTokenizer.nextToken());
            b = Integer.parseInt(stringTokenizer.nextToken());

            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        for (int i=1; i<n+1; i++) {
           arr.get(i).sort(Collections.reverseOrder());
        }


        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> queue = new ArrayDeque<>();

        stack.addLast(s);
        //visitedDFS[s] = true;

        while(!stack.isEmpty()) {
            int cur = stack.peekLast();
            stack.removeLast();
            if (visitedDFS[cur]) {
                continue;
            }
            visitedDFS[cur] = true;
            System.out.printf("%d ", cur);

            for (Integer v : arr.get(cur)) {
                if (!visitedDFS[v]) {
                    stack.addLast(v);
                }
            }
        }
        System.out.println();

        for (int i=1; i<n+1; i++) {
            Collections.sort(arr.get(i));
        }

        queue.addLast(s);
        visitedBFS[s] = true;
        System.out.printf("%d ", s);

        while(!queue.isEmpty()) {
            int cur = queue.peekFirst();
            queue.removeFirst();

            for (Integer v : arr.get(cur)) {
                if (!visitedBFS[v]) {
                    visitedBFS[v] = true;
                    queue.addLast(v);
                    System.out.printf("%d ", v);
                }
            }
        }
    }
}
