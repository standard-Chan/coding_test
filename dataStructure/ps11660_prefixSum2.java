package me.jeong.dataStructure;
/*
    구간합 알고리즘


    숫자 최대값 : 1,000,000,000 => int로 커버  가능
    브루트포스 : 100,000,000,000 연산

    예제
    1 2 3 4
    2 3 4 5
    3 4 5 6
    4 5 6 7

    1  3  6  10
    3  8  15 24

    세로축이 x임!!!

    슈도코드

    초기화
    N(표 크기), M(연산 횟수)
    map(표)
    sumMap(구간합 표)

    for(N){
        map 초기화
    }

    //prefixSumMap 초기화
    for (N번) {
        for (N번) {
            if (x == 0) {

            }
            else if (y == 0) {
            }
            else {
                sumMap[x][y] = sumMap[x-1][y] + sumMap[x][y-1] - sumMap[x-1][y-1] + map[x][y];
            }
        }
    }

    계산값 구하기
    x1,y1 ~ x2,y2
    sumMap[x2][y2] - sumMap[x2][y1-1] - sumMap[x1-1][y2] + sumMap[x1-1][y1-1]
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ps11660_prefixSum2 {

    static int N, M;
    static int[][] map;
    static int[][] sumMap;

    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        sumMap = new int[N+1][N+1];

        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(bf.readLine());
            for (int j=1; j<N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //prefixSumMap 초기화
        for (int x=1; x<N+1; x++) {
            for (int y=1; y<N+1; y++) {
                sumMap[x][y] = sumMap[x-1][y] + sumMap[x][y-1] - sumMap[x-1][y-1] + map[x][y];
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x1, y1, x2, y2;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            System.out.println(sumMap[x2][y2] - sumMap[x2][y1-1] - sumMap[x1-1][y2] + sumMap[x1-1][y1-1]);
        }
    }
}
