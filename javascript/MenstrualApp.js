const prompt = require('prompt-sync')();


function calculateNextPeriod(startDay, cycleLength) {
  return startDay + cycleLength;
}

function calculateOvulation(startDay, cycleLength) {
  return startDay + (cycleLength - 14);
}

function getFertileWindow(ovulationDay) {
  return [ovulationDay - 2, ovulationDay + 2];
}

function getSafeDays(startDay, ovulationDay, nextPeriod) {
  return {
    before: [startDay, ovulationDay - 3],
    after: [ovulationDay + 3, nextPeriod]
  };
}

function getValidInput(promptText, min, max) {
  while (true) {
    const input = parseInt(prompt(promptText));
    if (!isNaN(input) && input >= min && input <= max) {
      return input;
    }
    console.log(`Please enter a number between ${min}-${max}`);
  }
}


function runTracker() {
  console.log("\nMenstrual Cycle Tracker\n");
  
  const startDay = getValidInput("Last period start day (1-31): ", 1, 31);
  const cycleLength = getValidInput("Average cycle length (21-35 days): ", 21, 35);
  
  const nextPeriod = calculateNextPeriod(startDay, cycleLength);
  const ovulationDay = calculateOvulation(startDay, cycleLength);
  const [fertileStart, fertileEnd] = getFertileWindow(ovulationDay);
  const { before, after } = getSafeDays(startDay, ovulationDay, nextPeriod);
  
  console.log("\nResults");
  console.log(`Next period: Day ${nextPeriod}`);
  console.log(`Ovulation day: Day ${ovulationDay}`);
  console.log(`Fertile window: Days ${fertileStart}-${fertileEnd}`);
  console.log(`Safe days before ovulation: ${before[0]}-${before[1]}`);
  console.log(`Safe days after ovulation: ${after[0]}-${after[1]}`);
}


runTracker();