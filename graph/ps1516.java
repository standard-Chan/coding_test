package me.jeong.graph;
/*
    위상 정렬 : 게임 개발
    설명
        동시에 건설 가능
    입력
    N
    시간 -1
    시간 전제 조건 -1   (끝이 -1)

    전제 조건을 다 완료 해야 건물을 지을 수 있으므로 위상 정렬 알고리즘을 사용한다.

    위상정렬 알고리즘

    다음 2가지 초기 배열을 설정하여 저장한다.
    건물 건설 시 전제 조건을 완료하는 건물
    1 : 2 3 4
    2 : 3 4
    3 :
    4 : 3

    ㅇ건물 번호 : 1 2 3 4
    전제조건 수 : 0 1 3 2

    우선 전제 조건 수가 0인 번호부터 완료한다. 완료 후에는 구별할 수 있도록 -1로 표기한다.
     1 2 3 4
     0 1 3 2

     1번 완료
      1 2 3 4
     -1 0 2 1

     2번 완료
      1  2 3 4
     -1 -1 1 0

     4번 완료
      1  2 3  4
     -1 -1 0 -1

     3번 완료
     1 2 3 4
    -1-1-1-1


    문제 테스트

    5
    10 -1
    5 1 -1
    7 1 -1
    8 1 2 3 -1
    9 1 2 3 -1

    outNode
    1 2 3 4 5
    2
    3
    4 2 3
    5 2 3

    indegree 계산            초 10 5  7  8  9
    1 2 3 4 5               초 1  2  3  4  5    완료
    0 1 1 3 3               초 0  0  0  0  0
    0 0 0 2 2               초 10 0  0  0  0     1
    0 0 0 1 1               초 10 15 0  0  0     2
    0 0 0 0 0               초 10 15 17 0  0     3
    0 0 0 0 0               초 10 15 17 25 0     4
    0 0 0 0 0               초 10 15 17 25 26    5

    건물을 짓는 데에 걸리는 시간 = 먼저 지어야하는 건물 중 가장 오래걸리는 시간 + 건설 시간
    진입차수로 지을 수 있는 건물 파악
    지을 수 있는 건물중 먼저 지어야 하는 건물 최대 건설시간

    슈도 코드
    ArrayList  outNode (진출 노드 정보)
    ArrayList inNode  (먼저 지어야하는 건물)
    배열 indegree(진입 차수)
    배열 time(건설 시간)
    배열 totalTime(총 소요 시간)

    for (node 개수) {
        for (indegree 순회) {
            if (indegree == 0) {
                for (inNode 순회)
                    totalTime = Max(해당 노드의 먼저 지어야하는 건물 totalTime) + 현재 건물 건설 시간
                for (outNode 순회) {
                    indegree --;
                }
            }
        }
    }


    for (node 개수) {
        for (indegree 순회) {
            if (진입차수 == 0) {
                for (a : outNode[해당 노드]) {
                    indegree[a] -= 1;
                }
                break;
            }
        }
    }

 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ps1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] time = new int[N+1];
        int[] totalTime = new int[N+1];
        int[] indegree = new int[N+1];
        ArrayList<ArrayList<Integer>> outNode = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> preCondition = new ArrayList<ArrayList<Integer>>();

        // List 초기화
        for (int i=0; i<N+1; i++) {
            preCondition.add(new ArrayList<Integer>());
            outNode.add(new ArrayList<Integer>());
        }

        for (int i=1; i<N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            // 건설 시간 설정
            time[i] = Integer.parseInt(st.nextToken());

            // 전제 건물 설정
            while(true) {
                int a = Integer.parseInt(st.nextToken());
                if (a==-1) {
                    break;
                }
                outNode.get(a).add(i);
                preCondition.get(i).add(a);
                indegree[i] ++;
            }
        }

        for (int i=1; i<N+1; i++) {
            for (int j=1; j<N+1; j++) {
                if (indegree[j] == 0) {
                    indegree[j] = -1;
                    totalTime[j] = time[j];
                    int maxTime = 0;
                    for (int k=0; k<preCondition.get(j).size(); k++) {
                        if (totalTime[preCondition.get(j).get(k)] > maxTime) {
                            maxTime = totalTime[preCondition.get(j).get(k)];
                        }
                    }
                    totalTime[j] += maxTime;

                    for (int k=0; k<outNode.get(j).size(); k++) {
                        indegree[outNode.get(j).get(k)] --;
                    }
                }
            }
        }

        for (int i=1; i<N+1; i++) {
            System.out.println(totalTime[i]);
        }
    }
}

/*
    5
    8 2 3 4 -1
    10 -1
    5 2 -1
    7 2 -1
    9 2 3 4 -1
 */