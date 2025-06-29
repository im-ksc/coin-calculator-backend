# 🪙🪙 Coin Change Calculator - Backend 🪙🪙

This is the backend service for the Coin Change Calculator app, built using [Dropwizard](https://www.dropwizard.io/). It communicates with a React Frontend to return the optimal coin combination.

Built for a challenge

---

## Assumptions made for challenge

- If there's no possible combination to reach target exactly, the least coins combination will be returned, but will not be equal to target

---

## Features

- REST API service that returns optimal coin denominations for a given target amount (0 - 10000) and given list of coin denominations
- Auto-deployment on DigitalOcean

---

## Getting Started

### Prerequisites

- Java JDK 21 or higher
- Maven 3.x (3.9.9 recommended)

### Installation
1. Clone Repo
```
git clone https://github.com/im-ksc/coin-calculator-backend.git
```
2. Open terminal and change directory
```
cd coin-calculator-backend
```
3. Build project
```
mvn clean package
```
4. Run server (locally)
```
java -jar target/coin-calculator-backend-1.0-SNAPSHOT.jar server config.yml
```
5. Test API request
- Windows cmd
```
curl -X POST http://localhost:8080/api/coin/calculate ^
 -H "Content-Type: application/json" ^
 -d "{\"target\": 11, \"coinDenominations\": [1, 2, 5]}"
```
- Bash
```
curl -X POST http://localhost:8080/api/coin/calculate \
 -H "Content-Type: application/json" \
 -d "{\"target\": 11, \"coinDenominations\": [1, 2, 5]}"
```
Expected output [1.0, 5.0, 5.0]

---

## 🐳 Docker method to run locally
### Prerequisites
- Install [Docker Engine](https://docs.docker.com/engine/install/) on local machine

1. Build Docker Image
```
docker build -t coin-calculator-backend .
```
2. Run Docker Container
```
docker run -d --name coin-calculator-backend -p 8080:8080 coin-calculator-backend
```
3. Test API request (Refer to above Windows cmd and Bash commands)

---

## 🐳 Docker method to run on ☁️ Cloud 
### Prerequisites
- [DockerHub account](https://hub.docker.com/)
- Install [Docker Engine](https://docs.docker.com/engine/install/) on server VM

1. Build Docker Image
```
docker build -t {your-docker-username}/coin-calculator-backend:latest .
```
2. Push to DockerHub (Login if required)
```
docker push {your-docker-username}/coin-calculator-backend:latest
```
2. Run Docker Container
```
docker run -d --name coin-calculator-backend -p 8080:8080 {your-docker-username}/coin-calculator-backend:latest
```
3. Test API request (Refer to above Windows cmd and Bash commands)
Replace **localhost:8080** with the **Host Url**. Example as below
```
curl -X POST http://123.12.123.123:8080/api/coin/calculate ^
 -H "Content-Type: application/json" ^
 -d "{\"target\": 11, \"coinDenominations\": [1, 2, 5]}"
```
---

## Tech Stack

- Dropwizard v4.0.14
- Spotless v2.44.5 (Lint tool)
- Sonatype v3.2.0 (SCA tool)
- JaCoCo v0.8.13 (Code coverage)
