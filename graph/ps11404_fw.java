package me.jeong.graph;
/*
    플로이드 워셜 슈도코드
    dist[a][b] = min(dist[a][b], dist[a][k] + dist[k][b])

    초기화

    N(도시 수)
    M(에지 수)

    인접행렬 선언

    인접 행렬 INF로 초기화


    for (중간점 노드){
        for (시작 노드) {
            for (끝 노드) {
                if (거리의 합이 현재 거리보다 작을 경우 ) {
                    거리 갱신하기
                }
            }
        }
    }

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ps11404_fw {
    static int N;
    static int M;
    static long [][] graph;
    static long INF = Long.MAX_VALUE;

    static public void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new long[N+1][N+1];

        for (int i=0; i<N+1; i++) {
            for (int j=0; j<N+1; j++) {
                graph[i][j] = INF;
            }
            if (i == 0)
                continue;
            graph[i][i] = 0;
        }

        for (int i=0; i<M; i++) {
            int a, b, c;
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (graph[a][b] > c) {
                graph[a][b] = c;
            }
        }

        for (int k=1; k<= N; k++){
            for (int s=1; s<= N; s++) {
                for (int e=1; e<=N; e++) {
                    if (graph[s][k]!= INF && graph[k][e] != INF && graph[s][e] > graph[s][k] + graph[k][e]) {
                        graph[s][e] = graph[s][k] + graph[k][e];
                    }
                }
            }
        }

        for (int i=1; i<= N; i++) {
            for (int j=1; j<=N; j++) {
                if (graph[i][j] == INF) {
                    graph[i][j] = 0;
                }
                System.out.print(graph[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
