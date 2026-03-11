Prerequisites: Java 17+, Maven, PostgreSQL

This personal Blog embodies my first attempt to work with SpringBoot, Thymeleaf as well as JPA.

The Blog has to be connected to a PostgreSQL Database. The schema can be found in the schema.sql 
file in resources. Create a application.properties file in resources and enter the data as pre-
sented in the application.properties.example file.

The blog has a public view as well as a private-admin view, which is secured by Spring Security 
and basic-authentication. The admin-view has a minimalist design and enables basic CRUD operations 
for all articles. 
