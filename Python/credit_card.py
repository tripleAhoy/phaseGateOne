def get_card_type(number):
    if number.startswith('4'):
        return "Visa"
    elif number.startswith('5'):
        return "MasterCard"
    elif number.startswith('37'):
        return "American Express"
    elif number.startswith('6'):
        return "Discover"
    return "Unknown"

def validation_check(number):
    digits = [int(d) for d in number]
    sum_even = 0
    sum_odd = 0

    for i in range(len(digits) - 2, -1, -2):
        double = digits[i] * 2
        sum_even += double if double < 10 else double - 9

    for i in range(len(digits) - 1, -1, -2):
        sum_odd += digits[i]

    total = sum_even + sum_odd
    return total % 10 == 0
