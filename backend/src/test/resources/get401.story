
Narrative:
As a client app
I must not be able to view content that requires authorization when I am not authenticated

Scenario: when a client app sends a GET request to an endpoint requiring authentication, the service returns 401 Error

Given an endpoint requiring authentication
When client app sends a GET request
Then github responds with 401 Error