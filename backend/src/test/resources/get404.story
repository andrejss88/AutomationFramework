
Scenario: when a client app sends a GET request to a non-existing endpoint, the service returns 404 Error

Given an endpoint that does not exist
When client app sends a GET request
Then github responds with 404 Error