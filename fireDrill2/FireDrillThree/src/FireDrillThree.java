import java.util.Arrays;
import java.util.Scanner;

public class FireDrillThree {
    static Scanner input = new Scanner(System.in);

    public static void taskOne() {
        int [] array = new int[10];
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void taskTwo() {
        int [] array = new int[10];
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        for(int count : array){
            System.out.println(count);
        }

    }

    public static void taskThree() {
        int [] array = new int[10];
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        for(int count : array){
            System.out.print(array[count] + " ");
        }
    }

    public int[] taskFour() {
        int [] array = new int[10];
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        for(int count : array){
            if(array[count] % 2 == 0){
                System.out.print(array[count] + " ");
            }
        }
        return array;
    }

    public int[] taskFive() {
        int [] array = new int[10];
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        for(int count : array){
            if(array[count] % 2 != 0){
                System.out.print(array[count] + " ");
            }
        }
        return array;
    }

    public int taskSix() {
        int [] array = new int[10];
        int sum = 0;
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        for(int count : array){
            if(array[count] % 2 == 0){
                sum += array[count];
            }
        }
        return sum;
    }

    public int taskSeven() {
        int [] array = new int[10];
        int sum = 0;
        for(int counter = 0; counter < array.length; counter++){
            System.out.println("Enter score: ");
            int score = input.nextInt();
            array[counter] = score;
        }
        for(int count : array){
            if(array[count] % 2 != 0){
                sum += array[count];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
      //  taskOne();
        taskTwo();
    }





}
