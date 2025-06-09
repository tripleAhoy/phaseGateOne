const prompt = require('prompt-sync')();

function getCardType(number) {
    if (number.startsWith("4")) return "Visa";
    if (number.startsWith("5")) return "MasterCard";
    if (number.startsWith("37")) return "American Express";
    if (number.startsWith("6")) return "Discover";
    return "Unknown";
}

function luhnCheck(number) {
    let sumEven = 0, sumOdd = 0;
    for (let i = number.length - 2; i >= 0; i -= 2) {
        let double = parseInt(number[i]) * 2;
        sumEven += (double > 9) ? double - 9 : double;
    }
    for (let i = number.length - 1; i >= 0; i -= 2) {
        sumOdd += parseInt(number[i]);
    }
    return (sumEven + sumOdd) % 10 === 0;
}

function validateCard() {
    const number = prompt("Enter your credit card number: ").trim();
    if (!/^\d{13,16}$/.test(number)) {
        console.log("Invalid input. Card number must be 13–16 digits.");
        return;
    }

    const type = getCardType(number);
    const valid = validCheck(number);

    console.log("Card Type:", type);
    console.log("Status:", valid ? "VALID" : "INVALID");
}

validateCard();
