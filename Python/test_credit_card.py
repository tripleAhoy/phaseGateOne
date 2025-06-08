import unittest
from credit_card import get_card_type, luhn_check

class TestCreditCard(unittest.TestCase):

    def test_get_card_type(self):
        self.assertEqual(get_card_type("4123456789012"), "Visa")
        self.assertEqual(get_card_type("5123456789012345"), "MasterCard")
        self.assertEqual(get_card_type("371234567890123"), "American Express")
        self.assertEqual(get_card_type("6123456789012345"), "Discover")
        self.assertEqual(get_card_type("7123456789012"), "Unknown")

    def test_luhn_check_valid(self):
        # Valid card numbers (fake)
        self.assertTrue(luhn_check("4388576018410707"))
        self.assertTrue(luhn_check("6011111111111117"))

    def test_luhn_check_invalid(self):
        # Invalid card numbers
        self.assertFalse(luhn_check("4388576018402626"))
        self.assertFalse(luhn_check("1234567890123"))

if __name__ == "__main__":
    unittest.main()
