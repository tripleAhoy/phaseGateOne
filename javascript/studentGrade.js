const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function askQuestion(question) {
  return new Promise(resolve => {
    rl.question(question, answer => {
      resolve(answer);
    });
  });
}

async function getPositiveInput(prompt) {
  let input;
  do {
    input = parseInt(await askQuestion(prompt));
    if (isNaN(input) || input <= 0) {
      console.log("Invalid input. Please enter a positive integer.");
    }
  } while (isNaN(input) || input <= 0);
  return input;
}

async function getValidScore(prompt, name, subject) {
  let score;
  do {
    score = parseInt(await askQuestion(`${prompt} for ${name} in subject ${subject} (0-100): `));
    if (isNaN(score) || score < 0 || score > 100) {
      console.log("Invalid score! Enter a value between 0-100.");
    }
  } while (isNaN(score) || score < 0 || score > 100);
  return score;
}

async function collectStudentData(numStudents, numSubjects) {
  const studentNames = [];
  const studentGrades = [];
  
  for (let i = 0; i < numStudents; i++) {
    const name = await askQuestion(`Enter name for student ${i+1}: `);
    studentNames.push(name);
    
    const grades = [];
    for (let j = 0; j < numSubjects; j++) {
      const score = await getValidScore("Enter score", name, j+1);
      grades.push(score);
    }
    studentGrades.push(grades);
  }
  
  return { studentNames, studentGrades };
}

function calculateTotals(studentGrades) {
  return studentGrades.map(grades => grades.reduce((sum, grade) => sum + grade, 0));
}

function calculateAverages(totals, numSubjects) {
  return totals.map(total => total / numSubjects);
}

function calculatePositions(totals) {
  const indexedTotals = totals.map((total, index) => ({ total, index }));
  indexedTotals.sort((a, b) => b.total - a.total);
  
  const positions = new Array(totals.length);
  let currentRank = 1;
  
  indexedTotals.forEach((item, i) => {
    if (i > 0 && item.total < indexedTotals[i-1].total) {
      currentRank = i + 1;
    }
    positions[item.index] = currentRank;
  });
  
  return positions;
}

function printReportHeader(numStudents, numSubjects) {
  console.log("\nClass Summary Report");
  console.log("====================");
  console.log(`Number of Students: ${numStudents}`);
  console.log(`Number of Subjects: ${numSubjects}\n`);
  
  let header = "Student Name\t\t";
  for (let i = 0; i < numSubjects; i++) {
    header += `Subject ${i+1}\t`;
  }
  header += "Total\tAverage\tPosition";
  console.log(header);
}

function printStudentResults(names, grades, totals, averages, positions) {
  names.forEach((name, i) => {
    let row = `${name}\t\t`;
    row += grades[i].join("\t") + "\t";
    row += `${totals[i]}\t${averages[i].toFixed(2)}\t${positions[i]}`;
    console.log(row);
  });
}

function printSubjectSummaries(names, grades) {
  console.log("\nSubject Summaries");
  console.log("===============");
  
  const numSubjects = grades[0].length;
  const numStudents = grades.length;
  
  for (let j = 0; j < numSubjects; j++) {
    let highest = -1;
    let lowest = 101;
    let highName = "";
    let lowName = "";
    let total = 0;
    let passes = 0;
    
    for (let i = 0; i < numStudents; i++) {
      const score = grades[i][j];
      total += score;
      if (score > highest) {
        highest = score;
        highName = names[i];
      }
      if (score < lowest) {
        lowest = score;
        lowName = names[i];
      }
      if (score >= 50) passes++;
    }
    
    const avg = total / numStudents;
    const fails = numStudents - passes;
    
    console.log(`\nSubject ${j+1}`);
    console.log(`Highest scoring student is: ${highName} (${highest})`);
    console.log(`Lowest scoring student is: ${lowName} (${lowest})`);
    console.log(`Total score: ${total}`);
    console.log(`Average score: ${avg.toFixed(2)}`);
    console.log(`Number of passes: ${passes}`);
    console.log(`Number of fails: ${fails}`);
    console.log("---------------------");
  }
}

function printClassStatistics(names, grades, totals) {
  console.log("\nClass Statistics");
  console.log("================");
  

  const numSubjects = grades[0].length;
  const numStudents = grades.length;
  
  let maxFails = -1;
  let minFails = numStudents + 1;
  let hardest = -1;
  let easiest = -1;
  
  for (let j = 0; j < numSubjects; j++) {
    let fails = 0;
    for (let i = 0; i < numStudents; i++) {
      if (grades[i][j] < 50) fails++;
    }
    
    if (fails > maxFails) {
      maxFails = fails;
      hardest = j;
    }
    if (fails < minFails) {
      minFails = fails;
      easiest = j;
    }
  }
  
  console.log(`The hardest subject is Subject ${hardest+1} with ${maxFails} failures`);
  console.log(`The easiest subject is Subject ${easiest+1} with ${numStudents-minFails} passes`);
  
  let highScore = -1;
  let lowScore = 101;
  let highName = "";
  let lowName = "";
  let highSubj = -1;
  let lowSubj = -1;
  
  for (let i = 0; i < numStudents; i++) {
    for (let j = 0; j < numSubjects; j++) {
      const score = grades[i][j];
      if (score > highScore) {
        highScore = score;
        highName = names[i];
        highSubj = j;
      }
      if (score < lowScore) {
        lowScore = score;
        lowName = names[i];
        lowSubj = j;
      }
    }
  }
  
  console.log(`The overall highest score is scored by ${highName} in Subject ${highSubj+1} (${highScore})`);
  console.log(`The overall lowest score is scored by ${lowName} in Subject ${lowSubj+1} (${lowScore})`);
 
  console.log("\nClass Summary");
  console.log("=============");
  
  const bestIdx = totals.indexOf(Math.max(...totals));
  const worstIdx = totals.indexOf(Math.min(...totals));
  
  console.log(`Best graduating student is ${names[bestIdx]} with total score ${totals[bestIdx]}`);
  console.log(`Worst graduating student is ${names[worstIdx]} with total score ${totals[worstIdx]}`);
  
  const classTotal = totals.reduce((sum, total) => sum + total, 0);
  const classAvg = classTotal / (numStudents * numSubjects);
  
  console.log(`Class total score: ${classTotal}`);
  console.log(`Class average score: ${classAvg.toFixed(2)}`);
}

async function main() {
  console.log("How many students do you have?");
  const numStudents = await getPositiveInput("Enter a positive integer: ");
  
  console.log("\nHow many subjects do they offer?");
  const numSubjects = await getPositiveInput("Enter a positive integer: ");
  console.log("Saved successfully\n");
  
  const { studentNames, studentGrades } = await collectStudentData(numStudents, numSubjects);
  const totals = calculateTotals(studentGrades);
  const averages = calculateAverages(totals, numSubjects);
  const positions = calculatePositions(totals);
  
  printReportHeader(numStudents, numSubjects);
  printStudentResults(studentNames, studentGrades, totals, averages, positions);
  printSubjectSummaries(studentNames, studentGrades);
  printClassStatistics(studentNames, studentGrades, totals);
  
  rl.close();
}

main().catch(err => {
  console.error("An error occurred:", err);
  rl.close();
});