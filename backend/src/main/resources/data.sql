-- destinacije
insert into destination (id,airport,country,name,zip) values (1,'Kenedi', 'SAD', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Srbija', 'Belgrade', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'london', 'Velika britanija', 'London', 'LON');

-- aviokompanije
insert into airline_company (id,description, address, name) values (1, 'Cheap flights', 'Kraljevica Marka 2', 'AirSerbia');
insert into airline_company (id,description, address, name) values (2, '', 'Oslobodjenja 3', 'AirFrance');

-- lista destinacija u aviokompaniji
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "New York");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "Belgrade");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "London");

--avion
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols)
 values (1, 'Boing747',1,2,1,2,1,2);

--lista aviona u aviokompaniji
insert into airline_company_airplanes(airline_company_id, airplanes_id) values (1,1);

--let
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time_hours, flight_time_minutes,landing_date,landing_time,airline_company_id,final_destination_id,start_destination_id, airplane_id, ticket_price_first, ticket_price_business, ticket_price_economy)
 VALUES (3,'2019-03-16','11:02',100,3,50,'2019-03-20','11:11',1,2,3,1,500,400,300);
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time_hours, flight_time_minutes,landing_date,landing_time,airline_company_id,final_destination_id,start_destination_id, airplane_id, ticket_price_first, ticket_price_business, ticket_price_economy)
 VALUES (2,'2019-03-16','15:02',100,2,0,'2019-07-20','11:11',1,3,2,1,500,400,300);

--lista letova u aviokompaniji
insert into airline_company_flights(airline_company_id, flights_id) values (1, 2);
insert into airline_company_flights(airline_company_id, flights_id) values (1, 3);

--lista letova za avion
insert into airplane_flight(airplane_id,flight_id) values(1,2);
insert into airplane_flight(airplane_id,flight_id) values(1,3);


--sedista
insert into seat(id, col_num, row_num,taken) values (1, 1, 1, FALSE);
insert into seat(id, col_num, row_num,taken) values (2, 1, 2, FALSE);
insert into seat(id, col_num, row_num,taken) values (3, 1, 1, FALSE);
insert into seat(id, col_num, row_num,taken) values (4, 1, 2, FALSE);
insert into seat(id, col_num, row_num,taken) values (5, 1, 1, FALSE);
insert into seat(id, col_num, row_num,taken) values (6, 1, 2, FALSE);

--sedista za let id 3
insert into flight_seats_first(flight_id, seats_first_id)  values (3,1);
insert into flight_seats_first(flight_id, seats_first_id)  values (3,2);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (3,3);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (3,4);
insert into flight_seats_business(flight_id, seats_business_id)  values (3,5);
insert into flight_seats_business(flight_id, seats_business_id)  values (3,6);

--sedista
insert into seat(id, col_num, row_num, taken) values (7, 1, 1, FALSE);
insert into seat(id, col_num, row_num, taken) values (8, 1, 2, FALSE);
insert into seat(id, col_num, row_num, taken) values (9, 1, 1, FALSE);
insert into seat(id, col_num, row_num, taken) values (10, 1, 2, FALSE);
insert into seat(id, col_num, row_num, taken) values (11, 1, 1, FALSE);
insert into seat(id, col_num, row_num, taken) values (12, 1, 2, FALSE);


--sedista za let id 2
insert into flight_seats_first(flight_id, seats_first_id)  values (2,7);
insert into flight_seats_first(flight_id, seats_first_id)  values (2,8);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (2,9);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (2,10);
insert into flight_seats_business(flight_id, seats_business_id)  values (2,11);
insert into flight_seats_business(flight_id, seats_business_id)  values (2,12);

--hotelAdmin
insert into hotel_admin(id,username,password) values (1,'Dragan','dzaja');
insert into hotel_admin(id,username,password) values (2,'Dragoslav','seki');
insert into hotel_admin(id,username,password) values (3,'Vladislav','pizon');

--hotel
insert into hotel(id,name,address,description) values (1,'Moskva','Srbija/Beograd/Bulevar 55','Visok hotel');
insert into hotel(id,name,address,description) values (2,'Seraton','Srbija/Novi Sad/Futoski put 76','Skup hotel');
insert into hotel(id,name,address,description) values (3,'Kasina','Srbija/Beograd/bb','Poznat hotel');
insert into hotel(id,name,address,description) values (4,'Parisjen','Francuska/Pariz/Ajfelovog Tornja 45','Z v z');

--hotel-hoteladmin
insert into hotel_admins(hotel_id,admins_id) values (1,1);
insert into hotel_admins(hotel_id, admins_id) values (1,2);
insert into hotel_admins(hotel_id, admins_id) values (4,3);

------------------------------------------------------------------------------------------------------------------------

--insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,
--price_per_day,average_rating) values ('1','Automobil','BMW','320','Sedan',5,2014,40,0);
--
--insert into rentacar (id,name,address,promotional_description) values ('1','test rentacar', 'adress unknown',
--'no promotional description');
