import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class StudentGradesApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many students do you have?");
		int numStudents = getPositiveInput(input);
		System.out.println("\nHow many subjects do they offer?");
		int numSubjects = getPositiveInput(input);
		 System.out.println("Saved successfully\n");

		String[] studentNames = new String[numStudents];
		int[][] studentGrades = new int[numStudents][numSubjects];

		collectStudentData(input, studentNames, studentGrades);
		displayClassReport(studentNames, studentGrades);
	}

	public static int getPositiveInput(Scanner input) {
		int value;
		do {
			System.out.print("Enter a positive integer: ");
		while (!input.hasNextInt()) {
			System.out.print("Invalid input. Enter a positive integer: ");
			input.next();
		}
		value = input.nextInt();
		input.nextLine(); // consume newline
		} while (value <= 0);
		return value;
	}

	public static void collectStudentData(Scanner input, String[] studentNames, int[][] studentGrades) {
		for(int count = 0; count < studentNames.length; count++) {
			System.out.print("Enter name for student " + (count+1) + ": ");
			studentNames[count] = input.next();
		
			for(int index = 0; index < studentGrades[count].length; index++ ) {
				System.out.printf("Enter score for %s in subject %d (0-100): ", studentNames[count], index+1 );
				studentGrades[count][index] = getValidScore(input);
			}
		}
	}

	public static int getValidScore(Scanner input) {
		int score = input.nextInt();
		while(score < 0 || score > 100) {
			System.out.print("Invalid score! Enter a value between (0-100): ");
			score = input.nextInt();
		}
		return score;
	}

	public static void displayClassReport(String[] studentNames, int[][] studentGrades) {
		int numStudents = studentNames.length;
		int numSubjects = studentGrades[0].length;

		int[] totals = calculateTotals(studentGrades);
		double[] averages = calculateAverages(totals, numSubjects);
		int[] positions = calculatePositions(totals);

		printReportHeader(numStudents, numSubjects);
		printStudentResults(studentNames, studentGrades, totals, averages, positions);
		printSubjectSummaries(studentNames, studentGrades);
		printClassStatistics(studentNames, studentGrades, totals);
	}

	public static int[] calculateTotals(int[][] studentGrades) {
		int[] totals = new int[studentGrades.length];
		for (int count = 0; count < studentGrades.length; count++) {
			for (int grade : studentGrades[count]) {
				totals[count] += grade;
			}
		}
		return totals;
	}

	public static double[] calculateAverages(int[] totals, int numSubjects) {
		double[] averages = new double[totals.length];
		for (int count = 0; count < totals.length; count++) {
			averages[count] = (double) totals[count] / numSubjects;
		}
		return averages;
	}

	public static int[] calculatePositions(int[] totals) {
		Integer[] indices = new Integer[totals.length];
		for (int count = 0; count < totals.length; count++) {
			indices[count] = count;
		}
        
		Arrays.sort(indices, Comparator.comparingInt(count -> -totals[count]));
        
		int[] positions = new int[totals.length];
		int currentRank = 1;

		for (int count = 0; count < indices.length; count++) {
			if (count > 0 && totals[indices[count]] < totals[indices[count-1]]) {
				currentRank = count + 1;
			}
			positions[indices[count]] = currentRank;
		}
		return positions;
	}

	public static void printReportHeader(int numStudents, int numSubjects) {
		System.out.println("\nClass Summary Report");
		System.out.println("====================");
		System.out.printf("Number of Students: %d%n", numStudents);
		System.out.printf("Number of Subjects: %d%n", numSubjects);
		System.out.println();

		System.out.printf("Student Name\t\t");
		for (int counter = 0; counter < numSubjects; counter++) {
			System.out.printf("Subject %d\t", counter+1);
		}
		System.out.println("Total\tAverage\tPosition");
	}

	public static void printStudentResults(String[] studentNames, int[][] studentGrades, int[] totals, double[] averages, int[] positions) {
		for (int i = 0; i < studentNames.length; i++) {
			System.out.print(studentNames[i] + "\t\t");
			for (int grade : studentGrades[i]) {
				System.out.print(grade + "\t");
			}
			System.out.printf("%d\t%.2f\t%d%n", totals[i], averages[i], positions[i]);
		}
	}

	
	public static void printSubjectSummaries(String[] studentNames, int[][] studentGrades) {
		System.out.println("\nSubject Summary");
		System.out.println("===============");

		for (int subject = 0; subject < studentGrades[0].length; subject++) {
			int highestScore = Integer.MIN_VALUE;
			int lowestScore = Integer.MAX_VALUE;
			String highestScorer = "";
			String lowestScorer = "";
			int total = 0;
			int passes = 0;
			int fails = 0;

			for (int student = 0; student < studentGrades.length; student++) {
				int score = studentGrades[student][subject];
				total += score;
            
				if (score > highestScore) {
					highestScore = score;
					highestScorer = studentNames[student];
				}
				if (score < lowestScore) {
					lowestScore = score;
					lowestScorer = studentNames[student];
				}
			if (score >= 50) passes++;
			else fails++;
		}

			double average = (double) total / studentGrades.length;

			System.out.println("Subject " + (subject + 1));
			System.out.println("Highest scoring student is: " + highestScorer + " (" + highestScore + ")");
			System.out.println("Lowest scoring student is: " + lowestScorer + " (" + lowestScore + ")");
			System.out.println("Total score: " + total);
			System.out.printf("Average score: %.2f%n", average);
			System.out.println("Number of passes: " + passes);
			System.out.println("Number of fails: " + fails);
			System.out.println("---------------------");
		}
	}

	public static void printClassStatistics(String[] studentNames, int[][] studentGrades, int[] totals) {
		System.out.println("\nClass Statistics");
		System.out.println("================");

		int maxFails = -1;
		int minFails = Integer.MAX_VALUE;
		int hardestSubject = -1;
		int easiestSubject = -1;
    
		for (int subject = 0; subject < studentGrades[0].length; subject++) {
			int fails = 0;
			for (int student = 0; student < studentGrades.length; student++) {
				if (studentGrades[student][subject] < 50) {
					fails++;
				}
			}
			if (fails > maxFails) {
				maxFails = fails;
				hardestSubject = subject;
			}
			if (fails < minFails) {
				minFails = fails;
				easiestSubject = subject;
			}
		}
    
		System.out.printf("The hardest subject is Subject %d with %d failures%n", 
			hardestSubject + 1, maxFails);
		System.out.printf("The easiest subject is Subject %d with %d passes%n", 
                     easiestSubject + 1, studentGrades.length - minFails);
    
		int overallHighest = -1;
		int overallLowest = 101;
		String highestScorer = "";
		String lowestScorer = "";
		int highestSubject = -1;
		int lowestSubject = -1;
   
		for (int student = 0; student < studentGrades.length; student++) {
			for (int subject = 0; subject < studentGrades[student].length; subject++) {
				int score = studentGrades[student][subject];
				if (score > overallHighest) {
					overallHighest = score;
					highestScorer = studentNames[student];
					highestSubject = subject;
				}
				if (score < overallLowest) {
					overallLowest = score;
					lowestScorer = studentNames[student];
					lowestSubject = subject;
				}
			}
		}
    
		System.out.printf("The overall highest score is scored by %s in Subject %d (%d)%n", highestScorer, highestSubject + 1, overallHighest);
		System.out.printf("The overall lowest score is scored by %s in Subject %d (%d)%n", lowestScorer, lowestSubject + 1, overallLowest);
  
		System.out.println("\nClass Summary");
		System.out.println("=============");
    
		int bestStudentIndex = 0;
		int worstStudentIndex = 0;
		for (int i = 1; i < totals.length; i++) {
			if (totals[i] > totals[bestStudentIndex]) {
				bestStudentIndex = i;
			}
			if (totals[i] < totals[worstStudentIndex]) {
				worstStudentIndex = i;
			}
		}
    
		System.out.printf("Best graduating student is %s with total score %d%n", studentNames[bestStudentIndex], totals[bestStudentIndex]);
		System.out.printf("Worst graduating student is %s with total score %d%n", studentNames[worstStudentIndex], totals[worstStudentIndex]);
    

		int classTotal = Arrays.stream(totals).sum();
		double classAverage = (double) classTotal / (studentGrades.length * studentGrades[0].length);
    
		System.out.println("Class total score: " + classTotal);
		System.out.printf("Class average score: %.2f%n", classAverage);
	}

}