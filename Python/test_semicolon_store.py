import unittest
from semicolon_store3 import calculate_totals

class TestSemicolonStore(unittest.TestCase):

    def test_cart_below_discount_threshold(self):
        cart = [{'name': 'Book', 'quantity': 2, 'price': 5000}]
        subtotal, discount, vat, total = calculate_totals(cart)
        self.assertEqual(subtotal, 10000)
        self.assertEqual(discount, 0)
        self.assertEqual(vat, 750)
        self.assertEqual(total, 10750)

    def test_cart_with_discount(self):
        cart = [
            {'name': 'Laptop', 'quantity': 1, 'price': 150000},
            {'name': 'Mouse', 'quantity': 2, 'price': 5000}
        ]
        subtotal, discount, vat, total = calculate_totals(cart)
        self.assertEqual(subtotal, 160000)
        self.assertEqual(discount, 16000)
        self.assertEqual(vat, 12000)
        self.assertEqual(total, 156000)

if __name__ == '__main__':
    unittest.main()
