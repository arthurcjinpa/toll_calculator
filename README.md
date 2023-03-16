
# Trip Toll Calculator

In this assessment, I have tried to implement a trip toll calculator using Java and Spring framework.



## How to run Locally

- You must have IDE, such as IntellijIdea or Eclipse

- In IDE create a new project from version control and paste my projects HTTPS: https://github.com/arthurcjinpa/toll_calculator.git

- For this project, I used JDK 11

- If you get an error connected with maven you could try to navigate to the root directory of the project in your terminal and call this command:

```bash
  mvn clean install
```
You won't need anything else. But please let me know if you have any problems or questions.
## How to call GET request and calculate the distance and cost of the trip

#### Send this request in your tool for testing API (such as Postman, Insomnia or just call it in your browser) and paste your two connected locations (order does not affect) from interchanges.json as request params by calling

```http
  GET /toll/calculate?firstLocation=QEW&secondLocation=Dundas Street
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `firstLocation`      | `String` | **Required**. Input first location |
| `secondLocation`      | `String` | **Required**. Input second location |

- Full request would look like this: 

```http
  http://localhost:8080/toll/calculate?firstLocation=QEW&secondLocation=Dundas Street
```

You will get a Data Transfer Object of the calculated result

It would look like this:

    {
    "distance": 6062,
    "cost": "$1,515.50"
    }

#### Also, If you want to change interchanges.json in resources or load the new one, make sure that it would have the same name ("interchanges").




## Tech Stack

**Frameworks:** Spring, Junit

**Tools:** Github, Postman, IntellijIdea


## Authors

Please, feel free to message me if you encounter any kind of problem or have something to add/edit!
- [@arthurcjinpa](https://www.github.com/arthurcjinpa)

