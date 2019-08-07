# Back office

Project includes 3 modules
- etl - ETL (emit transform load) utility to convert TSV based files into MySQL tables and data for Back Office
- back office api - API backend for Back Office (Spring Boot, MySQL)
- back office ui - Angular based UI for Back Office

## Running the application

### ETL
##### Prerequisites
- Please, make sure ```JDK 8``` is installed on your machine (run ```javac -version``` to verify version installed).
- Use ```docker compose up db``` to run ```MySQL``` (```docker-compose.yml``` file is located in a project root)

##### Build & Run
- Navigate to ```etl``` directory
- Run ```./mvnw clean install``` (for Linux, MacOS) or ```mvnw.cmd clean install``` (for Windows)
- Run ```java -jar /absolute-path-to-etl-1.0-SNAPSHOT.jar etl -b /absolute-path-to-brands.tsv -q /absolute-path-to-quantity.tsv -u user -p secret --url jdbc:mysql://localhost:3306/brands_db?useSSL=false```
- Run ```java -jar /absolute-path-to-etl-1.0-SNAPSHOT.jar etl -h``` to get help

### Back Office API
##### Prerequisites
- Please, make sure ```JDK 8``` is installed on your machine (run ```javac -version``` to verify version installed).
- Follow [this](https://dev.mysql.com/downloads/installer/) link to install ```MySQL DB```.

##### Build & Run
- Navigate to ```backup-office-api``` directory
- Run ```./mvnw spring-boot:run``` (for Linux, MacOS) or ```mvnw.cmd spring-boot:run``` (for Windows)
- Open http://localhost:8080/back-office/actuator/health in your browser. App started successfully if you see ```{"status":"UP"}``` in a browser console.

##### Documentation
API documentation (powered by [Swagger](https://swagger.io/tools/swagger-ui/)) is avaliable by the folowing [link](http://localhost:8080/back-office/swagger-ui.html).

### Back Office UI


##### Build & Run
- go to ```back-office-ui``` folder
- run ```npm install``` to install all the required dependencies and packages 
- run ```npm run ng serve``` which will start an app server running on http://localhost:4200. Follow http://localhost:4200/back-office/ to open UI app in a browser.
