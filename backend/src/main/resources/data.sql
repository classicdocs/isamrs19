--roles
insert into role (id,role) values (1,'User');
insert into role (id,role) values (2,'RentACar Admin');
insert into role (id,role) values (3,'Airline Company Admin');
insert into role (id,role) values (4,'Hotel Admin');
insert into role (id,role) values (5,'System Admin');
------------------------------------------------------------------------------------------------------------------------

-- users

-- Registered users
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RegisteredUser',1,'address1','email1@gmail.com','name1','lastname1','user1','12345','51542',1,null,null,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RegisteredUser',2,'address2','email2@gmail.com','name2','lastname2','user2','12345','51541',1,null,null,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RegisteredUser',3,'address3','email3@gmail.com','name3','lastname3','user3','12345','51546',1,null,null,null, FALSE, TRUE);

--Rentacar admins
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RentACarAdmin',4,'address4','email4@gmail.com','name4','lastname4','rentacaradmin','12345','51542',2,1,null,null, FALSE, TRUE);

--Airline-Company admins
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('AirlineCompanyAdmin',5,'address5','email5@gmail.com','name5','lastname5','airlineadmin1','12345','51543',3,null,1,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('AirlineCompanyAdmin',6,'address6','email6@gmail.com','name6','lastname6','airlineadmin2','12345','51543',3,null,2,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('AirlineCompanyAdmin',7,'address7','email7@gmail.com','name7','lastname7','airlineadmin3','12345','51543',3,null,3,null, FALSE, TRUE);

--Hotel admins
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('HotelAdmin',8,'adresa4','email4','ime4','prezime4','hoteladmin','12345','51544',4,null,null,4, FALSE, TRUE);

--System admin
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('SystemAdmin',9,'adresa5','email5','ime5','prezime5','systemadmin','12345','51545',5,null,null,null, FALSE, TRUE);
------------------------------------------------------------------------------------------------------------------------

-- map location
insert into map_location(id, latitude, longitude) values(1, 40.730610, -73.935242); -- New York
insert into map_location(id, latitude, longitude) values(2, 44.787197, 20.457273); -- Belgrade
insert into map_location(id, latitude, longitude) values(3, 51.509865, -0.118092); -- London
insert into map_location(id, latitude, longitude) values(4, 25.276987, 55.296249); -- Dubai
insert into map_location(id, latitude, longitude) values(5, 52.520008, 13.404954); -- Berlin
insert into map_location(id, latitude, longitude) values(6, 55.751244, 37.618423); -- Moscow
insert into map_location(id, latitude, longitude) values(7, -33.865143, 151.209900); -- Sydney
insert into map_location(id, latitude, longitude) values(8, 37.983810, 23.727539); -- Athens
insert into map_location(id, latitude, longitude) values(9, 48.864716, 2.349014); -- Paris
insert into map_location(id, latitude, longitude) values(10, -22.908333, -43.196388); -- Rio de Janeiro
------------------------------------------------------------------------------------------------------------------------

-- destinations
insert into destination (id,airport,country,name,zip) values (1,'John F. Kennedy', 'USA', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Serbia', 'Belgrade', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'Heathrow', 'Englend', 'London', 'LHR');
insert into destination (id,airport,country,name,zip) values (4,'Al Maktoum International', 'UAE', 'Dubai', 'DWC');
insert into destination (id,airport,country,name,zip) values (5,'Berlin-Tegel', 'Germany', 'Berlin', 'TXL');
insert into destination (id,airport,country,name,zip) values (6,'Sheremetyevo', 'Russia', 'Moscow', 'SVO');
insert into destination (id,airport,country,name,zip) values (7,'Kingsford Smith', 'Australia', 'Sydney', 'SYD');
insert into destination (id,airport,country,name,zip) values (8,'Eleftherios Venizelos', 'Greece', 'Athens', 'ATH');
insert into destination (id,airport,country,name,zip) values (9,'Charles de Gaulle', 'France', 'Paris', 'CDG');
insert into destination (id,airport,country,name,zip) values (10,'Galeao', 'Brasil', 'Rio de Janeiro', 'GIG');
------------------------------------------------------------------------------------------------------------------------

-- airline_companies
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (1, 'American Airlines is an american airline company!', 'USA/New York', 'American Airlines', 5, 1, 1);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (2, 'Air Serbia is a serbian airline company', 'Serbia/Belgrade', 'Air Serbia', 4.5, 1, 2);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (3, 'British Airways is a british airline company', 'UK/London', 'British Airways', 4, 1, 3);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (4, 'Emirates Airlines is a UAE airline company', 'UAE/Dubai', 'Emirates Airlines', 5, 1, 4);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (5, 'Lufthansa is a germany airline company', 'Germany/Berlin', 'Lufthansa', 5, 1, 5);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (6, 'Aeroflot is a russian airline company', 'Russia/Moscow', 'Aeroflot', 5, 1, 6);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (7, 'Australian Airlines is an australian airline company', 'Australia/Sydney', 'Australian Airlines', 4, 1, 7);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (8, 'Greece Airlines is a greece airline company', 'Greece/Athens', 'Greece Airlines', 3, 1, 8);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (9, 'Air France is a french airline company', 'France/Paris', 'AirFrance', 4, 1, 9);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (10, 'Gol Airlines is a brasilian airline company', 'Brasil/Rio de Janeiro', 'Gol Airlines', 3.5, 1, 10);
------------------------------------------------------------------------------------------------------------------------

-- list of destinations of airline_company
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'New York JFK');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Belgrade BGD');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'London LHR');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Dubai DWC');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Berlin TXL');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Moscow SVO');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Sydney SYD');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Athens ATH');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Paris CDG');
insert into airline_company_destinations(airline_company_id, destinations ) values (1, 'Rio de Janeiro GIG');

insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'New York JFK');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Belgrade BGD');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'London LHR');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Dubai DWC');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Berlin TXL');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Moscow SVO');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Sydney SYD');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Athens ATH');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Paris CDG');
insert into airline_company_destinations(airline_company_id, destinations ) values (2, 'Rio de Janeiro GIG');

insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'New York JFK');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Belgrade BGD');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'London LHR');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Dubai DWC');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Berlin TXL');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Moscow SVO');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Sydney SYD');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Athens ATH');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Paris CDG');
insert into airline_company_destinations(airline_company_id, destinations ) values (3, 'Rio de Janeiro GIG');
------------------------------------------------------------------------------------------------------------------------

-- airplanes
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (1, 'Boing 747', 6, 6, 6, 6, 6, 6, 1);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (2, 'Airbus A320', 5, 5, 5, 5, 5, 5, 1);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (3, 'Convair CV440', 4, 4, 4, 4, 4, 4, 1);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (4, 'Embreaer 190', 4, 4, 5, 5, 4, 4, 1);

insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (5, 'Boing 747', 6, 6, 6, 6, 6, 6, 2);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (6, 'Airbus A320', 5, 5, 5, 5, 5, 5, 2);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (7, 'Convair CV440', 4, 4, 4, 4, 4, 4, 2);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (8, 'Embreaer 190', 4, 4, 5, 5, 4, 4, 2);

insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (9, 'Boing 747', 6, 6, 6, 6, 6, 6, 3);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (10, 'Airbus A320', 5, 5, 5, 5, 5, 5, 3);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (11, 'Convair CV440', 4, 4, 4, 4, 4, 4, 3);
insert into airplane(id, model, seats_first_rows, seats_first_cols, seats_business_rows, seats_business_cols, seats_economy_rows, seats_economy_cols, airline_company_id)
values (12, 'Embreaer 190', 4, 4, 5, 5, 4, 4, 3);
------------------------------------------------------------------------------------------------------------------------

-- luggage
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (1, 'Cabin Baggage', '40x20x55', 8 , 0.0, 1);
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (2, 'Checked Baggage', '78x28x50', 20 , 50.0, 1);
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (3, 'Checked Baggage Large', '120x40x70', 40 , 500, 1);

insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (4, 'Cabin Baggage', '40x20x55', 8 , 0.0, 2);
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (5, 'Checked Baggage', '78x28x50', 20 , 50.0, 2);
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (6, 'Checked Baggage Large', '120x40x70', 40 , 500, 2);

insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (7, 'Cabin Baggage', '40x20x55', 8 , 0.0, 3);
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (8, 'Checked Baggage', '78x28x50', 20 , 50.0, 3);
insert into luggage(id, name, dimensions, weight, price, airline_company_id) values (9, 'Checked Baggage Large', '120x40x70', 40 , 500, 3);
------------------------------------------------------------------------------------------------------------------------


-- hotel
insert into hotel_destination(id, country, name, zip) values
(1, 'Serbia', 'Vranje', 'VR');
insert into hotel_destination(id, country, name, zip) values
(2, 'Serbia', 'Belgrade', 'BG');
insert into hotel_destination(id, country, name, zip) values
(3, 'Serbia', 'Nis', 'NI');
insert into hotel_destination(id, country, name, zip) values
(4, 'Serbia', 'Kragujevac', 'KG');
insert into hotel_destination(id, country, name, zip) values
(5, 'Serbia', 'Subotica', 'SU');
insert into hotel_destination(id, country, name, zip) values
(6, 'Serbia', 'Kikinda', 'KI');
insert into hotel_destination(id, country, name, zip) values
(7, 'Serbia', 'Zrenjanin', 'ZR');
insert into hotel_destination(id, country, name, zip) values
(8, 'Serbia', 'Novi Sad', 'NS');
insert into hotel_destination(id, country, name, zip) values
(9, 'Japan', 'Tokio', 'TOK');
insert into hotel_destination(id, country, name, zip) values
(10, 'Italia', 'Barri', 'BAR');


insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (1,'Moscow','Terazije 20','Nice view from hotel',4,15,1,1,4,1);
insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (3,'Kasina','Terazije 25','Hotel with rich history',2,15,1,1,3,1);
insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (4,'Parisjen','Old square 52','Best in Paris',4,30,1,3,2,1);
insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (2,'Sheraton','Futoski put 76','Known for good services',3,8,1,2,5,1);



------------------------------------------------------------------------------------------------------------------------
--rentacar servis 1
insert into rentacar (id,name,address,promotional_description,rating, total_rating, location) values (1,'Drive X', 'North Road 7 London','DriveX has been continually growing ever since its foundation in 1989 in London, Great Britain, with branches all over the country.At all our locations we offer customers affordable and trusted rent a car services.',4,1, 1);
--rentacar servis 2
insert into rentacar (id,name,address,promotional_description, rating, total_rating, location) values (2,'EcoCar', '2nd Street 63 Paris','With over 60 years of experience in the industry, you can be sure that whether you are looking for a family car, sedan or prestige car for your business or leisure trips, EcoCar has the vehicle to meet every car rental need.Make the most out of our large range of services to make car rental experience a unique experience',4.5,2, 1);
--vozilo 1
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (99,'Test vehicle','Audi','A4','Sedan',4,2014,50,4,1);

insert into vehicle_quick_reservation (id,child_seat_included,collision_insurance_included,gps_included,pickup_location,reserved_from,reserved_until,return_location,theft_insurance_included,total_price,vehicle_id) values (99,TRUE,TRUE,FALSE,'Belgrade','2019-07-10 14:00','2020-07-14 14:00','Belgrade',FALSE,100,99);
--vozilo 1 u servis 1
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,99);

insert into rentacar_quick_reservations (rentacar_id,quick_reservations_id) values (1,99);



--dodavanje filijala
insert into branch_offices (id, branches) values (1,'Belgrade');
insert into branch_offices (id, branches) values (1,'Paris');
insert into branch_offices (id, branches) values (1,'London');

-- spratovi
insert into hotel_floor(id,floor_level,max_rooms) values (1,1,15);
insert into hotel_floor(id,floor_level,max_rooms) values (2,2,15);
insert into hotel_floor(id,floor_level,max_rooms) values (3,3,15);
insert into hotel_floor(id,floor_level,max_rooms) values (4,4,15);
--
insert into hotel_floor(id,floor_level,max_rooms) values (5,1,15);
insert into hotel_floor(id,floor_level,max_rooms) values (6,2,15);

insert into hotel_floor(id,floor_level,max_rooms) values (7,1,30);
insert into hotel_floor(id,floor_level,max_rooms) values (8,2,30);

-- hoteli_spratovi
insert into hotel_floors(hotel_id,floors_id) values (1,1);
insert into hotel_floors(hotel_id,floors_id) values (1,2);
insert into hotel_floors(hotel_id,floors_id) values (1,3);
insert into hotel_floors(hotel_id,floors_id) values (1,4);

insert into hotel_floors(hotel_id,floors_id) values (2,5);
insert into hotel_floors(hotel_id,floors_id) values (2,6);

insert into hotel_floors(hotel_id,floors_id) values (3,7);
insert into hotel_floors(hotel_id,floors_id) values (3,8);

-- sobe
-- PRVI SPRAT
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (1, 2, 1, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (2, 2, 2, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (3, 3, 3, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (4, 4, 4, 3, 1);
-- DRUGI SPRAT
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (5, 1, 1, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (6, 4, 2, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (7, 3, 5, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (8, 4, 10, 3, 1);

insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (9, 1, 1, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (10, 4, 2, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (11, 1, 3, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (12, 4, 9, 3, 1);

--spratovi sobe
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,1);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,2);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,3);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (1,4);

insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,5);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,6);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,7);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (2,8);

insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (5,9);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (5,10);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (6,11);
insert into hotel_floor_rooms_on_floor(hotel_floor_id,rooms_on_floor_id) values (6,12);

--insert into room_taken(id,end_date,start_date) values
--(1,'2020-05-19','2020-05-15');
--
--insert into room_taken(id,end_date,start_date) values
--(2,'2020-05-29','2020-05-25');
--
--insert into room_booked(room_id,room_taken_id) values(1,1);
--insert into room_booked(room_id,room_taken_id) values(1,2);


insert into special_price(id,end_date,price,start_date) values
(1,'2020-05-29',150,'2020-05-12');
insert into special_price(id,end_date,price,start_date) values
(2,'2020-05-09',50,'2020-05-01');
insert into special_price(id,end_date,price,start_date) values
(3,'2020-10-10',350,'2020-10-02');

insert into room_special_prices(room_id,special_prices_id) values
(1,1);
insert into room_special_prices(room_id,special_prices_id) values
(1,2);
insert into room_special_prices(room_id,special_prices_id) values
(1,3);


insert into hotels_offer(id, description, price, type) values
(1,'Room with one bed', 150, 0);
insert into hotels_offer(id, description, price, type) values
(2,'Room with two beds', 250, 1);
--insert into hotels_offer(id, description, price, type) values
--(3,'Room with three beds', 350, 2);
insert into hotels_offer(id, description, price, type) values
(4,'Room with four beds', 450, 3);
insert into hotels_offer(id, description, price, type) values
(5,'Room with five beds', 550, 4);
insert into hotels_offer(id, description, price, type) values
(6,'WiFi', 30, 5);
insert into hotels_offer(id, description, price, type) values
(7,'Air condition', 50, 5);
insert into hotels_offer(id, description, price, type) values
(8,'Horse riding', 40, 5);

insert into hotel_price_list(hotel_id, price_list_id) values
(1,1);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,2);
--insert into hotel_price_list(hotel_id, price_list_id) values
--(1,3);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,4);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,5);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,6);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,7);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,8);