## Start Kafka locally
1. docker-compose kafup -d
2. run `docker ps` to ensure kafka has started

## POD event producer
1. Start podpublish app
2. Access app in following format - http://localhost:8081/1?lat=40.742&lon=-73.5&speed=75
3. Generate load with tiny variations of lat, lon and speed
4. /1 is id, you can increment it for every request

## POD event processing
1. Start podprocess app
2. Access the app - http://localhost:8080/dynamicmarkers.html
