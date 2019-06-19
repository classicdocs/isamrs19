# Isa & Mrs Team 19
``` bash
Stefan Varga SW-48/2016
Janko Ljubić SW-38/2016
Miloš Rašević SW-66/2016
```
## Project setup
```bash
isamrs19
├─┬ backend     → backend module with Spring Boot code
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js code
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven parent pom managing both modules
```
## Requirements
For starting this project you will need <a href="https://maven.apache.org/index.html">Maven</a> and <a href="https://www.mysql.com/">MySQL</a>(8.0)

```bash
#Database user:
username: admin
password: admin
```

## Running the full application
Inside the root directory, do a:
``` bash
mvn clean install
```
Run our complete Spring Boot App:
``` bash
mvn --projects backend spring-boot:run
```
Now go to http://localhost:8088/

## Running the backend for development mode
There are multiple ways to run the backend. For development, you can use your favorite IDE. <br>
Insert ``--spring.profiles.active=dev`` to program arguments and run the
``
backend.src.main.java.com.project.project.ProjectApplication
``
Your backend application will be served at 8088 port.

## Running the frontend for development mode
Make sure to install node, npm and all required packages
```bash
cd frontend
install node and npm
npm install
```
Then you are ready to start your frontend application
```bash
npm run serve
```
Now go to http://localhost:8080/

## Hot reloading
Both the front-end and back-end modules support hot reloading.
