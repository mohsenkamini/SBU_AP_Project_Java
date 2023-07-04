# AliBaba Backend
## Introduction
This repo represents a backend API server, which communicates to clients over TCP. It was developed for a college project as a simulation of [alibaba](https://www.alibaba.ir/).

### Design

The overall Design is a simple client/server design. The server is completely stateless and stores everything in a statefull database.
All these components are Java Classes. Even the API requests/responses.

![Java Backend Design](https://github.com/AidaLf/SBU_AP_Project_Java/assets/77579794/db8f451d-38d5-4986-b329-372cf52e2f7d)


### Communication

The communication between the client and the server is done through custom API. API requests and responses are passed as JSON objects. Each API has some required fields: `username`, `method` and `route`.

here's an example:
~~~
{
    "method": "POST",
    "username": "mohsen",
    "route": "/user/signup/",
    "company": "IranAir",
    "payload": {
      "password": "changeme",
    }
}
~~~

The function of `method` and `route` concepts are borrowed from REST and mean exactly the same. They define what you want to do with the server.

For `POST`, `PUT` and `DELETE` requests, you need to have a `payload`, which is a JSON object that provides the required fields for the server to complete your request. In this case `password`.

> NOTE: You cannot send the JSON object prettified to the server, since the server will listen for an object only in one line. otherwise server won't recognize your request as a healthy JSON object.

>‌ You can use [this file](https://github.com/mohsenkamini/SBU_AP_Project_Java/blob/main/ExampleClient.java) as a client to send APIs and receive responses. it reads a JSON file (either prettified or not) as an API request.

### APIs
Currently a list of available APIs are accessible on the release note of this project. But overally, you can think of every Java object in this project as a JSON object. For example when you want to add a ticket to the database, you fill the `payload`'s value with a JSON object created by the fields of `Ticket` class:
~~~
{
    "method": "POST",
    "username": "mohsen",
    "company": "IranAir",
    "route": "/company/addticket/",
    "payload": {
        "ID": "98245",
        "price": 45363.46,
        "startDate": "2023.12.02",
        "endDate": "2023.12.03",
        "origin": "Do ghoz abad",
        "destination": "Tehran",
        "capacity": 130,
        "booked": 0
    }
}
~~~

### Conclusion
This stack could be extended to whatever needs you might have, to be easily communicating over TCP, with a simple API structure.


