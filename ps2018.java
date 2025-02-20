package me.jeong;

/*
    sum => 수열의 합 = n(n+1)/2
    방법 1
        S[j]-S[i] = N 인 j와 i를 bruteforce로 구하기 -> 시간 제한 걸림
    방법 2
        연속된 수의 합들을 구해놓는다면?
        1
        12
        123
        ... 234  => 너무 많음. 시간 제한
    5 = 2+3
    7 = 3+4
    9 = 4+5
    10= 1+2+3+4, 10
    11= 5+6
    12= 3+4+5
    13= 6+7

*/

/*
    값 받기
    start, end 2개 생성
    start ~ end까지의 합구하기.
    while(end != start)
    => 1~n까지의 합 = n(n+1)/2
    => start ~ end = end(end+1)/2 - start(start+1)/2
        i) sum < target : end + 1
        ii) sum == target : end + 1, count ++
        iii) sum > target : start +1

*/
import java.util.Scanner;

public class ps2018 {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        int cnt = 1;
        int start = 1;
        int end = 2;
        int sum = 3;
        while(start < end){
            if (sum == target) {
                cnt ++;
                end ++;
                sum += end;
            } else if (sum < target) {
                end ++;
                sum += end;
            } else {
                sum -= start;
                start ++;
            }
        }

        System.out.println(cnt);
    }
}
