import csv
import random

with open('quizzes.csv', mode='w', newline='') as file:
    writer = csv.writer(file, quoting=csv.QUOTE_NONNUMERIC)
    quizId = 1
    for movie_number in range(1, 3953):
        for question_number in range(1, 4):
            quiz_body = f"movie{movie_number} quiz{question_number} Body"
            correct_answer = random.randint(1, 4)
            writer.writerow([quizId, movie_number, question_number, quiz_body, correct_answer])
            quizId += 1