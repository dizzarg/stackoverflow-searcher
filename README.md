# stackoverflow-searcher
Stackoverflow searcher demo project

The web application allows user to search questions according to the input data from Stackoverflow Exchange using its API.

### Requirements:
  * Oracle Java SE Development Kit 8 
  * Apache Maven 3.x
  * Docker 18.03 

### Build
The project is built by a command `mvn clean package`.

### Run server
Within project folder run command: 

`java -jar application/target/stackoverflow-searcher-demo-0.0.1-SNAPSHOT.jar`
 
### Run client
Within project folder run command:
 
`cd ui/frontend && npm run start`

### Run in docker container

Within project folder run command:
 
`docker-compose up`

### Usage without docker container
Go to `http://localhost:3000` and write down what do you want to find in StackOverflow.

### Usage with docker container
Go to `http://localhost/` and write down what do you want to find in StackOverflow.
