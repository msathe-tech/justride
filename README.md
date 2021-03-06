## Start Kafka locally
1. docker-compose up -d
2. run `docker ps` to ensure kafka has started

## POD event producer
1. Start podpublish app
2. Access app in following format - http://localhost:8081/1?lat=40.742&lon=-73.5&speed=75
3. Generate load with tiny variations of lat, lon and speed
4. /1 is id, you can increment it for every request

## POD event processing

Note - You need Google Maps API key. Once you have the actual API key, find and replace YOUR_API_KEY in the HTML code.

1. Start podprocess app
2. Access the app - http://localhost:8080/dynamicmarkers.html

## Clean up Kafka containers

1. docker kill $(docker ps -q)
2. docker rm $(docker ps -a -q)
3. docker rmi $(docker images -q)
