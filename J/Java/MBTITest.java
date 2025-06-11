import java.util.Scanner;

public class MBTITest {

	static String[][] questions = {
		{"I prefer group activities", "I prefer one-on-one conversations", "EI"},
		{"I enjoy being the center of attention", "I prefer being more reserved", "EI"},
		{"I feel energized after socializing", "I feel drained after socializing", "EI"},
		{"I speak more than I listen", "I listen more than I speak", "EI"},
		{"I act first and think later", "I think first and then act", "EI"},

		{"I trust experience", "I trust inspiration", "SN"},
		{"I like concrete facts", "I like abstract theories", "SN"},
		{"I focus on what is real", "I focus on possibilities", "SN"},
		{"I like to live in the present", "I like to imagine the future", "SN"},
		{"I remember specific facts", "I remember impressions or patterns", "SN"},

		{"I make decisions with logic", "I make decisions with my heart", "TF"},
		{"I value justice", "I value empathy", "TF"},
		{"I critique to help", "I avoid hurting others", "TF"},
		{"I am task-oriented", "I am people-oriented", "TF"},
		{"I prefer consistency", "I prefer harmony", "TF"},

		{"I like to plan and organize", "I like to stay flexible", "JP"},
		{"I prefer clear structures", "I enjoy spontaneity", "JP"},
		{"I like routines", "I go with the flow", "JP"},
		{"I complete tasks ahead of time", "I work best under pressure", "JP"},
		{"I need closure", "I can handle open-ended situations", "JP"}
	};

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int eiA = 0, snA = 0, tfA = 0, jpA = 0;

		System.out.println("Welcome to the MBTI Personality Test (Java Console Version)");
		System.out.println("Please respond with A or B:\n");

		for (int i = 0; i < questions.length; i++) {
		String group = questions[i][2];
		String answer = "";

			do {
				System.out.printf("Q%d: A. %s\n    B. %s\nYour answer (A/B): ", i + 1, questions[i][0], questions[i][1]);
				answer = scanner.nextLine().trim().toUpperCase();
			} while (!answer.equals("A") && !answer.equals("B"));

				if (answer.equals("A")) {
					switch (group) {
						case "EI": eiA++; break;
						case "SN": snA++; break;
						case "TF": tfA++; break;
						case "JP": jpA++; break;
					}
				}
			}

		String personality =
		(eiA > 2 ? "E" : "I") +
		(snA > 2 ? "S" : "N") +
		(tfA > 2 ? "T" : "F") +
		(jpA > 2 ? "J" : "P");

		System.out.println("\nYour MBTI Type: " + personality);

		scanner.close();
	}
}
