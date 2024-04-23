cd milestone1

mongod --fork --logpath /var/log/mongodb.log
mongosh admin --eval "db.createUser({ user: 'user', pwd: 'password', roles: ['userAdminAnyDatabase'] })"
mongoimport --db=assign1 --collection=movie --authenticationDatabase admin --username user --password password --type=csv --file=data/movies.csv --fields=movieId.int32\(\),name.string\(\),genre.string\(\) --columnsHaveTypes 
mongoimport --db=assign1 --collection=rating --authenticationDatabase admin --username user --password password --type=csv --file=data/ratings.csv --fields=userId.int32\(\),movieId.int32\(\),rating.int32\(\),timestamp.string\(\) --columnsHaveTypes 
mongoimport --db=assign1 --collection=user --authenticationDatabase admin --username user --password password --type=csv --file=data/users.csv --fields=userId.int32\(\),gender.string\(\),age.int32\(\),occupation.int32\(\),zipCode.string\(\),point.int32\(\),movieStatus.int32\(\),partyRoomStatus.int32\(\),hobby.int32\(\),favoriteFood.int32\(\) --columnsHaveTypes

mvn package
java -jar ./target/cse364-project-1.0-SNAPSHOT.jar