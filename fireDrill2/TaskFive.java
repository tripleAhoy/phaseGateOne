package Classwork;

import java.util.Scanner;

public class TaskFive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        int sum = 0;
        int count = 0;
        double average = 0;

        for(int index = 0; index < 10; index++) {
            System.out.println("Please enter your score:");
            int score1 = input.nextInt();
            if(score1 % 2 == 0) {
                sum = sum + score1;
            }

            count++;
        }

        System.out.printf("the sum of even numbers: %d", sum);

    }
}
