1. Script for database with records locate in resources(upt_db.sql)
2. Set up your database username & password in application.properties for Spring Boot

#Endpoints for categories
- **`create:`****`Http method POST`** /category/add 
-- example json body: {"id":"0","name": "name", "description":"description"} or without id field
- **`read:`****`Http method GET`** /category/id (id - id of your category in database)
- **`update:`****`Http method PUT`** /category/update
-- example json body: the same as create but with particular id
- **`delete:`****`Http method DELETE`** /category/delete/id (id - id of your category in database)
- **`find:`****`Http method GET`** /category/find?keyword=keyword

#Endpoints for products
- **`create:`****`Http method POST`** /product/add 
-- example json body: {"name": "your name","price":"0", "description":"descr","category": {"id":"0"}}
- **`read:`****`Http method GET`** /product/id (id - id of your product in database)
- **`update:`****`Http method PUT`** /product/update
-- example json body: {"id":"0", "name": "your name","price":"0", "description":"descr","category": {"id":"0"}}
- **`delete:`****`Http method DELETE`** /product/delete/id (id - id of your product in database)


I used ResponseEntityExceptionHandler for simple validation (existing) values from database! He handle
only one Exception (NoSuchElementException.class) and display message for a client. 
