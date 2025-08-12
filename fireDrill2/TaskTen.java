package Classwork;

import java.util.Scanner;

public class TaskTen {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        double average = 0;

        for(int index = 0; index < 10; index++) {
            System.out.println("Please enter your score (0-100):");
            int score1 = input.nextInt();
            if(score1 >= 0 && score1 <= 100 ) {
                sum = sum + score1;
                count++;
            }

        }
        average = (double) sum / count;
        //System.out.printf("the sum of valid scores: %d%n", sum);
        System.out.printf("the average of valid scores: %.2f", average);

    }
}
