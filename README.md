# RabbitMQ + Spring Boot Demo

This project is a simple messaging example using RabbitMQ with Spring Boot. The repository contains two independent modules:
- **Producer:** Sends messages through an HTTP interface. When a request is received, it uses the RabbitTemplate component to deliver the message to RabbitMQ queues with a defined exchange and routing key.
- **Consumer:** Listens to the same queue using the @RabbitListener annotation and prints incoming messages to the console.



## Requirements

- Java 17+
- Maven
- Docker


## Run Locally

#### 1) Start the RabbitMQ Service
The **docker-compose.yaml** file in the root directory defines a RabbitMQ container using the **rabbitmq:3-management** image. To start the container:
```bash
docker compose up -d
```
This command starts the RabbitMQ server in the background and binds **5672 (application connection)** and **15672 (management UI)** ports. You can access the management interface at http://localhost:15672 and log in with **guest/guest** credentials.

###

#### 2) Run the Producer Application
The Producer module provides a REST endpoint to receive messages via HTTP and send them to RabbitMQ. To run:
```bash
cd Producer
./mvnw spring-boot:run
```
This application runs on **port 8080**. The necessary configuration values are set as follows:  
- app.mq.queue=test.queue  
- app.mq.exchange=test.exchange  
- app.mq.routing=test.routing  

You can modify these values in **Producer/src/main/resources/application.properties**.

###

#### 3) Run the Consumer Application
The Consumer module listens to the **test.queue** queue and prints received messages. To run:
```bash
cd Consumer
./mvnw spring-boot:run
```
The application runs on **port 8081** and starts listening when the RabbitMQ queue is ready. You can see the message output in the console.

###

#### 4) Send a Message
While the Producer is running, you can send a message using an HTTP POST request like below:
```bash
curl -X POST \
  -H "Content-Type: text/plain" \
  -d "Merhaba RabbitMQ" \
  http://localhost:8080/sendMessage
```
