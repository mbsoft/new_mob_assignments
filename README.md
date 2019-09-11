### Skateboard REST API

This project defines a very simple REST API for sharing Skateboards. It defines the following endpoints:

- **GET /boards/** - List All Skateboards
- **POST /boards/** - Create New Skateboard
- **GET /boards/:id/** - Get One Skateboard
- **PUT /boards/:id/** - Update One Skateboard
- **DELETE /boards/:id/** - Delete One Skateboard
- **GET /boards/available/** - List All Available Skateboards

When retrieving Skateboards the will have the following JSON members:
```json
{
    "id": 1,
    "owner": "Lorem",
    "brand": "Element",
    "weight": 7.5,
    "location": "Brighton, MI",
    "is_available": true,
    "created_at": "2019-09-11T18:10:19.077217Z",
    "updated_at": "2019-09-11T19:27:44.038182Z"
}
```

When creating and updating Skateboards you only need to include the following JSON members:
```json
{
    "id": 1,
    "owner": "Lorem",
    "brand": "Element",
    "weight": 7.5,
    "location": "Brighton, MI",
    "is_available": true
}
```

#### Working with this solution

This project assumes that the user has Docker and docker-compose installed on their system

##### To Run tests

```shell
docker-compose run www ./manage.py test
```

##### To Run Development Server

```shell
docker-compose run www ./manage.py migrate
docker-compose up
```

##### To Run User stories
As a skateboard owner I want to be able to add my individual board to a skateboard sharing marketplace.
```shell
curl -X POST -H "Content-Type: application/json" http://localhost:8000/boards/ --data '{"owner": "Lorem", "brand": "Element", "weight": 7.5, "location": "Brighton, MI", "is_available": true}'
```
As a skateboard owner I want to be able to indicate that my board is available or unavailable for sharing (modify the is_available member)
```shell
curl -X PUT -H "Content-Type: application/json" http://localhost:8000/boards/1/ --data '{"id": 1, "owner": "Lorem", "brand": "Element", "weight": 7.5, "location": "Brighton, MI", "is_available": true}'
```
As a skateboard owner I want to be able to modify the details for the board that I share.
```shell
curl -X PUT -H "Content-Type: application/json" http://localhost:8000/boards/1/ --data '{"id": 1, "owner": "Lorem", "brand": "Element", "weight": 7.0, "location": "Brighton, MI", "is_available": true}'
```
As a skateboard borrower, I want to see a list of available boards
```shell
curl http://localhost:8000/boards/available/
```
