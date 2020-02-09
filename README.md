# Skateboard Data Accessor
Backend REST API to help skateboarders to share their 'boards'

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

`{
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
 }`


2. Search the marketplace through board specs (/borrower/search)

`{
  	"minLength": "13",
  	"maxLength": "25",
  	"minHeight": "30"
 }`