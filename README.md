# TestRestAPI-HotelBooking

Create REST application Hotel booking

Tech Stack:
o	Java
o	MySQL
o	Spring Boot
o	Hibernate
o	JPA
o	Lombok
For checking API –Using Postman Client 
API-
1.Create User (Post)- http://localhost:8080/api/users/
Input- user_Id(generate)
email,first_name, last name

2.Book Room API (Post)-   http://localhost:8080/api/users/{user_id}/booking/
Input-user id , guest count, check-in, check-out
Output-Reservation Id (Unique identifier), Status

3.Retrieving Reservation Details  (get)- http://localhost:8080/api/users/{user_id}/booking/{res_id}
Input- Reservation Id (Unique identifier )output-status

4. Cancelled booking (PUT) – http://localhost:8080/api/users/{user_id}/booking/{res_id}
Input – reservation id

Database-
User-
userid (Long)- primary key
Email (String)
first name (String)
last name (String)

Booking -
reservation_id (Long)- primary key 
user_id(Foreign Key-User)
chyeck_in_date(LocalDate)
check_out_date(LocalDate)
booking_status( String)
no_guest(int)
Compilation 
1.	First Download the zip folder then import project in IDE.
2.	Create one database in MySQL Workbench 
3.	Add database name in application. Properties also add MySQL username and password
4.	Run the application.

Running the application should not require a standalone application server (application should run on embedded tomcat server)
Test Run - Check the different test scenario
All API are working and checked through PostMan.





