package Classwork;

import java.util.Arrays;
import java.util.Scanner;

public class TaskOne {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        int sum = 0;

        for(int index = 0; index < 10; index++) {
            System.out.println("Please enter your score:");
            int score1 = input.nextInt();
            sum = sum + score1;
        }


        System.out.printf("the sum is %d", sum);


    }
}
