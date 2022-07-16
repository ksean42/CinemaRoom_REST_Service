# CinemaRoom_REST_Service

Learned topics:
:white_check_mark: REST
:white_check_mark: Spring Boot
:white_check_mark: MVC
:white_check_mark: Http
:white_check_mark: JSON
:white_check_mark: Postman
:white_check_mark: Gradle


In this project, I created a simple Spring REST service that will help manage a small movie theatre. Handle HTTP requests in controllers, create services and respond with JSON objects.

Usage:
**/seats** endpoint that handles GET request and returns information about movie theatre
The response contains information about the rows, columns, and available seats. The response is a JSON object and has the following format:

```json{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1
      },

      {
      ......
      },

      {
         "row":5,
         "column":5
      },
      {
         "row":5,
         "column":6
      }
   ]
}
```
**/purchase** endpoint that handles POST requests and marks a booked ticket as purchased.

A request should contain the following data:
row — the row number;
column — the column number.

If the purchase is successful, the response body will be as follows:
```
"token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
```

If the seat is taken, controller respond with a 400 (Bad Request) status code. The response body contains the following:
```{
    "error": "The ticket has been already purchased!"
}
```

**/return** endpoint, handles POST requests and allow customers to refund their tickets.

The request has the token feature that identifies the ticket in the request body. Once you have the token, you need to identify the ticket it relates to and mark it as available. The response body will be as follows:
```{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```
The returned_ticket should contain the information about the returned ticket.

If cannot identify the ticket by the token, program responds with a 400 status code and the following response body:
```
{
    "error": "Wrong token!"
}
```

# Examples
Example 1: a correct POST /purchase request

Request body:
```{
    "row": 3,
    "column": 4
}
```
Response body:
```{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
    "ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
```
Example 2: POST /return with the correct token

Request body:
```{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response body:
```{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
```
Example 3: POST /return with an expired token

Request body:
```{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```
Response body:
```{
    "error": "Wrong token!"
}
```

**/stats** endpoint that handles POST requests with URL parameters. If the URL parameters contain a password key with a super_secret value, returns the movie theatre statistics in the following format:

```{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```
If the parameters don't contain a password key or a wrong value has been passed, respond with a 401 status code. The response body should contain the following:

```{
    "error": "The password is wrong!"
}
```
