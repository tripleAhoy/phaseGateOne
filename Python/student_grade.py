def main():
    print("How many students do you have?")
    num_students = get_positive_input("Enter a positive integer: ")
    
    print("\nHow many subjects do they offer?")
    num_subjects = get_positive_input("Enter a positive integer: ")
    print("Saved successfully\n")
    
    names, grades = collect_student_data(num_students, num_subjects)
    totals = calculate_totals(grades)
    averages = calculate_averages(totals, num_subjects)
    positions = calculate_positions(totals)
    
    print_report_header(num_students, num_subjects)
    print_student_results(names, grades, totals, averages, positions)
    print_subject_summaries(names, grades)
    print_class_statistics(names, grades, totals)


def get_positive_input(prompt):
    while True:
        try:
            value = int(input(prompt))
            if value > 0:
                return value
            print("Invalid input. Please enter a positive integer.")
        except ValueError:
            print("Invalid input. Please enter a positive integer.")

def get_valid_score(prompt):
    while True:
        try:
            score = int(input(prompt))
            if 0 <= score <= 100:
                return score
            print("Invalid score! Enter a value between 0-100.")
        except ValueError:
            print("Invalid input. Please enter a number.")

def collect_student_data(num_students, num_subjects):
    student_names = []
    student_grades = []
    
    for i in range(num_students):
        name = input(f"Enter name for student {i+1}: ")
        student_names.append(name)
        
        grades = []
        for j in range(num_subjects):
            score = get_valid_score(
                f"Enter score for {name} in subject {j+1} (0-100): "
            )
            grades.append(score)
        student_grades.append(grades)
        
    return student_names, student_grades

def calculate_totals(student_grades):
    return [sum(grades) for grades in student_grades]

def calculate_averages(totals, num_subjects):
    return [total / num_subjects for total in totals]

def calculate_positions(totals):
    ranked = sorted(((total, i) for i, total in enumerate(totals)), reverse=True)
    positions = [0] * len(totals)
    current_rank = 1
    
    for i, (total, idx) in enumerate(ranked):
        if i > 0 and total < ranked[i-1][0]:
            current_rank = i + 1
        positions[idx] = current_rank
        
    return positions

def print_report_header(num_students, num_subjects):
    print("\nClass Summary Report")
    print("====================")
    print(f"Number of Students: {num_students}")
    print(f"Number of Subjects: {num_subjects}\n")
    
    header = "Student Name\t\t"
    header += "\t".join(f"Subject {i+1}" for i in range(num_subjects))
    header += "\tTotal\tAverage\tPosition"
    print(header)

def print_student_results(names, grades, totals, averages, positions):
    for i in range(len(names)):
        row = f"{names[i]}\t\t"
        row += "\t".join(str(grade) for grade in grades[i])
        row += f"\t{totals[i]}\t{averages[i]:.2f}\t{positions[i]}"
        print(row)

def print_subject_summaries(names, grades):
    print("\nSubject Summary")
    print("===============")
    
    num_subjects = len(grades[0])
    num_students = len(grades)
    
    for j in range(num_subjects):
        highest = -1
        lowest = 101
        high_name = ""
        low_name = ""
        total = 0
        passes = 0
        
        for i in range(num_students):
            score = grades[i][j]
            total += score
            if score > highest:
                highest = score
                high_name = names[i]
            if score < lowest:
                lowest = score
                low_name = names[i]
            if score >= 50:
                passes += 1
        
        avg = total / num_students
        fails = num_students - passes
        
        print(f"\nSubject {j+1}")
        print(f"Highest scoring student is: {high_name} ({highest})")
        print(f"Lowest scoring student is: {low_name} ({lowest})")
        print(f"Total score: {total}")
        print(f"Average score: {avg:.2f}")
        print(f"Number of passes: {passes}")
        print(f"Number of fails: {fails}")
        print("---------------------")

def print_class_statistics(names, grades, totals):
    print("\nClass Statistics")
    print("================")
    
    # Hardest/easiest subjects
    num_subjects = len(grades[0])
    num_students = len(grades)
    
    max_fails = -1
    min_fails = num_students + 1
    hardest = -1
    easiest = -1
    
    for j in range(num_subjects):
        fails = sum(1 for i in range(num_students) if grades[i][j] < 50)
        if fails > max_fails:
            max_fails = fails
            hardest = j
        if fails < min_fails:
            min_fails = fails
            easiest = j
            
    print(f"The hardest subject is Subject {hardest+1} with {max_fails} failures")
    print(f"The easiest subject is Subject {easiest+1} with {num_students-min_fails} passes")
    
    # Overall highest/lowest
    high_score = -1
    low_score = 101
    high_name = ""
    low_name = ""
    high_subj = -1
    low_subj = -1
    
    for i in range(num_students):
        for j in range(num_subjects):
            score = grades[i][j]
            if score > high_score:
                high_score = score
                high_name = names[i]
                high_subj = j
            if score < low_score:
                low_score = score
                low_name = names[i]
                low_subj = j
                
    print(f"The overall highest score is scored by {high_name} in Subject {high_subj+1} ({high_score})")
    print(f"The overall lowest score is scored by {low_name} in Subject {low_subj+1} ({low_score})")
    
    # Class summary
    print("\nClass Summary")
    print("=============")
    
    best_idx = totals.index(max(totals))
    worst_idx = totals.index(min(totals))
    
    print(f"Best graduating student is {names[best_idx]} with total score {totals[best_idx]}")
    print(f"Worst graduating student is {names[worst_idx]} with total score {totals[worst_idx]}")
    
    class_total = sum(totals)
    class_avg = class_total / (num_students * num_subjects)
    
    print(f"Class total score: {class_total}")
    print(f"Class average score: {class_avg:.2f}")


if __name__ == "__main__":
    main()