# 🚀 Micro Java Web Server

\[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)\]
\[![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven)\]
\[![License](https://img.shields.io/badge/License-MIT-black?style=for-the-badge)\]

------------------------------------------------------------------------

## 📌 Project Overview

This project implements a **minimal Web Server and IoC Microframework in Java**.

The server is capable of:

- Serving **HTML files**
- Serving **PNG images**
- Handling **HTTP GET requests**
- Extracting **query parameters**
- Dynamically loading **controllers using Java Reflection**
- Deploying the application in **AWS EC2**

The goal of the project is to understand the internal mechanisms of modern frameworks like **Spring Boot**, by implementing a simplified version from scratch.

------------------------------------------------------------------------

## 🏗 Architecture

Client Browser\
↓\
HTTP Request\
↓\
MicroServer (Socket Server)\
↓\
RouteTable (Route Registry)\
↓\
ControllerScanner (IoC Loader)\
↓\
Controller Methods (@GetRoute)\
↓\
Response (HTML / PNG / String)


------------------------------------------------------------------------

## 🧠 Core Concepts Demonstrated

### 1️⃣ HTTP Server Implementation

A lightweight HTTP server implemented using Java sockets:
``` java
ServerSocket server = new ServerSocket(35000);
Socket client = server.accept();
```
The server parses incoming HTTP requests and dispatches them to the appropriate controller.

### 2️⃣ Reflection-based IoC Container

Controllers are discovered dynamically using reflection.
``` java
ControllerScanner.scan("co.edu.escuelaing.reflexionlab.demo");
```
Classes annotated with:
``` java
@WebController
```
are automatically loaded and registered. Their annotated methods are registered as HTTP endpoints in the routing table.

### 3️⃣ Annotation-based Routing

Routes are defined using custom annotations:
``` java
@GetRoute("/hello")
public String hello() {
    return "Hello World";
}
```
During application startup, the framework scans controller classes and registers each annotated method in the RouteTable, mapping URLs to executable methods.

### 4️⃣ Query Parameter Extraction

Query parameters are automatically extracted from the URL and injected into controller methods.

Example request:

``` bash
/greeting?name=Sebastian
```

``` java
@GetRoute("/greeting")
public String greeting(@QueryParam(value="name", defaultValue="World") String name){
    return "Hello " + name;
}
```
The framework parses the query string and matches parameters with the method arguments using the @QueryParam annotation.

### 5️⃣ Static File Handling

The server can serve static resources such as:
- HTML pages
- PNG images
Example requests:
```
http://localhost:35000/index.html
http://localhost:35000/logo.png
```

If a request does not match a registered route, the framework attempts to locate the resource in the static files directory and returns it with the correct HTTP content type.


``` java
@GetRoute("/hello")
public String hello() {
    return "Hello World";
}
```
During application startup, the framework scans controller classes and registers each annotated method in the RouteTable, mapping URLs to executable methods.



------------------------------------------------------------------------

## 🚀 Example Endpoints

The server exposes the following endpoints:

    /hello
    /greeting?name=Jeisson
    /pi
    /square?n=9

Example responses:

    /pi → 3.141592653589793
    /square?n=9 → 81
    /greeting?name=Jeisson → Hola Jeisson


------------------------------------------------------------------------

## ⚙️ Getting Started

### 📦 Prerequisites

-   Java 17+
-   Maven
-   Git
-   AWS Account

------------------------------------------------------------------------

## 🔧 Installation

``` bash
git clone https://github.com/JeissonS02/micro-java-web-server.git
cd micro-java-web-server
```
Compile the project:
``` bash
mvn clean package
```

------------------------------------------------------------------------

## ▶️ Running the Project

Run the micro server:
``` bash
java -cp target/classes co.edu.escuelaing.reflexionlab.MicroServer
```

Server runs at:

    http://localhost:35000

------------------------------------------------------------------------

## 📁 Project Structure

    micro-java-web-server
    ├── src
    │   └── main
    │       ├── java
    │       │   └── co
    │       │       └── edu
    │       │           └── escuelaing
    │       │               └── reflexionlab
    │       │                   ├── annotations
    │       │                   ├── framework
    │       │                   ├── demo
    │       │                   └── MicroServer.java
    │       └── resources
    │           ├── index.html
    │           └── logo.png
    ├── images
    │   ├── aws-instance.png
    │   ├── server-running.png
    │   ├── endpoint-pi.png
    │   └── endpoint-square.png
    ├── pom.xml
    └── README.md

------------------------------------------------------------------------

## ☁️ AWS Deployment

The application was deployed on an AWS EC2 instance running Amazon Linux.

### EC2 Instance

![AWS Instance](/images/aws-instance.jpeg)

#### Server Running on EC2

The server was executed with

``` bash 
java -cp target/classes co.edu.escuelaing.reflexionlab.MicroServer
```

![Server Running](/images/server-running.png)

### Testing Public Endpoints
The application is accessible through the EC2 public DNS.

Example:

```
http://ec2-98-92-252-214.compute-1.amazonaws.com:35000/pi
```

![Endpoint Pi](/images/endpoint-pi.jpeg)

```
http://ec2-98-92-252-214.compute-1.amazonaws.com:35000/square?n=9
```
![Endpoint Square](/images/endpoint-square.jpeg)

------------------------------------------------------------------------

## 📈 Learning Outcomes

Through this project we explored:

-   HTTP server implementation in Java
-   Reflection and annotation processing
-   Basic IoC container design
-   Static file serving
-   REST endpoint creation
-   Cloud deployment with AWS EC2

------------------------------------------------------------------------

## 👨‍💻 Author

[![GitHub](https://img.shields.io/badge/GitHub-JeissonS02-181717?style=for-the-badge&logo=github)](https://github.com/JeissonS02)


