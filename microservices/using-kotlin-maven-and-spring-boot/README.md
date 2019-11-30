# Microservice using Maven and Spring Boot from CLI

Using Maven and Spring Boot from CLI to create a server for a microservice.

## Steps I took to create this example

- Use https://start.spring.io/ to create a sample project:
   - Change from "Java" to "Kotlin"
   - Leave other settings as default
   - Add "Spring Web" dependency
   - Click "Generate" to download sample project
- Unzip the `demo.zip` and jump into the directory
- Install using `./mvnw install`
- Create `DemoController.kt` based on the content in the [example here](src/main/kotlin/com/example/demo/DemoController.kt)
- Run application with `java -jar target/demo-0.0.1-SNAPSHOT.jar`
- Visit http://localhost:8080
