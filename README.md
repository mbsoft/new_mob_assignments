# Skateboard Data Accessor
Backend REST API to help skateboarders to share their 'boards'

The microservice can be run through Gradle or Maven: https://spring.io/guides/gs/spring-boot/#_run_the_application

## Implementation Details
**Technologies Used**
- Java
- Spring boot
- Gradle
- Mockito

**Assumptions Made**

- In-memory database because the application is for demo purposes only
- To upload a skateboard to the marketplace you must be a registered owner
- You cannot search for skateboards that are not marked available

##  Demo
Swagger documentation is available at /swagger-ui.html
Test out the endpoints there!

Sample Requests

1. Create an owner with skateboards

```
{
 	"name": "Kenan Alkiek",
 	"boards": [
 		{
 			"brand": "FreshPark",
 			"weight": "2.2",
 			"height": "30.4",
 			"width": "15.4",
 			"length": "18.9",
 			"available": true
 		}, 
 		{
 			"brand": "Madrid Skateboards",
 			"weight": "5.7",
 			"height": "28.5",
 			"width": "16.7",
 			"length": "33.2",
 			"available": false
 		}
 	]
 }
```

2. Search the marketplace through board specs (/borrower/search)

```
{
  	"minLength": "13",
  	"maxLength": "25",
  	"minHeight": "30"
 }
```
 
## Requirements
1. Design your API in a RESTful way and respond with JSON. **_Done_**

2. Make sure your code has tests **_Done_**

3. Consider some form of logging **_Done_**

4. Write the code and design your system to be as realistic and production-ready as possible. Follow best practices and 
focus on quality. **_Done_**

5. A skateboard might have the following attributes: name of owner, brand, weight, length, location, timestamp and any 
other attribution you deem necessary. **_Done_**

6. Add endpoints that support your solution. **_Done_**

7. Bonus points for adding any kind of front-end that can be used to visualize available boards. **_Done_**
