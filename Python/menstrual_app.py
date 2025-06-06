def calculate_next_period(start_day: int, cycle_length: int) -> int:
    """Calculate next period date"""
    return start_day + cycle_length

def calculate_ovulation(start_day: int, cycle_length: int) -> int:
    """Calculate ovulation day (14 days before next period)"""
    return start_day + (cycle_length - 14)

def get_fertile_window(ovulation_day: int) -> str:
    """Calculate fertile window (2 days before/after ovulation)"""
    return f"{ovulation_day - 2}-{ovulation_day + 2}"

def get_safe_days(start_day: int, ovulation_day: int, next_period: int) -> str:
    """Get safe days recommendations"""
    return (
        f"Safe days before ovulation: {start_day}-{ovulation_day - 3}\n"
        f"Safe days after ovulation: {ovulation_day + 3}-{next_period}"
    )

def main():
	print("Menstrual Period Tracker")
	start_day = int(input("Enter last period start day (1-31): "))
	cycle_length = int(input("Enter average cycle length (21-35 days): "))


	next_period = calculate_next_period(start_day, cycle_length)
	ovulation_day = calculate_ovulation(start_day, cycle_length)
    
	print("\n\t Your Cycle Info")
	print(f"Next period: Day {next_period}")
	print(f"Ovulation day: Day {ovulation_day}")
	print(f"Fertile window: Days {get_fertile_window(ovulation_day)}")
	print(get_safe_days(start_day, ovulation_day, next_period))

if __name__ == "__main__":
	main()
