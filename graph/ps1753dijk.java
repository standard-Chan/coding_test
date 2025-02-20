package me.jeong.graph;
/*
    최단 경로 구하기

    출발 노드를 우선순위 큐에 넣기
    while(큐가 빌때까지) {
        현재 노드가 방문한 적이 있는지 확인하기
        현재 노드 방문처리
        for (노드와 연결된 에지수) {
            if (연결 노드가 방문X && 연결 노드의 거리 > 현재 노드 거리 + 에지 거리) {
                연결 노드 거리 업데이트
                우선순위 큐에 넣기
            }
        }
    }

    다익스트라 알고리즘 슈도코드
    출발 노드를 우선순위 큐에 넣기
    while(큐가 빌때까지) {
        현재 선택된 노드가 방문한 적이 있는 지 확인하기
        방문 노드로 업데이트하기
        for (현재 선택 노드 에지의 개수) {
            if (타겟 방문 전 && 현재 거리 + 가중치 < 타겟 거리) {
                타깃 노드 최단거리로 업데이트 하기
                우선순위 큐에 타킷 노드 추가하기.
            }
        }
    }

    문제 슈도코드

    자료구조 선언하기 (그래프, 노드(타깃, 비용), 최단거리, 방문여부)
    V(정점), E(간선)
    start (시작 노드)
    최단 거리 배열은 충분히 큰 수로 초기화
    for (노드 개수)
        그래프 초기화
    for (노드 개수)
        인접 리스트 배열에 에지 정보 저장
    <다익스트라 알고리즘 수행하기>
    우선순위 큐에 시작 노드 넣기
    while(큐가 빌 때까지) {
        현재 노드가 방문했는지 확인
        현재 노드를 방문 상태로 업데이트
        for (에지 수) {
            if (타깃 노드 방문 전 && 현재 거리 + 에지 < 타깃 노드 최단거리){
                최단 거리 업데이트하기
                에지와 연결된 노드 우선순위 큐에 넣기
            }
        }
    }

    최단 경로값 출력하기

    Edges 구현
        vertex
        edges

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ps1753dijk {
    final static int INF = 99999999;

    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
        int[] distance;
        boolean[] visited;
        int V, E, start;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());

        distance = new int[V+1];
        visited = new boolean[V+1];

        for (int i=0; i<V+1; i++) {
            distance[i] = INF;
        }

        for (int i=0; i<V+1; i++) {
            graph.add(new ArrayList<Edge>());
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u, v, w;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v,w));
        }

        PriorityQueue<Edge> q = new PriorityQueue<Edge>();

        q.add(new Edge(start, 0));
        distance[start] = 0;
        while(!q.isEmpty()) {
            Edge now = q.poll();
            if (visited[now.vertex]) {
                continue;
            }
            visited[now.vertex] = true;
            for (int i=0; i< graph.get(now.vertex).size(); i++) {
                Edge next = graph.get(now.vertex).get(i);
                if (!visited[next.vertex] && distance[now.vertex] + next.weight < distance[next.vertex]) {
                    distance[next.vertex] = distance[now.vertex] + next.weight;
                    q.add(new Edge(next.vertex, distance[next.vertex]));
                }
            }
        }


        for (int i=1; i<V+1; i++) {
            if (distance[i] >= INF)
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }


    }

    static class Edge implements Comparable<Edge>{
        public int vertex;
        public int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            if (this.weight > e.weight) return 1;
            return -1;
        }
    }
}
