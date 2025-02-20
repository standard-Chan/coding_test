package me.jeong.dataStructure;
/*
시간제한 2초
input 개수 : 2000
수의 범위 : 10억
하나의 수가 서로 다른 2개의 수의 합으로 표현이 가능하면 +1
예제
10
10 6 2 1 7 3 5 4 8 9

정렬이 되어있으면
10
1 2 3 4 5 6 7 8 9 10

brute force  : N*(N-1)*(N-2) = O(N^3)
2,000,000 => O(N^2) 까지 가능

방법 1
    N^2으로 모든 수의 합을 구한다. N^2
    차례대로 숫자를 검사하면서 구한 모든 수의 합에 있는지 확인한다. N^2
    => 다른 두개의 수의 합이어야하므로 해당 방식을 사용하면 안된다.
방법 2
    투 포인터의 활용
    이진 탐색처럼 정렬되어있는 자료의 특성을 활용하여 O(N)의 방식으로 탐색해보자.
    1. 일단 N개의 수를 확인해야한다. => O(N)
    2. N개의 수에 대해서 O(N)의 시간 복잡도로 찾아내야함. 여기에 맞춘다고 생각해보면, 투 포인터를
    양 끝 지점에 두고
    if (target < sum)
        end --;
    else if (target > sum)
        start ++;
    else
        cnt ++;
    을 해주면 된다.
    반복은 start == end가 될때까지.

    위 과정을 각 N에 대해서 수행해주면 된다.



 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ps1253Good {
    static int[] arr;
    static List<Integer> sum2 = new ArrayList<>();
    static Set<Integer> sum = new HashSet<>();
    static int cnt = 0;
    static int startIndex = 0;
    static int endIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        arr = new int[n];
        stringTokenizer = new StringTokenizer(bf.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(arr);

        // 탐색하기
        for (int point=0; point<n; point++) {
            // 초기설정
            startIndex = 0;
            endIndex = n-1;
            while(startIndex < endIndex) {
                if (startIndex == point){
                    startIndex ++;
                    continue;
                }
                if (endIndex == point) {
                    endIndex --;
                    continue;
                }

                int sum = arr[startIndex] + arr[endIndex];
                int target = arr[point];
                // 검사
                if (sum > target) {
                    endIndex--;
                    continue;
                } else if (sum < target) {
                    startIndex++;
                    continue;
                } else {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
