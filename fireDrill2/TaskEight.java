package Classwork;

import java.util.Scanner;

public class TaskEight {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        int sum = 0;
        int count = 0;
        double average = 0;
        int validScoreCount = 0;


        do{
            System.out.println("Please enter your score (0-100):");
            score = input.nextInt();
            if(score >= 0 && score <= 100){
                validScoreCount++;
            }

        } while(validScoreCount <= 10 );



        System.out.printf("the sum of valid scores: %d%n", sum);


    }
}
