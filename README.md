### Skateboard REST API

```$xslt
  ______  _                           _                               _
 / _____)| |              _          | |                             | |
( (____  | |  _  _____  _| |_  _____ | |__    ___   _____   ____   __| |   _____  ____   ____
 \____ \ | |_/ )(____ |(_   _)| ___ ||  _ \  / _ \ (____ | / ___) / _  |  (____ ||  _ \ |  _ \
 _____) )|  _ ( / ___ |  | |_ | ____|| |_) )| |_| |/ ___ || |    ( (_| |  / ___ || |_| || |_| |
(______/ |_| \_)\_____|   \__)|_____)|____/  \___/ \_____||_|     \____|  \_____||  __/ |  __/
                                                                                 |_|    |_|
```

This is SkateBoard app with REST API's developed in SpringBoot 2, Lombok, Maven and Swagger2


Please free to modify the server port. The current state uses 8081 as server port.

Please use below URL's to browse

- **For APi docs:** Please visit http://localhost:8081/v2/api-docs
- **To use swagger:** http://localhost:8081/swagger-ui.html#/
- Please use below JSON format to test 
    ```$xslt
    {
      "boardAvailable": true,
      "brand": "string",
      "id": 0,
      "length": 0,
      "ownerName": "string",
      "weight": 0
    }
    ```

* **Create skateboard:**
    - http://localhost:8081/api/v1/createSkateboard
        ```$xslt
         curl -X POST "http://localhost:8081/api/v1/createSkateboard" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"boardAvailable\": true, \"brand\": \"Nike\", \"id\": 4, \"length\": 14, \"ownerName\": \"Rohan\", \"weight\": 35}"
        ```
   
* **Get skateboards:**
    - http://localhost:8081/api/v1/skateboards
        ```$xslt
        curl -X GET "http://localhost:8081/api/v1/skateboards" -H "accept: application/json"
        ```
    - http://localhost:8081/api/v1/skateboards/3
        ```$xslt
        curl -X GET "http://localhost:8081/api/v1/skateboards/3" -H "accept: application/json"
        ```
    - http://localhost:8081/api/v1/skateboards/byBrand/Nike
        ```$xslt
        curl -X GET "http://localhost:8081/api/v1/skateboards/byBrand/Nike" -H "accept: application/json"
        ```
    - http://localhost:8081/api/v1/skateboards/byLength/24
        ```$xslt
        curl -X GET "http://localhost:8081/api/v1/skateboards/byLength/24" -H "accept: application/json"
        ```
    - http://localhost:8081/api/v1/skateboards/byWeight/48
        ```$xslt
        curl -X GET "http://localhost:8081/api/v1/skateboards/byWeight/48" -H "accept: application/json"
        ```
    
* **Update skateboard**
    - http://localhost:8081/api/v1/skateboards/2/Tom/Reebok/34/45/false
        ```$xslt
        curl -X PUT "http://localhost:8081/api/v1/skateboards/2/Tom/Reebok/34/45/false" -H "accept: application/json"
        ```
  
* **Delete skateboard**
    - http://localhost:8081/api/v1/skateboards/2
        ```$xslt
        curl -X DELETE "http://localhost:8081/api/v1/skateboards/2" -H "accept: application/json"
        ```

- **To Build app:**
    ```$xslt
    mvn compile
    ```
- **To install app:** 
    ```$xslt
    mvn install
    ```
- **To Run Tests:**
    ```$xslt
    mvn test
    ```
- **To Start app:**
    ```$xslt
    mvn spring-boot:run
    ```

- **Swagger UI preview:**
![Alt text](SkateBoard-Swagger-ui.png?raw=true "Swagger-UI")
