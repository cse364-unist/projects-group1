import csv
import random

input_file_path = 'users.csv'
output_file_path = 'newUsers.csv'

with open(input_file_path, 'r', newline='') as infile, open(output_file_path, 'w', newline='') as outfile:
    reader = csv.reader(infile)
    writer = csv.writer(outfile)

    for row in reader:
        #Point
        random_number = random.randint(0, 100)
        row.append(random_number)
        #Movie ID
        random_number = random.randint(1, 3952)
        row.append(random_number)
        #Party Room number
        random_number = random.randint(0, 99)
        row.append(random_number)
        #Hobby
        random_number = random.randint(0, 9)
        row.append(random_number)
        #Favorite food
        random_number = random.randint(0, 9)
        row.append(random_number)
        writer.writerow(row)

print("CSV file saved.")