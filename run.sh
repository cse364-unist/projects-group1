cd milestone1

mongod --fork --logpath /var/log/mongodb.log
mongosh admin --eval "db.createUser({ user: 'user', pwd: 'password', roles: ['userAdminAnyDatabase'] })"
mongoimport --db=assign1 --collection=movie --authenticationDatabase admin --username user --password password --type=csv --file=data/movies.csv \
    --fields=movieId.int32\(\),name.string\(\),genre.string\(\),placeId.int32\(\) --columnsHaveTypes 
mongoimport --db=assign1 --collection=rating --authenticationDatabase admin --username user --password password --type=csv --file=data/ratings.csv \
    --fields=userId.int32\(\),movieId.int32\(\),rating.int32\(\),timestamp.string\(\) --columnsHaveTypes 
mongoimport --db=assign1 --collection=user --authenticationDatabase admin --username user --password password --type=csv --file=data/users.csv \
    --fields=userId.int32\(\),gender.string\(\),age.int32\(\),occupation.int32\(\),zipCode.string\(\),point.int32\(\),movieStatus.int32\(\),latitude.double\(\),longitude.double\(\),hobby.int32\(\),favoriteFood.int32\(\) --columnsHaveTypes
mongoimport --db=assign1 --collection=place --authenticationDatabase admin --username user --password password --type=csv --file=data/moviePlaces.csv \
    --fields=placeId.int32\(\),name.string\(\),latitude.double\(\),longitude.double\(\),hobby.int32\(\),favoriteFood.int32\(\) --columnsHaveTypes

#mvn test # JUnit 테스트 케이스들이 실행되어 각 테스트의 성공 여부를 결정. 이 단계는 테스트가 성공적으로 수행되었는지 확인
mvn jacoco:report # JaCoCo는 Java 코드의 coverage를 측정하는 라이브러리, 이 명령어는 실행된 테스트를 기반으로 코드 커버리지 보고서를 생성
mvn package # 여기 안에 test 포함이라 굳이 위에 따로 위에 안해도 ㄱㅊ
java -jar ./target/cse364-project-1.0-SNAPSHOT.jar