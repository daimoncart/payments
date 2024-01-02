
# Payment Application

## Introduction
Welcome to the Payment Application! This application is designed to simulate payment transactions and notify you via email about the success or failure of these transactions.

### Key Features
- Simulates payment transactions.
- Sends email notifications regarding transaction outcomes.
- Accounts are preloaded with a default balance of €10,000, which resets upon restarting the application.

## Setup and Configuration

### Prerequisites
Before running the application, please ensure you have received the complete `application.yml` file from the person who introduced you to this repository. This file contains essential configurations, including the password for the email account used to send notifications.

### Running the application
The application uses the default profile, so no specific configuration is necessary. Simply run the main() method.

### App version
This application is running Java 17 and Spring Boot 3.

### Email Notifications
- After sending an email through the application, please check the spam folder, as the email might be redirected there.
- The application uses the email address in the `application.yml` file for sending out notifications.

## Using the Application
There are two endpoints - _/authenticate_ and _/payments_. 
- You use the first one to generate authentication token. There are two fields to be filled in. The email is important part, it is used for sending transaction details later. The password field is there, and it should be filled in as well, but it is not being used. Just make sure it's at least 8 characters long.
- The second endpoint is used to simulate the payment. Please use the same email address as in the first endpoint, and play with the amount to test the application. The other fields should be filled in dilligently, but they don't affect the functionality. Don't forget the token you generated in the first step. By default, tokens expire in 30mins.

### Postman Collection
- A Postman collection is available in the root directory of the project.
- As mentioned previously, you will need to specify the recipient's email address for both requests to direct the payment summaries accordingly.

### Swagger UI
- Alternatively, you can use Swagger UI for an interactive API documentation and testing experience.

## Request examples
### /authenticate endpoint
`{
 "username": "paul@morphy.com",
 "password": "e4e5Kf3Kc6"
}`
### /payments endpoint
`Authentication: "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNzA0MTcxMTg4LCJlbWFpbCI6InBhdWxAbW9ycGh5LmNvbSIsImF1dGhvcml0aWVzIjpbIlVTRVIiXX0.CkFlzSQKmf1i5Y7iAGMUxIIw6qnyl-DThMotADtdcRs"`

`{
 "amount": 500.25,
 "description": "Initial payment",
 "payer": {
  "firstName": "Paul",
  "lastName": "Morphy",
  "email": "paul@morphy.com" 
  },
  "beneficiary": {
    "firstName": "Peter",
    "lastName": "Svidler",
    "accountNo": "LVxxPARX1234567890123"
  }
}`
