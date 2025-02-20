package me.jeong.graph;
/*

    !!!!!!  long 타입으로 선언하지 않아서 오답이 게속 발생함. !!!!!!
    근데 왜 음수 인 경우 최솟값이 -30억이 나오는거지?
    => 1번 노드와 모든 노드와의 에지가 -10000만인 경우 => 1회 : -6000만이 가능하다.
    따라서 -30억까지 최솟값이 나올 수 있다.
    하지만 양수일 때에는 최솟값이 아니기 때문에 최대 500만까지 나올 수 있다.


    벨만 포드 알고리즘
    노드 기준이 아닌, 에지 기준으로 진행. 그래서 자료구조도 에지를 중심으로 만들어야한다.


    1  2  3  4  5
    0  -  -  -  -
    0  8  3  -  -
    0  8  3 10 13
    0  6  3 10 13
    0  6  3 10 11

    슈도코드
    초기화
    N(도시 수), M(에지 수)
    에지 정보를 담은 에지 그래프(ArrayList)
    거리 정보 배열
    음의 사이클 유무

    for (에지수) {
        에지 그래프 초기화
    }
    for (에지수) {
        에지 정보 받기
    }
    for (노드개수 - 1 ) {
        for (에지의 개수) {
            if(에지의 시작 노드가 무한이 아닌 경우 && 끝 노드 거리 > 시작 노드 거리 + 에지 가중치) {
                끝 노드 거리 = 시작 노드 거리 + 에지 가중치
            }
        }
    }

    for (에지의 개수) {
        if(에지의 시작 노드가 무한이 아닌 경우 && 끝 노드 거리 > 시작 노드 거리 + 에지 가중치) {
            했을 때 변화하는 값이 있는지 확인하기
        }
    }

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ps11657_bellman {
    static final int INF = Integer.MAX_VALUE;

    static int N, M;
    static ArrayList<Edge> graph = new ArrayList<>();
    static long[] dist;
    static boolean negativeCycle;

    static public void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<M;i ++){
            st = new StringTokenizer(bf.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.add(new Edge(a,b,c));
        }

        dist = new long[N+1];
        // dist 초기화
        for (int i=0; i<N+1; i++) {
            dist[i] = INF;
        }
        dist[1] = 0;

        for (int i=0; i<N-1; i++) {
            for (Edge e : graph) {
                if (dist[e.start] < INF && dist[e.end] > dist[e.start] + e.weight) {
                    dist[e.end] = dist[e.start] + e.weight;
                }
            }
        }

        for (Edge e : graph) {
            if(dist[e.start] < INF && dist[e.end] > dist[e.start] + e.weight) {
                dist[e.end] = dist[e.start] + e.weight;
                negativeCycle = true;
                break;
            }
        }

        if (negativeCycle) {
            System.out.println(-1);
        }

        else {
            for (int i = 2; i < N + 1; i++) {
                if (dist[i] == INF)
                    System.out.println(-1);
                else
                    System.out.println(dist[i]);
            }
        }
    }

    static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int next, int weight) {
            this.start = start;
            this.end = next;
            this.weight = weight;
        }
    }
}
