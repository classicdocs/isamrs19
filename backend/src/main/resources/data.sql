-- destinacije
insert into destination (id,airport,country,name,zip) values (1,'John F. Kennedy', 'USA', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Serbia', 'Belgrade', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'Heathrow', 'Englend', 'London', 'LHR');


-- aviokompanije
insert into airline_company (id,description, address, name) values (1, 'Cheap flights!', 'Serbia/Belgrade', 'AirSerbia');
insert into airline_company (id,description, address, name) values (2, 'Air France is a french airline company', 'France/Paris', 'AirFrance');

-- lista destinacija u aviokompaniji
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "New York JFK");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "Belgrade BGD");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "London LHR");

--avion
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols)
 values (1, 'Boing747',1,2,1,2,1,2);

--lista aviona u aviokompaniji
insert into airline_company_airplanes(airline_company_id, airplanes_id) values (1,1);

--let
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time_hours, flight_time_minutes,landing_date,landing_time,airline_company_id,final_destination_id,start_destination_id, airplane_id, ticket_price_first, ticket_price_business, ticket_price_economy)
 VALUES (1,'2019-04-28','22:00',1700,3,30,'2019-04-29','01:30',1,2,3,1,500,200,100);
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time_hours, flight_time_minutes,landing_date,landing_time,airline_company_id,final_destination_id,start_destination_id, airplane_id, ticket_price_first, ticket_price_business, ticket_price_economy)
 VALUES (2,'2019-05-03','15:00',1700,3,0,'2019-05-03','18:00',1,3,2,1,500,200,100);

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

-- hotel
insert into hotel(id,name,address,description,num_of_floors,rooms_by_floor) values (1,'Moscow','Serbia/Belgrade/Bulevar 55','Nice view from hotel',4,15);
insert into hotel(id,name,address,description,num_of_floors,rooms_by_floor) values (3,'Kasina','Sebia/Belgrade/bb','Hotel with rich history',2,15);
insert into hotel(id,name,address,description,num_of_floors,rooms_by_floor) values (4,'Parisjen','France/Paris/Old square 52','Best in Paris',4,30);
insert into hotel(id,name,address,description,num_of_floors,rooms_by_floor) values (2,'Sheraton','Serbia/Novi Sad/Futoski put 76','Known for good services',3,8);


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
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RegisteredUser',1,'adresa','email','ime','prezime','user1','12345','5154',1,null,null,null, FALSE);
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RegisteredUser',2,'adresa1','email1','ime1','prezime1','user2','12345','51541',1,null,null,null, FALSE);
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RegisteredUser',7,'adresa6','email6','ime6','prezime6','user3','12345','51546',1,null,null,null, FALSE);

--rentacar admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('RentACarAdmin',3,'adresa2','email2','ime2','prezime2','rentacaradmin','12345','51542',2,1,null,null, FALSE);

--airline company admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('AirlineCompanyAdmin',4,'adresa3','email3','ime3','prezime3','airlineadmin','12345','51543',3,null,1,null, FALSE);

--hotel admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('HotelAdmin',5,'adresa4','email4','ime4','prezime4','hoteladmin','12345','51544',4,null,null,1, FALSE);

--system admini
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('SystemAdmin',6,'adresa5','email5','ime5','prezime5','systemadmin','12345','51545',5,null,null,null, FALSE);

--dodavanje filijala
insert into branch_offices (id, branches) values (1,'Belgrade');
insert into branch_offices (id, branches) values (1,'Paris');
insert into branch_offices (id, branches) values (1,'London');

-- spratovi
insert into hotel_floor(id,level,max_rooms) values (1,1,15);
insert into hotel_floor(id,level,max_rooms) values (2,2,15);
insert into hotel_floor(id,level,max_rooms) values (3,3,15);
insert into hotel_floor(id,level,max_rooms) values (4,4,15);
--
--insert into hotel_floor(id,level,max_rooms) values (5,1,15);
--insert into hotel_floor(id,level,max_rooms) values (6,2,15);
--
--insert into hotel_floor(id,level,max_rooms) values (7,1,30);
--insert into hotel_floor(id,level,max_rooms) values (8,2,30);

-- hoteli_spratovi
insert into hotel_floors(hotel_id,floors_id) values (1,1);
insert into hotel_floors(hotel_id,floors_id) values (1,2);
insert into hotel_floors(hotel_id,floors_id) values (1,3);
insert into hotel_floors(hotel_id,floors_id) values (1,4);

--insert into hotel_floors(hotel_id,floors_id) values (2,5);
--insert into hotel_floors(hotel_id,floors_id) values (2,6);
--
--insert into hotel_floors(hotel_id,floors_id) values (3,7);
--insert into hotel_floors(hotel_id,floors_id) values (3,8);

-- sobe
-- PRVI SPRAT
insert into room(id,number_of_beds,room_number) values (1, 2, 1);
insert into room(id,number_of_beds,room_number) values (2, 2, 2);
insert into room(id,number_of_beds,room_number) values (3, 3, 3);
insert into room(id,number_of_beds,room_number) values (4, 4, 4);
-- DRUGI SPRAT
insert into room(id,number_of_beds,room_number) values (5, 1, 1);
insert into room(id,number_of_beds,room_number) values (6, 4, 2);
insert into room(id,number_of_beds,room_number) values (7, 3, 5);
insert into room(id,number_of_beds,room_number) values (8, 4, 10);

--insert into room(id,number_of_beds,room_number,hotel_floor_id) values (9, 1, 1, 5);
--insert into room(id,number_of_beds,room_number,hotel_floor_id) values (10, 4, 2, 5);
--insert into room(id,number_of_beds,room_number,hotel_floor_id) values (11, 1, 3, 6);
--insert into room(id,number_of_beds,room_number,hotel_floor_id) values (12, 4, 9, 6);

--spratovi sobe
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,1);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,2);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,3);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,4);

insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,5);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,6);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,7);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,8);

--insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (5,9);
--insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (5,10);
--insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (6,11);
--insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (6,12);
