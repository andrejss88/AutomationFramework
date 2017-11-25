
Narrative:
As a client app
I want to receive a successful response when I hit endpoints that do not require authentication

Scenario: when a client app sends a GET request to an endpoint not requiring authentication, the service returns OK 200

Given base github endpoint
When client app sends a request
Then github responds with 200 OK