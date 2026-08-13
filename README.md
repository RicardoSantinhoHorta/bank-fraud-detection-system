# Bank Fraud Detection System

A backend application for processing bank transactions and detecting potentially fraudulent activity using a machine learning model.

The project combines a **Java Spring Boot backend** with a **Python machine learning service**. Transactions are analysed using transaction characteristics and a trained **Random Forest** model, which returns a fraud risk score used by the backend to approve or reject transactions.

## Architecture

The application is divided into two main components:

### Spring Boot Backend

Responsible for:

- Managing bank accounts
- Creating and retrieving transactions
- Applying transaction rules
- Communicating with the fraud detection service
- Determining whether transactions are approved or rejected
- Managing account balances
- Persisting data in PostgreSQL
- Handling application errors and validation

### Python AI Service

Responsible for:

- Loading the trained fraud detection model
- Receiving transaction information from the Java backend
- Predicting the probability of fraud
- Returning a risk score through a REST API

### Application Flow

```text
Client
  |
  | HTTP Request
  v
Spring Boot API
  |
  v
TransactionService
  |
  v
FraudService
  |
  v
AiClient
  |
  | POST /predict
  v
Python FastAPI Service
  |
  v
Random Forest Model
  |
  | riskScore
  v
Spring Boot
  |
  v
APPROVED / REJECTED
  |
  v
PostgreSQL
```

## Technologies

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven

### Database

- PostgreSQL

### Machine Learning

- Python
- FastAPI
- scikit-learn
- pandas
- Random Forest Classifier

### Testing

- JUnit
- Mockito

## Fraud Detection

When a transaction is created, the backend calculates several characteristics that are used by the fraud detection model:

- Transaction amount
- Transaction amount level
- Transaction type (`DOMESTIC` or `INTERNATIONAL`)
- Tax haven risk level

The Java backend sends these values to the Python service.

Example:

```json
{
  "amount": 15000,
  "transactionAmountLevel": "HIGH",
  "taxHavenRiskLevel": "HIGH",
  "transactionType": "INTERNATIONAL"
}
```

The machine learning service returns a fraud risk score:

```json
{
  "riskScore": 0.93
}
```

The backend converts this score into a risk level:

- `LOW`
- `MEDIUM`
- `HIGH`

The risk level is then used to determine whether the transaction should be approved or rejected.

## Transaction Flow

When a transaction is requested:

1. The sender and receiver accounts are retrieved.
2. The sender's available balance is validated.
3. Transaction characteristics are calculated.
4. The transaction is sent to the fraud detection service.
5. The AI model calculates the fraud risk score.
6. The backend determines the final risk level.
7. The transaction is either `APPROVED` or `REJECTED`.
8. If approved, the funds are transferred between the accounts.
9. The transaction is stored in PostgreSQL.

Rejected transactions do not modify account balances.

## REST API

### Accounts

Create an account:

```http
POST /accounts
```

Retrieve an account:

```http
GET /accounts/{id}
```

### Transactions

Create and analyse a transaction:

```http
POST /transactions
```

Example request:

```json
{
  "senderAccountId": 1,
  "receiverAccountId": 2,
  "amount": 30.00
}
```

Retrieve a transaction:

```http
GET /transactions/{id}
```

## Running the Project

### Requirements

Make sure the following are installed:

- Java 21
- Maven
- Python
- PostgreSQL

### 1. Configure PostgreSQL

Create a PostgreSQL database and configure the database connection in your local `application.properties`.

Database credentials should not be committed to the repository.

### 2. Start the AI Service

Navigate to the `ai` directory and activate the Python virtual environment.

Install the required dependencies:

```bash
pip install -r requirements.txt
```

Start the FastAPI service:

```bash
uvicorn app:app --reload --port 5000
```

The AI service will run on:

```text
http://localhost:5000
```

### 3. Start the Spring Boot Application

From the project root:

```bash
mvn spring-boot:run
```

The backend will run on:

```text
http://localhost:8080
```

Requests can then be tested using Postman or another HTTP client.

## Testing

Run the Java tests with:

```bash
mvn test
```

The test suite covers important business rules such as:

- Transaction amount classification
- Tax haven risk classification
- Domestic and international transaction classification
- Fraud risk score classification
- Transaction approval and rejection logic

## Error Handling

The application handles cases such as:

- Account not found
- Insufficient account balance
- Fraud detection service unavailable

Custom exceptions are used to represent application-specific failures.

## Project Structure

```text
bank-fraud-detection-system/
├── src/
│   ├── main/
│   │   ├── java/fraudetection/
│   │   │   ├── account/
│   │   │   ├── transaction/
│   │   │   ├── fraud/
│   │   │   └── exception/
│   │   └── resources/
│   └── test/
│
├── ai/
│   ├── data/
│   ├── models/
│   ├── app.py
│   ├── predictor.py
│   ├── train.py
│   └── requirements.txt
│
└── pom.xml
```

## Machine Learning Model

The fraud detection component currently uses a **Random Forest Classifier**.

The model is trained separately using `train.py` and stored as a serialized model. The FastAPI service loads the trained model and uses it to generate predictions.

The current dataset is intended for demonstration and educational purposes. The resulting fraud probabilities should not be interpreted as suitable for real-world banking decisions.

## Future Improvements

Possible future improvements include:

- Training with a larger real-world fraud dataset
- Adding additional transaction features
- Improving model evaluation and tuning
- Authentication and authorization
- Dockerizing the Java, Python and PostgreSQL services
- Integration and end-to-end testing
- OpenAPI/Swagger documentation

## Purpose

This project was developed as a practical exercise in building a backend system that combines:

- REST APIs
- Layered Spring Boot architecture
- Relational databases
- Business rules
- Machine learning
- Communication between Java and Python services
- Automated testing
- Error handling

The focus of the project is the integration between traditional backend development and a separate machine learning service rather than implementing a production-ready banking platform.