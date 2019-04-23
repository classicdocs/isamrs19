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
 VALUES (1,'2019-03-16','11:02',100,3,50,'2019-03-20','11:11',1,2,3,1,500,400,300);
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time_hours, flight_time_minutes,landing_date,landing_time,airline_company_id,final_destination_id,start_destination_id, airplane_id, ticket_price_first, ticket_price_business, ticket_price_economy)
 VALUES (2,'2019-03-16','15:02',100,2,0,'2019-07-20','11:11',1,3,2,1,500,400,300);

--lista letova u aviokompaniji
insert into airline_company_flights(airline_company_id, flights_id) values (1, 1);
insert into airline_company_flights(airline_company_id, flights_id) values (1, 2);


--lista letova za avion
insert into airplane_flight(airplane_id,flight_id) values(1,1);
insert into airplane_flight(airplane_id,flight_id) values(1,2);


--sedista
insert into seat(id, col_num, row_num,taken) values (1, 1, 1, FALSE);
insert into seat(id, col_num, row_num,taken) values (2, 1, 2, FALSE);
insert into seat(id, col_num, row_num,taken) values (3, 1, 1, FALSE);
insert into seat(id, col_num, row_num,taken) values (4, 1, 2, FALSE);
insert into seat(id, col_num, row_num,taken) values (5, 1, 1, FALSE);
insert into seat(id, col_num, row_num,taken) values (6, 1, 2, FALSE);

insert into seat_row(id) values (1);
insert into seat_row(id) values (2);
insert into seat_row(id) values (3);
insert into seat_row(id) values (4);
insert into seat_row(id) values (5);
insert into seat_row(id) values (6);

insert into seat_row_seats(seat_row_id, seats_id) values (1, 1);
insert into seat_row_seats(seat_row_id, seats_id) values (2, 2);
insert into seat_row_seats(seat_row_id, seats_id) values (3, 3);
insert into seat_row_seats(seat_row_id, seats_id) values (4, 4);
insert into seat_row_seats(seat_row_id, seats_id) values (5, 5);
insert into seat_row_seats(seat_row_id, seats_id) values (6, 6);

--sedista za let id 3
insert into flight_seats_first(flight_id, seats_first_id)  values (1,1);
insert into flight_seats_first(flight_id, seats_first_id)  values (1,2);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (1,3);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (1,4);
insert into flight_seats_business(flight_id, seats_business_id)  values (1,5);
insert into flight_seats_business(flight_id, seats_business_id)  values (1,6);

--sedista
insert into seat(id, col_num, row_num, taken) values (7, 1, 1, FALSE);
insert into seat(id, col_num, row_num, taken) values (8, 1, 2, FALSE);
insert into seat(id, col_num, row_num, taken) values (9, 1, 1, FALSE);
insert into seat(id, col_num, row_num, taken) values (10, 1, 2, FALSE);
insert into seat(id, col_num, row_num, taken) values (11, 1, 1, FALSE);
insert into seat(id, col_num, row_num, taken) values (12, 1, 2, FALSE);

insert into seat_row(id) values (7);
insert into seat_row(id) values (8);
insert into seat_row(id) values (9);
insert into seat_row(id) values (10);
insert into seat_row(id) values (11);
insert into seat_row(id) values (12);

insert into seat_row_seats(seat_row_id, seats_id) values (7, 7);
insert into seat_row_seats(seat_row_id, seats_id) values (8, 8);
insert into seat_row_seats(seat_row_id, seats_id) values (9, 9);
insert into seat_row_seats(seat_row_id, seats_id) values (10, 10);
insert into seat_row_seats(seat_row_id, seats_id) values (11, 11);
insert into seat_row_seats(seat_row_id, seats_id) values (12, 12);

--sedista za let id 2
insert into flight_seats_first(flight_id, seats_first_id)  values (2,7);
insert into flight_seats_first(flight_id, seats_first_id)  values (2,8);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (2,9);
insert into flight_seats_economy(flight_id, seats_economy_id)  values (2,10);
insert into flight_seats_business(flight_id, seats_business_id)  values (2,11);
insert into flight_seats_business(flight_id, seats_business_id)  values (2,12);

--hotel
insert into hotel(id,name,address,description) values (1,'Moskva','Srbija/Beograd/Bulevar 55','Visok hotel');
insert into hotel(id,name,address,description) values (2,'Seraton','Srbija/Novi Sad/Futoski put 76','Skup hotel');
insert into hotel(id,name,address,description) values (3,'Kasina','Srbija/Beograd/bb','Poznat hotel');
insert into hotel(id,name,address,description) values (4,'Parisjen','Francuska/Pariz/Ajfelovog Tornja 45','Z v z');
------------------------------------------------------------------------------------------------------------------------
--rentacar servis 1
insert into rentacar (id,name,address,promotional_description) values (1,'Drive X', 'North Road 7 London','DriveX has been continually growing ever since its foundation in 1989 in London, Great Britain, with branches all over the country.At all our locations we offer customers affordable and trusted rent a car services.');
--rentacar servis 2
insert into rentacar (id,name,address,promotional_description) values (2,'EcoCar', '2nd Street 63 Paris','With over 60 years of experience in the industry, you can be sure that whether you are looking for a family car, sedan or prestige car for your business or leisure trips, EcoCar has the vehicle to meet every car rental need.Make the most out of our large range of services to make car rental experience a unique experience');
--vozilo 1
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating) values (99,'Test vehicle','Audi','A4','Sedan',4,2014,50,0);
--vozilo 1 u servis 1
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,99);


--tabela uloga
insert into role (id,role) values (1,'User');
insert into role (id,role) values (2,'RentACar Admin');
insert into role (id,role) values (3,'Airline Company Admin');
insert into role (id,role) values (4,'Hotel Admin');
insert into role (id,role) values (5,'System Admin');

--obicni korisnici
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RegisteredUser',1,'adresa','email','ime','prezime','obican korisnik','sifra','5154',1,null,null,null, FALSE);
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RegisteredUser',2,'adresa1','email1','ime1','prezime1','obican korisnik 2','sifra1','51541',1,null,null,null, FALSE);
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RegisteredUser',7,'adresa6','email6','ime6','prezime6','obican korisnik 3','sifra6','51546',1,null,null,null, FALSE);

--rentacar admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RentACarAdmin',3,'adresa2','email2','ime2','prezime2','rentacar admin','sifra2','51542',2,1,null,null, FALSE);

--airline company admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('AirlineCompanyAdmin',4,'adresa3','email3','ime3','prezime3','airline company admin','sifra3','51543',3,null,1,null, FALSE);

--hotel admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('HotelAdmin',5,'adresa4','email4','ime4','prezime4','hotel admin','sifra4','51544',4,null,null,1, FALSE);

--system admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('SystemAdmin',6,'adresa5','email5','ime5','prezime5','system admin','sifra5','51545',5,null,null,null, FALSE);

--dodavanje prijatelja
insert into user_friends (id, friends_id) values (1,2);

