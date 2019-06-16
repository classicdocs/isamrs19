-- map location
insert into map_location(id, latitude, longitude) values
(1, 11.11, 22.22);

-- destinacije
insert into destination (id,airport,country,name,zip) values (1,'John F. Kennedy', 'USA', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Serbia', 'Belgrade', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'Heathrow', 'Englend', 'London', 'LHR');

-- aviokompanije
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (1, 'Cheap flights!', 'Serbia/Belgrade', 'AirSerbia', 4, 1, 1);
insert into airline_company (id,description, address, name, average_rating,total_rating, location) values (2, 'Air France is a french airline company', 'France/Paris', 'AirFrance', 4.5, 2, 1);

-- lista destinacija u aviokompaniji
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "New York JFK");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "Belgrade BGD");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "London LHR");


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
insert into user (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time) values ('HotelAdmin',15,'adresa4','email4','ime4','prezime4','a','12345','51544',4,null,null,4, TRUE);

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
insert into hotel_floor(id,level,max_rooms) values (5,1,15);
insert into hotel_floor(id,level,max_rooms) values (6,2,15);

insert into hotel_floor(id,level,max_rooms) values (7,1,30);
insert into hotel_floor(id,level,max_rooms) values (8,2,30);

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

--veza izmedju servisa i admina
insert into hotel_admins(hotel_id, admins_id) values(1,5);
insert into hotel_admins(hotel_id, admins_id) values(4,15);

insert into rentacar_admins(rentacar_id,admins_id) values (1,3);

insert into room_taken(id,end_date,start_date) values
(1,'2020-05-19','2020-05-15');

insert into room_taken(id,end_date,start_date) values
(2,'2020-05-29','2020-05-25');

insert into room_booked(room_id,room_taken_id) values(1,1);
insert into room_booked(room_id,room_taken_id) values(1,2);


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
--insert into hotels_offer(id, description, price, type) values
--(4,'Room with four beds', 450, 3);
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
--insert into hotel_price_list(hotel_id, price_list_id) values
--(1,4);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,5);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,6);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,7);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,8);