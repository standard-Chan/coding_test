package me.jeong.Math;

/*
소수 구하기

방법 1 : 에라토스테네스의 체 => O(NlogN)?
방법 2 : 각 정수마다 2~N으로 나누기. => O(N^2)

에라토스테네스의 체
슈도코드
start(시작 수)
end (끝 수)
primary[end+1]

for ( start ~ end ){
    if (primary) {
        for (1 ~ end/현재수) {
            primary[현재 * 현재2] = false;
        }
    }
}

for (primary)
    if true
        cnt ++

출력
 */

import java.util.Scanner;

public class ps1929 {
    public static void main(String[] args) {
        int start, end;
        Scanner scanner = new Scanner(System.in);
        start = scanner.nextInt();
        end = scanner.nextInt();

        boolean[] primary = new boolean[end+1];

        for (int i=2; i< primary.length; i++) {
            primary[i]=true;
        }

        for (int i = 2; i< end+1; i++) {
            if (primary[i]) {
                for (int j=2; i*j<=end; j++) {
                    primary[i*j] = false;
                }
            }
        }
        for (int i=start; i< primary.length; i++){
            if (primary[i]) {
                System.out.println(i);
            }
        }

    }
}
