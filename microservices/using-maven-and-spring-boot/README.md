# Microservice using Maven and Spring Boot from CLI

Using Maven and Spring Boot from CLI to create a server for a microservice.

## Steps I took to create this example

- Use https://start.spring.io/ to create a sample project:
   - Keep defaults
   - Add "Spring Web" dependency
   - Click "Generate" to download sample project
- Unzip the `demo.zip` and jump into the directory
- Install using `./mvnw install`
- Create `DemoController.java` based on the content in the [example here](src/main/java/com/example/demo/DemoController.java)
- Run application with `java -jar target/demo-0.0.1-SNAPSHOT.jar`
- Visit http://localhost:8080
