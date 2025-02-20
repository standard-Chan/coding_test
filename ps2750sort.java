package me.jeong;


import java.util.ArrayList;
import java.util.Scanner;

public class ps2750sort {
    static ArrayList<Integer> arr = new ArrayList<>();
    static int n;
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i=0; i<n; i++) {
            arr.add(scanner.nextInt());
        }
        bubbleSort();
        for (Integer i : arr ) {
            System.out.println(i);
        }
    }

    public static void bubbleSort() {
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<n-1; j++) {
                if (arr.get(j) > arr.get(j+1)) {
                    Integer temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }
        }

    }
}

