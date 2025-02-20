package me.jeong.graph;
/*
    최소 스패닝 트리

    예제 손풀

    시작 1 2 1
    ㅡ끝 2 3 3
    가중 1 2 3

    union find
    1 2 3
    1 2 3

    1 2 3
    1 1 3

    1 2 3
    1 1 1


    주의사항!
        Integer.Max_v < 간선의 가중치 < Intger.Max_v
        weight가 음수일 수 있다.
           ==> 결과값은 long으로 저장





    최소 스패닝 트리 슈도코드
    V(정점 수), E(간선 수)
    long total
    parent
    graph(에지 저장)

    정렬된 에지 그래프
    for (에지 개수) {
        if(에지로 연결된 두 노드의 부모가 같을 경우) {
            건너 뛰기
        }

        에지로 연결된 두 노드를 union
        총 가중치의 합에 가중치 더하기
    }

    class Edge {
        시작노드, 끝노드, 가중치
    }

    find(현재 노드) {
        if (현재노드 == 부모 노드)
            return 현재노드
        return find(현재노드)
    }

    union(노드1, 노드2) {
        if (root 부모가 다른 경우)
        노드2의 부모 = 노드1의 부모
    }

       1 -- 5

    2    3    4

5 6
1 2 2
2 3 1
1 3 4
1 4 1
4 3 2
3 5 7


다음의 경우 시간 초과발생함.
3 4 연결
3 2 연결
4 1 연결
이렇게 되면 4번의 부모 노드인 2번까지 올라가서 2번에서 1번노드로 부모를 바꿔야해서 시간이 오래걸린다.
따라서 부모노드가 생성됨과 동시에 따라 올라가면서 발생하는 모든 애들의 부모를 바꾸도록 하자.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ps1197_spannigTree {
    static int V, E;
    static long totalWeight;
    static int[] parent;
    static PriorityQueue<Edge> graph = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i=0; i<E; i++) {
            int a,b,c;
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.add(new Edge(a,b,c));

        }

        // parent 초기화
        parent = new int[V+1];
        for (int i=0; i<V+1; i++) {
            parent[i] = i;
        }

        for (int i=0; i<E; i++) {
            Edge e = graph.poll();
            if (find(parent[e.start]) == find(parent[e.end]))
                continue;
            union(e.start, e.end);
            totalWeight += e.weight;
        }
//        for (에지 개수) {
//            if (에지로 연결된 두 노드의 부모가 같을 경우){
//                건너 뛰기
//            }
//
//            에지로 연결된 두 노드를 union
//            총 가중치의 합에 가중치 더하기
//        }
        System.out.println(totalWeight);
    }
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        Edge(int a, int b, int w) {
            this.start = a;
            this.end = b;
            this.weight = w;
        }
        @Override
        public int compareTo (Edge e) {
            return this.weight - e.weight;
        }
    }

    static int find(int cur) {
        if (cur == parent[cur])
            return cur;
        return (parent[cur] = find(parent[cur]));
    }

    static void union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);
        if (p1 < p2) {
            parent[p2] = parent[p1];
        } else {
            parent[p1] = parent[p2];
        }
    }
}
