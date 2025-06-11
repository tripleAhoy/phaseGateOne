questions = [

    ("A. I prefer group activities\nB. I prefer one-on-one conversations", 'EI'),
    ("A. I enjoy being the center of attention\nB. I prefer being more reserved", 'EI'),
    ("A. I feel energized after socializing\nB. I feel drained after socializing", 'EI'),
    ("A. I speak more than I listen\nB. I listen more than I speak", 'EI'),
    ("A. I act first and think later\nB. I think first and then act", 'EI'),

    ("A. I trust experience\nB. I trust inspiration", 'SN'),
    ("A. I like concrete facts\nB. I like abstract theories", 'SN'),
    ("A. I focus on what is real\nB. I focus on possibilities", 'SN'),
    ("A. I like to live in the present\nB. I like to imagine the future", 'SN'),
    ("A. I remember specific facts\nB. I remember impressions or patterns", 'SN'),

    ("A. I make decisions with logic\nB. I make decisions with my heart", 'TF'),
    ("A. I value justice\nB. I value empathy", 'TF'),
    ("A. I critique to help\nB. I avoid hurting others", 'TF'),
    ("A. I am task-oriented\nB. I am people-oriented", 'TF'),
    ("A. I prefer consistency\nB. I prefer harmony", 'TF'),

    ("A. I like to plan and organize\nB. I like to stay flexible", 'JP'),
    ("A. I prefer clear structures\nB. I enjoy spontaneity", 'JP'),
    ("A. I like routines\nB. I go with the flow", 'JP'),
    ("A. I complete tasks ahead of time\nB. I work best under pressure", 'JP'),
    ("A. I need closure\nB. I can handle open-ended situations", 'JP'),
]

scores = {
    'EI': {'A': 0, 'B': 0},
    'SN': {'A': 0, 'B': 0},
    'TF': {'A': 0, 'B': 0},
    'JP': {'A': 0, 'B': 0},
}

print("Welcome to the MBTI Personality Test! Please answer the following 20 questions with 'A' or 'B':\n")

for idx, (q_text, dimension) in enumerate(questions):
    while True:
        answer = input(f"Q{idx+1}: {q_text}\nYour answer (A/B): ").strip().upper()
        if answer in ['A', 'B']:
            scores[dimension][answer] += 1
            break
        else:
            print("Invalid input. Please enter only 'A' or 'B'.")

personality = ""

mapping = {
    'EI': ('E', 'I'),
    'SN': ('S', 'N'),
    'TF': ('T', 'F'),
    'JP': ('J', 'P'),
}

for dim in ['EI', 'SN', 'TF', 'JP']:
    personality += mapping[dim][0] if scores[dim]['A'] > scores[dim]['B'] else mapping[dim][1]


print("\nYour MBTI Personality Type is:", personality)


descriptions = {
    "INTJ": "The Architect – Imaginative and strategic thinkers, with a plan for everything.",
    "INTP": "The Logician – Innovative inventors with an unquenchable thirst for knowledge.",
    "ENTJ": "The Commander – Bold, imaginative and strong-willed leaders.",
    "ENTP": "The Debater – Smart and curious thinkers who cannot resist an intellectual challenge.",
    "INFJ": "The Advocate – Quiet and mystical, yet very inspiring and tireless idealists.",
    "INFP": "The Mediator – Poetic, kind and altruistic, always eager to help a good cause.",
    "ENFJ": "The Protagonist – Charismatic and inspiring leaders, able to mesmerize their listeners.",
    "ENFP": "The Campaigner – Enthusiastic, creative and sociable free spirits.",
    "ISTJ": "The Logistician – Practical and fact-minded individuals, reliable and hard-working.",
    "ISFJ": "The Defender – Very dedicated and warm protectors.",
    "ESTJ": "The Executive – Excellent administrators, unsurpassed at managing things or people.",
    "ESFJ": "The Consul – Extraordinarily caring, social and popular people.",
    "ISTP": "The Virtuoso – Bold and practical experimenters, masters of all kinds of tools.",
    "ISFP": "The Adventurer – Flexible and charming artists, always ready to explore.",
    "ESTP": "The Entrepreneur – Smart, energetic and perceptive people who enjoy living on the edge.",
    "ESFP": "The Entertainer – Spontaneous, energetic and enthusiastic entertainers."
}

description = descriptions.get(personality, "Personality description not found.")
print(f"\nDescription: {description}")
