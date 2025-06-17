from datetime import datetime, timedelta

def calculate_next_period(start_date: datetime, cycle_length: int) -> datetime:
	return start_date + timedelta(days=cycle_length)

def calculate_ovulation(start_date: datetime, cycle_length: int) -> datetime:
	return start_date + timedelta(days=(cycle_length - 14))

def get_fertile_window(ovulation_date: datetime) -> str:
	start_fertile = ovulation_date - timedelta(days=2)
	end_fertile = ovulation_date + timedelta(days=2)
	return f"{start_fertile.strftime('%d %b %Y')} to {end_fertile.strftime('%d %b %Y')}"

def get_safe_days(start_date: datetime, ovulation_date: datetime, next_period: datetime) -> str:
	safe_before = start_date.strftime('%d %b %Y') + " - " + (ovulation_date - timedelta(days=3)).strftime('%d %b %Y')
	safe_after = (ovulation_date + timedelta(days=3)).strftime('%d %b %Y') + " - " + next_period.strftime('%d %b %Y')
	return f"Safe days before ovulation: {safe_before}\nSafe days after ovulation: {safe_after}"

def main():
	print("Menstrual Period Tracker")

	input_date = input("Enter last period start date (YYYY-MM-DD): ")
	start_date = datetime.strptime(input_date, "%Y-%m-%d").date()

	cycle_length = int(input("Enter average cycle length (21-35 days): "))

	next_period = calculate_next_period(start_date, cycle_length)
	ovulation_date = calculate_ovulation(start_date, cycle_length)

	print("\n\tYour Cycle Info")
	print(f"Next period: {next_period.strftime('%d %b %Y')}")
	print(f"Ovulation day: {ovulation_date.strftime('%d %b %Y')}")
	print(f"Fertile window: {get_fertile_window(ovulation_date)}")
	print(get_safe_days(start_date, ovulation_date, next_period))

if __name__ == "__main__":
	main()

