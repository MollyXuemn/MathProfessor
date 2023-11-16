# Overview

This is a class check application which allows you to check if the class has been cancelled.


## Getting Started

### Prerequisites

- You need to install the following software:
  - Java JDK 19
  - Maven 3.9.5
  - Spring Boot 3.1.5


### Installation

1. Clone the repo
   ```shell
   git clone git@github.com:MollyXuemn/MathProfessor.git
   ```

2. Build and run the project (With intellij)
   ```shell
   mvn spring-boot:run
   ```
   or you can launch the API via your IDE (in DEBUG if you want).


### Usage
- Test the API with Postman or curl.
1. You can test if a class has been cancelled via the url `http://localhost:8080/class-status/single?threshold=5` (threshold can be any integer >= 0 ):

    The payload is a JSON object with the following fields:
    ```shell
    {
      "times": [-2,-1,0,1, 2, 3]
    }
    ```
   
2. You can test the percentage of cancelled class in a week via the url `http://localhost:8080/class-status/weekly?threshold=2` :

    The payload is a JSON object with the following fields:
    ```shell
    {
      "week": [
      [-1,0,1, 2, 3],
      [4, 5, 6],
      [7, 8, 9]
      ]
    }
    ```

- If the payload is not valid, a http error must be returned (4xx/5xx).



