package me.jeong.graph;
/*
    UNION & FIND 연산

    UNION
    1 2 3 4 5
    1 2 3 4 5
    연결 : 1-2, 3-4 2-5, 5-4
    작은수를 root로 연결하자.
        1 2 3 4 5
    1 : 1 1 3 4 5
    2 : 1 1 3 3 5
    3 : 1 1 3 4 1
    4 : 1 1 1 3 1   : 대표노드인 3과 1을 연결하였다.

    조건 :
        1-작은수를 대표노드로 설정한다.
        2-연결하는 두 노드의 value가 대표노드가 아닐 경우, 대표노드로 타고 올라가서 대표노드를 연결한다.

    < 슈도코드 >
    n (n+1개의 노드), m (연산 개수)
    node 정보 저장 (길이 : n+1)

    함수
        1 - 두 노드를 연결하는 함수
            i) a:a, b:b 인경우
                if a>b
                    b의 value = a
                if a<b
                    a의 value = b
                if a == b
                    변화없음
            ii) a:a, c:b 인 경우
                대표노드를 찾는다. c:b => b:b
                if b > a
                    b의 value = a
                if b<a
                    a의 value = b
            iii) a:d, c:b 인 경우 (d->a, b
                a:d => 타고 올라가서 대표노드를 찾는다.
                c:b => 타고 올라가서 대표노드를 찾는다.
                찾은 대표 노드끼리 i)를 적용한다.

            union (node1, node2)
                a (대표노드1) = find(node1)
                b (대표노드2) = find(node2)
                if a>b
                    b의 value = a
                if a<b
                    a의 value = b
                if a == b
                    변화없음

        2 - find 대표노드를 반환해주는 함수 (index)
                if (index == value[index])
                    return index;   // 대표노드 반환
                return find(value[index]);

        1 2 3 4 5 6 7
        1 2 1 4 5 6 7  no
        1 2 1 4 5 6 6  no
        1 2 1 4 5 1 6
        1 2 1 2 5 1 6
        1 2 1 2 5 1 6

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class ps1717 {
    static int[] node;
    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        node = new int[n+1];
        for (int i=0; i< node.length; i++) {
            node[i] = i;
        }


        for (int i=0; i<m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 1 인경우 검색
            if (a == 1) {
                if (find(b) == find(c)) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
            // 0 인경우 union
            if (a == 0) {
                union(b, c);
            }
        }
    }

    public static void union (int node1, int node2) {
        int a = find(node1);
        int b = find(node2);
        if (a > b) {
            node[b] = a;
        } else if (a < b) {
            node[a] = b;
        }
    }

    public static int find(int index) {
        if (index == node[index]) {
            return index;   // 대표노드 반환
        }
        return find(node[index]);
    }
}
