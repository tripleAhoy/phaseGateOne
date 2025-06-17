const prompt = require('prompt-sync')();

function calculateNextPeriod(startDate, cycleLength) {
	let nextPeriod = new Date(startDate);
	nextPeriod.setDate(startDate.getDate() + cycleLength);
	return nextPeriod;
}

function calculateOvulation(startDate, cycleLength) {
	let ovulationDay = new Date(startDate);
	ovulationDay.setDate(startDate.getDate() + (cycleLength - 14));
	return ovulationDay;
}

function getFertileWindow(ovulationDate) {
	let fertileStart = new Date(ovulationDate);
	let fertileEnd = new Date(ovulationDate);
	fertileStart.setDate(ovulationDate.getDate() - 2);
	fertileEnd.setDate(ovulationDate.getDate() + 2);
	return { fertileStart, fertileEnd };
}

function getSafeDays(startDate, ovulationDate, nextPeriod) {
	let safeBeforeEnd = new Date(ovulationDate);
	safeBeforeEnd.setDate(ovulationDate.getDate() - 3);

	let safeAfterStart = new Date(ovulationDate);
	safeAfterStart.setDate(ovulationDate.getDate() + 3);

	return {
	safeBefore: { start: startDate, end: safeBeforeEnd },
	safeAfter: { start: safeAfterStart, end: nextPeriod }
	};
}

function formatDate(date) {
	return date.toDateString();
}

function getValidDateInput(promptText) {
	while (true) {
		let input = prompt(promptText);
		let date = new Date(input);
		if (!isNaN(date)) {
			return date;
		}
		console.log("Please enter a valid date (YYYY-MM-DD).");
	}
}

function runTracker() {
	console.log("\nMenstrual Cycle Tracker\n");

	let startDate = getValidDateInput("Enter last period start date (YYYY-MM-DD): ");
	let cycleLength = parseInt(prompt("Enter average cycle length (21-35 days): "), 10);

	let nextPeriod = calculateNextPeriod(startDate, cycleLength);
	let ovulationDate = calculateOvulation(startDate, cycleLength);
	let { fertileStart, fertileEnd } = getFertileWindow(ovulationDate);
	let { safeBefore, safeAfter } = getSafeDays(startDate, ovulationDate, nextPeriod);

	console.log("\nResults:");
	console.log(`Next period: ${formatDate(nextPeriod)}`);
	console.log(`Ovulation day: ${formatDate(ovulationDate)}`);
	console.log(`Fertile window: ${formatDate(fertileStart)} - ${formatDate(fertileEnd)}`);
	console.log(`Safe days before ovulation: ${formatDate(safeBefore.start)} - ${formatDate(safeBefore.end)}`);
	console.log(`Safe days after ovulation: ${formatDate(safeAfter.start)} - ${formatDate(safeAfter.end)}`);
}

runTracker();