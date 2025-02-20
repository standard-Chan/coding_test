package me.jeong.graph;
/*
    k번째의 최단경로 구하기
    <입력>
    시작 도시는 1번
    n(도시수), m(도로수), k(구하고자 하는 것)
    a b c  (a에서 b로 가는데 c만큼 소요)
    a b c
    ...

    <출력> 1번에서 해당 도시에 가는데 걸리는 시간
    2번
    3번
    4번
    5번
    (같은 정점 여러번 가능, 존재하지 않을 경우 -1)

    다익스트라
    1  2  3  4  5   방문한 노드
    0  -  -  -  -    1
    0  2  7  5  6    2
    0  2  6  4  6    4
    0  2  6  4  6    3
    0  2  6  4  6    5

    모든 거리를 모든 노드를 방문하면서 전부 저장하기
    최단거리를 구하고 각 최단거리에서
    <노드 총 거리 정보>
    1  2  3  4  5
    0  2  7  5  6
          6  4
            12 14
      10     7

    0번 노드 시작
    1  2  3  4  5
    0  2  7  5  6
    0  2  6  4  6


    무한히 계속해서 생김. 처음부터 k(300백만)번째까지 모두 구해놓을 수는 없다. 따라서 패턴을 찾아야함.
    일단 최단거리를 구하자.
    1  2  3  4  5
    0  2  6  4  6

    위에서 연결된 노드들을 구하자.
    1 2 3 4 5
  1   2 7 5 6
  2     4 2
  3       6 8
  4
  5   4   1

    2번 노드의 최단거리를 예측해보면
    1번노드 + 2 || 5번노드 + 4이다. 그러면
    맨 위(최소)부터 차례대로 더해나가보자.

    1  2  3  4  5
    0  2  6  4  6
      10  7  5 14
             7
            12

       시간복잡도 nmk  3,000,000,000


    <슈도코드>
    초기화
    거리 정보를 저장할 배열 (2차원 배열)
    최단 거리를 저장할 배열 (우선순위 큐)
    우선순위 큐
    n(도시수), m(도로수), k(구하고자 하는 것)
    a b c  (a에서 b로 가는데 c만큼 소요)
    a b c
    ...

    nmk 입력 받기

    for (m) {
        입력 map에 가중치 추가하기
    }

    우선순위큐에 노드 1번 넣기
    while(큐가 빌때까지) {
        우선순위 큐에서 노드 빼기
        for (노드 에지 수) {
            if (연결된 노드의 크기 < k) {
                거리 추가하기
                해당 노드 우선순위 큐에 넣기
            else if (연결된 노드의 크기가 k면) {
                더이상 구할 필요가 없음. 마지막에 있는 노드 거리가 가장 최소일 경우에만 추가
            }
        }
    }



 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ps1854dijk {
    static int[][] map;
    static ArrayList<PriorityQueue<Integer>> dist = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int n,m,k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int a, b, w;
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map[a][b] = w;
        }

        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2)
                    return 1;
                return -1;
            }
        };
        for (int i=0; i<n+1; i++) {
            dist.add(new PriorityQueue<Integer>(k, cp));
        }

        pq.add(new Node(1, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();
            for (int next=1; next<n+1; next++) {
                int weight = map[cur.num][next];
                if (weight <= 0)
                    continue;

                int distance = weight + cur.dist;

                if (dist.get(next).size() < k) {
                    dist.get(next).add(distance);
                    pq.add(new Node(next, distance));
                }
                else if (dist.get(next).size() >= k ){
                    if (dist.get(next).peek() > distance) {
                        dist.get(next).poll();
                        dist.get(next).add(distance);
                        pq.add(new Node(next, distance));
                        //더이상 구할 필요가 없음.마지막에 있는 노드 거리가 가장 최소일 경우에만 추가
                    }
                }
            }
        }

        for (int i=1; i<n+1; i++) {
            if (dist.get(i).size() < k) {
                System.out.println(-1);
            }
            else {
                System.out.println(dist.get(i).peek());
            }
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            if(this.dist > node.dist)
                return 1;
            else if (this.dist == node.dist)
                return 0;
            return -1;
        }
    }
}
