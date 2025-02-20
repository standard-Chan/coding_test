package me.jeong.search;
/*
10,000,000,000
O(N)으로 검색해야한다.
1. 이진탐색으로 구하기
    M개의 수에 대해서 :O(M) * 이진탐색 : O(logN) + 각각 정렬 O(NlogN)

    <손으로 풀어보기>
    4 1 5 2 3
    1 3 7 9 5

    정렬
    1 2 3 4 5
    1 3 5 7 9

    7찾아보기
    s = 1
    e = 5
    m = 3

    s = 4
    e = 5
    m = 4

    s = 5
    e = 5
    m = 5

    s = 6
    종료 조건 start > end

    M개 수 탐색
    1
    중앙값 : (1+5)/2 = [2] = 3
    작으므로 e = 2
    중앙값 (1+2)/2 = [1] = 1  => OK

    슈도코드
    N(타겟 길이), M(입력 길이)
    target 배열 선언
    입력 배열 선언

    for (입력 배열 요소에 대해서) {
        start = 0
        end = N
        while(start > end) {
            mid = (start + end)/2
            if (값이 mid보다 작다)
                end = mid-1;
            if 값이 mid보다 크다
                start = mid+1;
            else
                존재한다고 배열에 추가
                break;
        }
    }

    출력




2. 투 포인터로 구하기
    정렬 : O(NlogN) + 검색 :O(2N)

    <손으로 풀어보기>



 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ps1920 {
    public static void main(String[] args) throws IOException {
        int n, m;
        boolean[] exists;
        ArrayList<Integer> targetArr = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bf.readLine());
        while(stringTokenizer.hasMoreTokens()) {
            targetArr.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        stringTokenizer = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bf.readLine());
        while(stringTokenizer.hasMoreTokens()) {
            arr.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        exists = new boolean[m];
        // 정렬
        Collections.sort(targetArr);

        // 각각의 요소에 대해서 실행
        for (int i=0; i<arr.size(); i++) {
            Integer value = arr.get(i);
            int start = 0;
            int end = n-1;
            while(start <= end) {
                int mid = (start + end)/2;
                if (value < targetArr.get(mid)) {
                    end = mid - 1;
                } else if (value.equals(targetArr.get(mid))) {
                    exists[i] = true;
                    break;
                } else {
                    start = mid + 1;
                }
            }
        }

        for (boolean b : exists) {
            if (b)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
