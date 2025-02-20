package me.jeong.graph;
/*
    !! 시간 오래걸렸는데, 경로 압축을 안했음!!!!!!
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    여행 가자
    시간제한 : 2

    해당 경로가 가능한 것인지 파악

    A-B, B-C, A-D, B-D, E-A
    E C B C D : 경로를 통해 이동할 수 있으면 된다.

      A B C D E
    A 0 1 0 1 1
    B 1 0 1 1 0
    C 0 1 0 0 0
    D 1 1 0 0 0
    E 1 0 0 0 0

    A B C D E
    A B C D E
    A A C D E
    A A C A E
    A A C A A
    A A A A A
    여행이 가능하다 = 두 지역이 연결되어있다.
    UNION FIND => 마지막 줄에 받은 수가 모두 1개의 parent로 연결되어있으면 YES 출력

    <슈도 코드>
    N(도시수 및 입력 수), M(여행 계획에 속한 도시 수)
    인접 그래프 형태로 입력이 주어짐

    for (int y=1; y<=N; y++) {
        for (int x=1; x<=N; x++) {
            if (0이면)
                그대로
            if (1이면)
                y와 x를 연결한다. UNION 시킨다.
        }
    }

    UNION

    FIND

    5
    3
    0 1 0 1 1
    1 0 1 1 0
    0 1 0 0 0
    1 1 0 0 0
    1 0 0 0 0
    1 5 2

    3
    3
    0 0 1
    0 0 0
    1 0 0
    1 2 3

    3
    3
    1 1 1
    1 1 1
    1 1 1
    1 2 3

    4
    3
    0 0 1 0
    0 0 0 0
    1 0 0 0
    0 0 0 0
    1 3 2

    5
    5
    0 1 0 0 0
    1 0 0 0 1
    0 0 0 1 0
    0 0 1 0 1
    0 1 0 1 0
    3 5 4 2 1

      A B C D E
    A 0 1 0 0 0
    B 1 0 0 0 1
    C 0 0 0 1 0
    D 0 0 1 0 1
    E 0 1 0 1 0

    A B C D E
    1 2 3 4 5
    1 1 3 4 2
    1 1 3 3 2
    1 1 1 3 2
    1 1 1 3 2






 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ps1976 {
    static int[] parent;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<ArrayList<Integer>>();
        parent = new int[n];

        for (int i=0; i< parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            graph.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                int v = graph.get(y).get(x);
                if (v == 1) {
                    union(y, x);
                }
            }
        }

        int a;
        boolean result = true;
        st = new StringTokenizer(bf.readLine());
        a = find(parent[Integer.parseInt(st.nextToken())-1]);
        for (int i=1; i<m; i++) {
            if (a != find(parent[Integer.parseInt(st.nextToken())-1])){
                result = false;
                break;
            }
        }
        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void union(int node1, int node2)

    {
        int a = find(node1);
        int b = find(node2);
        if (a > b) {
            parent[a] = b;
        } else if (a < b)
            parent[b] = a;
    }

    public static int find(int node) {
        if (node == parent[node])
            return node;

        return find(parent[node]);
    }
}
