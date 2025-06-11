const readline = require("readline");

const questions = [
  ["I prefer group activities", "I prefer one-on-one conversations", "EI"],
  ["I enjoy being the center of attention", "I prefer being more reserved", "EI"],
  ["I feel energized after socializing", "I feel drained after socializing", "EI"],
  ["I speak more than I listen", "I listen more than I speak", "EI"],
  ["I act first and think later", "I think first and then act", "EI"],
  ["I trust experience", "I trust inspiration", "SN"],
  ["I like concrete facts", "I like abstract theories", "SN"],
  ["I focus on what is real", "I focus on possibilities", "SN"],
  ["I like to live in the present", "I like to imagine the future", "SN"],
  ["I remember specific facts", "I remember impressions or patterns", "SN"],
  ["I make decisions with logic", "I make decisions with my heart", "TF"],
  ["I value justice", "I value empathy", "TF"],
  ["I critique to help", "I avoid hurting others", "TF"],
  ["I am task-oriented", "I am people-oriented", "TF"],
  ["I prefer consistency", "I prefer harmony", "TF"],
  ["I like to plan and organize", "I like to stay flexible", "JP"],
  ["I prefer clear structures", "I enjoy spontaneity", "JP"],
  ["I like routines", "I go with the flow", "JP"],
  ["I complete tasks ahead of time", "I work best under pressure", "JP"],
  ["I need closure", "I can handle open-ended situations", "JP"]
];

function calculateMBTI(answers) {
  const scores = { EI: 0, SN: 0, TF: 0, JP: 0 };

  for (let i = 0; i < 20; i++) {
    const choice = answers[i].toUpperCase();
    const dim = questions[i][2];
    if (choice === "A") {
      scores[dim]++;
    } else if (choice !== "B") {
      throw new Error(`Invalid answer at Q${i + 1}: Must be A or B`);
    }
  }

  return (scores.EI > 2 ? "E" : "I") +
         (scores.SN > 2 ? "S" : "N") +
         (scores.TF > 2 ? "T" : "F") +
         (scores.JP > 2 ? "J" : "P");
}

async function runTest() {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });

  const ask = (q) => new Promise(resolve => rl.question(q, resolve));

  const answers = [];

  console.log("Welcome to the MBTI Personality Test!");
  console.log("Please answer the following 20 questions by typing 'A' or 'B'.\n");

  for (let i = 0; i < questions.length; i++) {
    const [a, b] = questions[i];
    let response;
    while (true) {
      response = await ask(`Q${i + 1}:\nA. ${a}\nB. ${b}\nYour choice (A/B): `);
      response = response.trim().toUpperCase();
      if (response === "A" || response === "B") {
        answers.push(response);
        break;
      } else {
        console.log("Invalid input. Please enter 'A' or 'B'.");
      }
    }
  }

  rl.close();

  const mbtiType = calculateMBTI(answers);
  console.log(`\nYour MBTI Type is: ${mbtiType}`);
  console.log(`Read more: https://www.16personalities.com/${mbtiType.toLowerCase()}-personality`);
}

if (require.main === module) {
  runTest();
}

module.exports = { questions, calculateMBTI };
