const prompt = require('prompt-sync')();

const customerName = prompt("What is the name of the customer? ");
const cart = [];

let addMore = true;
while (addMore) {
    const name = prompt("What did the user buy? ");
    const quantity = parseInt(prompt("How many pieces? "));
    const unitPrice = parseFloat(prompt("How much per unit? "));
    cart.push({ name, quantity, unitPrice });

    const more = prompt("Add more items? (yes/no): ").toLowerCase();
    addMore = (more === 'yes');
}

const cashierName = prompt("What is the name of cashier? ");

function round(value) {
    return Math.round(value * 100) / 100;
}

function computeSubtotal(cart) {
    return cart.reduce((sum, item) => sum + item.quantity * item.unitPrice, 0);
}

function computeDiscount(subtotal) {
    return subtotal >= 20000 ? subtotal * 0.10 : 0;
}

function computeVat(subtotal) {
    return subtotal * 0.075;
}

function computeTotal(subtotal, discount, vat) {
    return subtotal - discount + vat;
}

function displayInvoice() {
    console.log("\n======= SEMICOLON STORES INVOICE =======");
    console.log(`Customer: ${customerName}`);
    console.log(`Cashier: ${cashierName}`);
    console.log("----------------------------------------");
    console.log("Item\t\tQty\tPrice\tTotal");

    cart.forEach(item => {
        const total = item.quantity * item.unitPrice;
        console.log(`${item.name}\t\t${item.quantity}\t#${item.unitPrice}\t#${total}`);
    });

    const subtotal = computeSubtotal(cart);
    const discount = computeDiscount(subtotal);
    const vat = computeVat(subtotal);
    const total = computeTotal(subtotal, discount, vat);

    console.log("----------------------------------------");
    console.log(`Subtotal:\t\t\t#${round(subtotal)}`);
    console.log(`Discount:\t\t\t#${round(discount)}`);
    console.log(`VAT (7.5%):\t\t\t#${round(vat)}`);
    console.log(`TOTAL:\t\t\t\t#${round(total)}`);
    console.log("========================================");
}

displayInvoice();
