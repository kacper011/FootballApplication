<h1 align="center">Football Leagues API with Eureka Server and API Gateway</h1>
<h3>📋 Project Overview</h3>
The Football Leagues API is a microservices-based application that provides information about the top football leagues. This project leverages Spring Boot for service creation, Eureka Server for service discovery, and an API Gateway for routing client requests to the appropriate microservices. The architecture is designed to efficiently route requests based on URL patterns, ensuring seamless communication between services.

<h3>🏗️ Architecture</h3>
The system is composed of several key components that work together to provide a robust and scalable microservices architecture:
<br>
<br>
<li>Eureka Server: Handles service discovery, enabling dynamic scaling and fault tolerance by registering microservices dynamically.</li>
<br>
<li>API Gateway: Serves as the single entry point for client requests, routing them to the appropriate services based on URL paths:</li>

<li>Requests sent to http://localhost:8080/laliga are forwarded to the La Liga service running on http://localhost:8081.</li>
<li>Requests sent to http://localhost:8080/bundesliga are forwarded to the Bundesliga service running on http://localhost:8082.</li>
<br>
🛠️ Technologies Used
<br>
<br>
The following technologies and tools are used in this project:
<br>
<br>
<li>Java: Primary programming language used for developing microservices.</li>
<li>Spring Boot: A framework for building stand-alone, production-ready Spring applications quickly.</li>
<li>Eureka Server: A service registry for managing microservices within the architecture.</li>
<li>API Gateway: Routes client requests to the correct microservice based on the URL pattern.</li>
<li>MySQL: Database management system used to store application data.</li>
<li>Postman: API testing tool used for testing and verifying the functionality of services.</li>
<br>
🚀 Setup and Installation
<br><br>
Prerequisites
<br><br>
Ensure you have installed:
<br><br>
<li>Java 17 or higher</li>
<li>Maven</li>
<li>MySQL Server</li>
<li>Postman (optional, for API testing)</li>
<br>
📂 Application Structure
<br>
<br>
<li>eureka-server: Contains the Eureka Server configuration and setup files.</li>
<li>api-gateway: Contains the API Gateway configuration, which handles the routing of requests.</li>
<li>laliga-service: Microservice dedicated to handling data related to La Liga.</li>
<li>bundesliga-service: Microservice dedicated to handling data related to Bundesliga.</li>
<br>
🤝 Contributing
<br>
<br>
Contributions are welcome! Please fork the repository, make your changes, and submit a pull request. Ensure your code follows the project's coding standards and includes appropriate documentation.
