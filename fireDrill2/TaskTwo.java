package Classwork;

import java.util.Scanner;

public class TaskTwo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        int sum = 0;
        int count = 0;
        double average = 0;

        for(int index = 0; index < 10; index++) {
            System.out.println("Please enter your score:");
            int score1 = input.nextInt();
            sum = sum + score1;
            count++;
        }

        average = (double) sum / count;


        System.out.printf("the averrage is %f", average);


    }
}
