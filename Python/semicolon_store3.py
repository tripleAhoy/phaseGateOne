def get_product_info():
    name = input("What did the user buy? ").strip()
    quantity = int(input("How many pieces? "))
    price = float(input("How much per unit? #"))
    return {'name': name, 'quantity': quantity, 'price': price}


def ask_to_continue():
    return input("Add more items? (yes/no): ").strip().lower() == 'yes'


def calculate_totals(cart):
    subtotal = sum(item['quantity'] * item['price'] for item in cart)
    discount = 0.10 * subtotal if subtotal >= 20000 else 0
    vat = 0.075 * subtotal
    total = subtotal - discount + vat
    return round(subtotal, 2), round(discount, 2), round(vat, 2), round(total, 2)


def print_invoice(customer_name, cashier_name, cart, subtotal, discount, vat, total):
    print("\n=========== SEMICOLON STORE INVOICE ===========")
    print(f"Customer Name: {customer_name}")
    print(f"Cashier: {cashier_name}")
    print("-" * 50)
    print("{:<20} {:<10} {:<12} {:<10}".format("Product", "Qty", "Unit Price", "Total"))
    print("-" * 50)
    for item in cart:
        total_price = item['quantity'] * item['price']
        print("{:<20} {:<10} #{:<10.2f} #{:<10.2f}".format(item['name'], item['quantity'], item['price'], total_price))
    print("-" * 50)
    print(f"{'Subtotal:':<42} #{subtotal:.2f}")
    print(f"{'Discount (10% if ≥ #20,000):':<42} #{discount:.2f}")
    print(f"{'VAT (7.5%):':<42} #{vat:.2f}")
    print(f"{'Total:':<42} #{total:.2f}")
    print("=" * 50)


def main():
    print("=== Welcome to Semicolon Store Checkout ===")

    customer_name = input("What is the name of the customer? ").strip()
    cashier_name = input("What is the name of the cashier? ").strip()
    
    cart = []

    while True:
        item = get_product_info()
        cart.append(item)
        if not ask_to_continue():
            break

    if cart:
        subtotal, discount, vat, total = calculate_totals(cart)
        print_invoice(customer_name, cashier_name, cart, subtotal, discount, vat, total)
    else:
        print("Cart is empty. No invoice generated.")


if __name__ == "__main__":
    main()
