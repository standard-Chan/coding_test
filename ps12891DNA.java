package me.jeong;

/*
4 2
GATA
(A C G T)
 1 0 0 1
GA AT TA

2pointer 알고리즘

배열 = [A, C, G, T]
start
end
1칸 이동시마다 처리
    start에 해당하는 염기 제외
    end에 해당하는 염기 추가
구체적 알고리즘
0. 초기화
    DNA [4]
    start = 0
    end = 0
    cnt = 0
1. 입력받기
2. 길이만큼 이동
    while(end < len){
        DNA 추가
    }
3.  while(end >= stringLength) {
        start 문자 -1
        start ++
        end ++
        end 문자 + 1
        if (DNA 확인 후)
            cnt ++;
    }
4. 출력
 */

/*
    알고리즘
    슬라이딩 윈도우
    => 일정 길이 고정. 오른쪽으로 한칸씩 이동하며 빠지는 염기와 들어오는 염기에 대해서 다음 적용.
        가운데는 상관이 없으므로 (즉, 그대로이므로) 계산할 필요 없고, 들어오고 빠지는 것에 대해서만 계산하면 된다.
        들어왔을 때 같을 경우 +1 : 이상이 되면 그대로.
        빠졌을 때 같을 경우 -1 : 이하가 되면 그대로.
1. 선언
start
end
myDNA
myCorrect
cnt
2. 입력받기
stringLen
targetLen
dnaNum
3. 계산
    초기 targetLen만큼 이동하여 초기 myDNA 채우기
    슬라이딩 윈도우 적용    우측으로 한칸씩 이동한다.
        1. start 이동 시
            왼쪽 문자 myDNA에서 제거 및 조건 확인 후 myCorrect -1
        2. end 이동 시
            우측 문자 myDNA에 추가 및 조건 확인 후 myCorrect +1
4. 결과 출력
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class ps12891DNA {
    static int start = 0;
    static int end = 0;
    static int[] myDNA = new int[4];
    static int [] targetDNA = new int[4];
    static int myCorrect = 0;
    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        //2. 입력받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
        int stringLen = Integer.parseInt(stringTokenizer.nextToken());
        int len = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bf.readLine());
        String DNA = stringTokenizer.nextToken();

        stringTokenizer = new StringTokenizer(bf.readLine());
        for (int i=0; i<4; i++) {
            targetDNA[i] = Integer.parseInt(stringTokenizer.nextToken());
            if (targetDNA[i]==0)
                myCorrect ++;
        }

        //3. 슬라이딩 윈도우
        // 초기 채우기
        for (; end<len; end++) {
            add(DNA.charAt(end));
        }
        end --;
        if (myCorrect == 4) {
            cnt ++;
        }
        while(end < stringLen) {
            end += 1;
            if (end >= stringLen)
                break;

            remove(DNA.charAt(start));
            start += 1;
            add(DNA.charAt(end));

            if (myCorrect == 4) {
                cnt ++;
            }
        }
        // 우측으로 한칸씩 이동
        // start +1
        // end +1
        System.out.println(cnt);
    }

    public static void add(char C) {
        switch (C) {
            case 'A':
                myDNA[0] += 1;
                if (myDNA[0] == targetDNA[0])
                    myCorrect ++;
                break;
            case 'C':
                myDNA[1] += 1;
                if (myDNA[1] == targetDNA[1])
                    myCorrect ++;
                break;
            case 'G':
                myDNA[2] += 1;
                if (myDNA[2] == targetDNA[2])
                    myCorrect ++;
                break;
            case 'T':
                myDNA[3] += 1;
                if (myDNA[3] == targetDNA[3])
                    myCorrect ++;
                break;
        }
    }
    public static void remove(char C) {
        switch (C) {
            case 'A':
                if (myDNA[0] == targetDNA[0])
                    myCorrect --;
                myDNA[0] -= 1;
                break;
            case 'C':
                if (myDNA[1] == targetDNA[1])
                    myCorrect --;
                myDNA[1] -= 1;
                break;
            case 'G':
                if (myDNA[2] == targetDNA[2])
                    myCorrect --;
                myDNA[2] -= 1;
                break;
            case 'T':
                if (myDNA[3] == targetDNA[3])
                    myCorrect --;
                myDNA[3] -= 1;
                break;
        }
    }
    /*public static void main (String[] args) throws IOException {
        int [] DNA = new int [4];
        int [] curDNA = new int [4];
        int start = 0;
        int end = 0;
        int cnt = 0;
        //1. 입력받기
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int stringLength = Integer.parseInt(stringTokenizer.nextToken());
        int len = Integer.parseInt(stringTokenizer.nextToken());

        String dnaString = bufferedReader.readLine();

        StringTokenizer stringTokenizer1 = new StringTokenizer(bufferedReader.readLine());
        for (int i=0; i<4; i++) {
            DNA[i] += Integer.parseInt(stringTokenizer1.nextToken());
        }
        //2. 초기 길이만큼 이동
        while(end < len){
            switch (dnaString.charAt(end)) {
                case 'A':
                    curDNA[0] += 1;
                    break;
                case 'C':
                    curDNA[1] += 1;
                    break;
                case 'G':
                    curDNA[2] += 1;
                    break;
                case 'T':
                    curDNA[3] += 1;
                    break;
            }
            end ++;
        }
        end --;
//        3.
        while(end < stringLength) {
            // check
            for (int i=0; i<4; i++) {
                if (curDNA[i] < DNA[i])
                    break;
                if (i == 3)
                    cnt ++;
            }
            // move
            // start point move
            switch (dnaString.charAt(start)) {
                case 'A':
                    curDNA[0] -= 1;
                    break;
                case 'C':
                    curDNA[1] -= 1;
                    break;
                case 'G':
                    curDNA[2] -= 1;
                    break;
                case 'T':
                    curDNA[3] -= 1;
                    break;
            }
            start ++;
            end ++;
            if (end >= stringLength)
                break;
            switch (dnaString.charAt(end)) {
                case 'A':
                    curDNA[0] += 1;
                    break;
                case 'C':
                    curDNA[1] += 1;
                    break;
                case 'G':
                    curDNA[2] += 1;
                    break;
                case 'T':
                    curDNA[3] += 1;
                    break;
            }
        }
        System.out.println(cnt);
    }*/
}
