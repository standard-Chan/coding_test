package me.jeong.greedy;
/*
최솟값을 만들어야함.
1+2+3+4+5+6 => +만 있는 경우는 없어도 상관 없다
1+2+3+4-5+6 => -의 값을 최대한 크게 만들어야함.

브루트포스로 풀어보자.
- 지점 이후로 -가 나올때까지 수의 값을 모두 합치면 된다.
1+2+3-4+3-6+2-8 이라고 하면 구간을 설정
1+2+3-(4+3)-(6+2)-8 = -17

-를 기준으로 파싱
[1+2+3, 4+3, 6+2, 8]
모두 더한다.
[6,7,8,8]
index[0]에서 나머지를 모두 뺀다
-17
경우
i) + - -
3+2-1-4
5 1 4

ii) - - +
1-2-3+4
1 2 3+4
iii) ---
1 2 3 4
iv) + + +
1+2+3+4
v) - + -
1-2+3-4
1 2+3 4

vi) 00009 - 00009 + 00003
00009 00009 + 00003

슈도코드
입력 String을 받는다.
'-'를 기준으로 parsing한다.
파싱된 문자 수식을 계산한다.
for (파싱된 길이){
    +를 기준으로 파싱하여 arr 에 저장
    for (arr 길이) {
        파싱된 문자열을 숫자로 변환한다.
    }
    arr의 합을 구하여 저장
}
index[0] 에서 나머지를 뺀다.
출력

특이점
00008 인경우 => 8 로 변환한다.


 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ps1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String string = bf.readLine();

        String[] splitByMinus = string.split("-");
        ArrayList<Integer> numArr = new ArrayList<>();

        for (String a : splitByMinus) {
            int sum = 0;
            String[] splitByPlus = a.split("\\+");

            for (String b : splitByPlus) {
                sum += Integer.parseInt(b);
            }
            numArr.add(sum);
        }
        int result = numArr.get(0);
        for (int i=1; i<numArr.size(); i++) {
            result -= numArr.get(i);
        }
        System.out.println(result);
    }
}
