import shutil
import os

# 원본 파일 경로와 폴더 설정
source_file = '/Users/gyuwonpark/Desktop/2024-1학기-UNIST/소프트웨어공학/과제2_마일스톤2/projects-group1/movie1.jpg'
destination_folder = '/Users/gyuwonpark/Desktop/2024-1학기-UNIST/소프트웨어공학/과제2_마일스톤2/projects-group1/src/main/resources/static/images'

# 디렉토리가 존재하지 않으면 생성
if not os.path.exists(destination_folder):
    os.makedirs(destination_folder)

# 복사본 생성
for i in range(1, 4001):
    destination_file = os.path.join(destination_folder, f'movie{i}.jpg')
    shutil.copy(source_file, destination_file)

print("복사 완료!")