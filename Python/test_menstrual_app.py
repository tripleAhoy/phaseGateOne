import unittest
from menstrual_app import (
    calculate_next_period,
    calculate_ovulation,
    get_fertile_window,
    get_safe_days
)

class TestMenstrualCycle(unittest.TestCase):
    def test_calculate_next_period(self):
        self.assertEqual(calculate_next_period(1, 28), 29)
        self.assertEqual(calculate_next_period(10, 30), 40)
        self.assertEqual(calculate_next_period(15, 21), 36)

    def test_calculate_ovulation(self):
        self.assertEqual(calculate_ovulation(1, 28), 15)
        self.assertEqual(calculate_ovulation(5, 30), 21)
        self.assertEqual(calculate_ovulation(10, 35), 31)

    def test_get_fertile_window(self):
        self.assertEqual(get_fertile_window(15), "13-17")
        self.assertEqual(get_fertile_window(21), "19-23")

    def test_get_safe_days(self):
        result = get_safe_days(1, 15, 29)
        expected = "Safe days before ovulation: 1-12\nSafe days after ovulation: 18-29"
        self.assertEqual(result, expected)
        
        result2 = get_safe_days(5, 21, 35)
        expected2 = "Safe days before ovulation: 5-18\nSafe days after ovulation: 24-35"
        self.assertEqual(result2, expected2)

if __name__ == "__main__":
    unittest.main()
