
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

insert into map_location(id, latitude, longitude) values(11, 44.8130315, 20.4582418);
insert into map_location(id, latitude, longitude) values(12, 45.4317416, 12.332616);
insert into map_location(id, latitude, longitude) values(13, 41.3916124, 2.1697384);
insert into map_location(id, latitude, longitude) values(14, 59.3454907, 18.0655879);
insert into map_location(id, latitude, longitude) values(15, 55.7570628, 37.6143229);
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


-- hotel destinations
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
insert into hotel_destination(id, country, name, zip) values
(11, 'Italia', 'Rome', 'ROM');
insert into hotel_destination(id, country, name, zip) values
(12, 'Italia', 'Venice', 'VEN');
insert into hotel_destination(id, country, name, zip) values
(13, 'Italia', 'Torino', 'TOR');
insert into hotel_destination(id, country, name, zip) values
(14, 'Italia', 'Milano', 'MIL');
insert into hotel_destination(id, country, name, zip) values
(15, 'Italia', 'Genova', 'GEN');
insert into hotel_destination(id, country, name, zip) values
(16, 'Russia', 'Moscow', 'MOS');
insert into hotel_destination(id, country, name, zip) values
(17, 'Russia', 'Saint Petersburg', 'SNP');
insert into hotel_destination(id, country, name, zip) values
(18, 'Russia', 'Tymen', 'TYM');
insert into hotel_destination(id, country, name, zip) values
(19, 'Russia', 'Novosibirsk', 'NOV');
insert into hotel_destination(id, country, name, zip) values
(20, 'Russia', 'Krasnodar', 'KRA');
insert into hotel_destination(id, country, name, zip) values
(21, 'Spain', 'Barcelona', 'BAR');
insert into hotel_destination(id, country, name, zip) values
(22, 'Sweden', 'Stockholm', 'STO');
------------------------------------------------------------------------------------------------------------------------


-- hotel
insert into hotel(id,name,address,description,num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (1,'Moscow','Terazije 20','A landmark of Belgrade, one of the most important architectural gems of the Serbian capital, built in the style of the Russian secession which has been under state protection since the second half of the previous century.'
    ,4,15,11,2,4,1);

insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (2,'The Gritti Palace','Campo S.Maria Del Giglio 2467','This 15th-century palazzo occupies one of the loveliest spots on the Grand Canal, looking across to the magnificent Salute church, and has equally sumptuous interiors to match, with priceless antiques and frescoes, and a small but exquisite spa.'
    ,3,8,12,12,5,1);

insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (3,'Cotton House','Gran Vía de les Corts Catalanes, 670','Housed in the former headquarters of the city’s cotton guild, this hotel has honoured its origins.'
    ,2,15,13,21,3,1);

insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (4,'Ett Hem','Skoldungagatan 2, SE-114 27','Ett Hem has redefined the notion of a luxury hotel in Stockholm. The homely atmosphere is designed for guests to move around, and to relax in the living room, kitchen or conservatory. '
    ,4,30,14,22,2,1);

insert into hotel(id,name,address,description,
    num_of_floors,rooms_by_floor,location,destination_id, average_rating,total_rating)
    values (5,'Four Seasons Hotel Moscow','Okhotny Ryad, 2','A former Soviet-era hotel reimagined for the 21st century with an East-meets-West design aesthetic, a cool cocktail bar and top-notch spa.'
    ,4,18,15,16,2,1);
------------------------------------------------------------------------------------------------------------------------

-- floors

-- floors hotel 1
insert into hotel_floor(id,floor_level,max_rooms) values (1,1,15);
insert into hotel_floor(id,floor_level,max_rooms) values (2,2,15);
insert into hotel_floor(id,floor_level,max_rooms) values (3,3,15);
insert into hotel_floor(id,floor_level,max_rooms) values (4,4,15);
-- floors hotel 2
insert into hotel_floor(id,floor_level,max_rooms) values (5,1,8);
insert into hotel_floor(id,floor_level,max_rooms) values (6,2,8);
insert into hotel_floor(id,floor_level,max_rooms) values (7,3,8);
-- floors hotel 3
insert into hotel_floor(id,floor_level,max_rooms) values (8,1,15);
insert into hotel_floor(id,floor_level,max_rooms) values (9,2,15);
-- floors hotel 4
insert into hotel_floor(id,floor_level,max_rooms) values (10,1,30);
insert into hotel_floor(id,floor_level,max_rooms) values (11,2,30);
insert into hotel_floor(id,floor_level,max_rooms) values (12,3,30);
insert into hotel_floor(id,floor_level,max_rooms) values (13,4,30);
-- floors hotel 5
insert into hotel_floor(id,floor_level,max_rooms) values (14,1,18);
insert into hotel_floor(id,floor_level,max_rooms) values (15,2,18);
insert into hotel_floor(id,floor_level,max_rooms) values (16,3,18);
insert into hotel_floor(id,floor_level,max_rooms) values (17,4,18);
------------------------------------------------------------------------------------------------------------------------


-- hotel floors
insert into hotel_floors(hotel_id,floors_id) values (1,1);
insert into hotel_floors(hotel_id,floors_id) values (1,2);
insert into hotel_floors(hotel_id,floors_id) values (1,3);
insert into hotel_floors(hotel_id,floors_id) values (1,4);

insert into hotel_floors(hotel_id,floors_id) values (2,5);
insert into hotel_floors(hotel_id,floors_id) values (2,6);
insert into hotel_floors(hotel_id,floors_id) values (2,7);

insert into hotel_floors(hotel_id,floors_id) values (3,8);
insert into hotel_floors(hotel_id,floors_id) values (3,9);

insert into hotel_floors(hotel_id,floors_id) values (4,10);
insert into hotel_floors(hotel_id,floors_id) values (4,11);
insert into hotel_floors(hotel_id,floors_id) values (4,12);
insert into hotel_floors(hotel_id,floors_id) values (4,13);

insert into hotel_floors(hotel_id,floors_id) values (5,14);
insert into hotel_floors(hotel_id,floors_id) values (5,15);
insert into hotel_floors(hotel_id,floors_id) values (5,16);
insert into hotel_floors(hotel_id,floors_id) values (5,17);
------------------------------------------------------------------------------------------------------------------------

-- rooms
-- First floor Hotel 1
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (1, 2, 1, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (2, 2, 2, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (3, 3, 3, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (4, 4, 4, 3, 1);
-- Second floor Hotel 1
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (5, 1, 1, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (6, 4, 2, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (7, 3, 5, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (8, 4, 10, 3, 1);
-- First floor Hotel 2
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (9, 1, 1, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (10, 4, 2, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (11, 1, 3, 3, 1);
insert into room(id,number_of_beds,room_number,average_rating,total_rating) values (12, 4, 8, 3, 1);
------------------------------------------------------------------------------------------------------------------------

--floors rooms
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
------------------------------------------------------------------------------------------------------------------------

insert into special_price(id,end_date,price,start_date) values
(1,'2020-05-29',150,'2020-05-12');
insert into special_price(id,end_date,price,start_date) values
(2,'2020-05-09',50,'2020-05-01');
insert into special_price(id,end_date,price,start_date) values
(3,'2020-10-10',350,'2020-10-02');
------------------------------------------------------------------------------------------------------------------------

insert into room_special_prices(room_id,special_prices_id) values
(1,1);
insert into room_special_prices(room_id,special_prices_id) values
(1,2);
insert into room_special_prices(room_id,special_prices_id) values
(1,3);
------------------------------------------------------------------------------------------------------------------------

insert into hotels_offer(id, description, price, type) values
(1,'Room with one bed', 150, 0);
insert into hotels_offer(id, description, price, type) values
(2,'Room with two beds', 250, 1);
insert into hotels_offer(id, description, price, type) values
(3,'Room with three beds', 350, 2);
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
------------------------------------------------------------------------------------------------------------------------

insert into hotel_price_list(hotel_id, price_list_id) values
(1,1);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,2);
insert into hotel_price_list(hotel_id, price_list_id) values
(1,3);
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
------------------------------------------------------------------------------------------------------------------------




--rentacar servis 1
insert into rentacar (id,name,address,promotional_description,rating, total_rating, location) values (1,'Drive X', 'North Road 7 London','DriveX has been continually growing ever since its foundation in 1989 in London, Great Britain, with branches all over the country.At all our locations we offer customers affordable and trusted rent a car services.',4,1, 3);
--rentacar servis 2
insert into rentacar (id,name,address,promotional_description, rating, total_rating, location) values (2,'EcoCar', '2nd Street 63 Paris','With over 60 years of experience in the industry, you can be sure that whether you are looking for a family car, sedan or prestige car for your business or leisure trips, EcoCar has the vehicle to meet every car rental need.Make the most out of our large range of services to make car rental experience a unique experience',4.5,2, 9);
--rentacar servis 3
insert into rentacar (id,name,address,promotional_description, rating, total_rating, location) values (3,'GoRent', 'Old Square Berlin','The most reliable rent-a-car company in Berlin.',3,1, 5);
--rentacar servis 4
insert into rentacar (id,name,address,promotional_description, rating, total_rating, location) values (4,'VehiclesToTake', 'Wilshire Boulevard New York','We provides cheap vehicles to many satisfied customers.',5,1, 1);
--rentacar servis 5
insert into rentacar (id,name,address,promotional_description, rating, total_rating, location) values (5,'CarsForYou', '5th Avenue Rio de Janeiro','We are known for good customer service.',2.5,1, 10);

--dodavanje vozila
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (1,'vehicle1','Audi','A4','Sedan',5,2017,100,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (2,'vehicle2','BMW','520','Station wagon',5,2013,80,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (3,'vehicle3','Ford','Mustang','Coupe',4,2010,120,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (4,'vehicle4','Audi','Q7','SUV',7,2016,170,3,1);

insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (5,'vehicle5','Citroen','C5','Sedan',5,2012,100,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (6,'vehicle6','Fiat','Punto','Hatchback',5,2009,70,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (7,'vehicle7','Ford','Fiesta','Hatchback',5,2010,90,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (8,'vehicle8','Mercedes Benz','E Class','Sedan',5,2016,170,3,1);

insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (9,'vehicle9','Opel','Astra','Station wagon',5,2017,100,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (10,'vehicle10','Peugeot','508','Sedan',5,2018,150,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (11,'vehicle11','Renault','Kadjar','SUV',5,2017,120,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (12,'vehicle12','Volkswagen','Tiguan','SUV',7,2016,130,3,1);

insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (13,'vehicle13','Audi','TT','Coupe',2,2017,200,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (14,'vehicle14','BMW','320','Sedan',5,2018,150,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (15,'vehicle15','Alfa Romeo','Giulia','Sedan',5,2010,90,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (16,'vehicle16','Volkswagen','Touareg','SUV',7,2016,170,3,1);

insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (17,'vehicle17','Citroen','C3','Sedan',5,2017,100,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (18,'vehicle18','Ford','Mondeo','Station wagon',5,2013,80,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (19,'vehicle19','Opel','Corsa','Hatchback',4,2010,120,3,1);
insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating,total_rating) values (20,'vehicle20','Renault','Kadjar','SUV',7,2016,170,3,1);


insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,1);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,2);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,3);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,4);

insert into rentacar_vehicles (rentacar_id, vehicles_id) values (2,5);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (2,6);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (2,7);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (2,8);

insert into rentacar_vehicles (rentacar_id, vehicles_id) values (3,9);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (3,10);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (3,11);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (3,12);

insert into rentacar_vehicles (rentacar_id, vehicles_id) values (4,13);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (4,14);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (4,15);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (4,16);

insert into rentacar_vehicles (rentacar_id, vehicles_id) values (5,17);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (5,18);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (5,19);
insert into rentacar_vehicles (rentacar_id, vehicles_id) values (5,20);

insert into vehicle_quick_reservation (id,child_seat_included,collision_insurance_included,gps_included,pickup_location,reserved_from,reserved_until,return_location,theft_insurance_included,total_price,vehicle_id) values (1,TRUE,TRUE,FALSE,'Belgrade','2019-07-10 14:00','2019-07-14 14:00','Belgrade',FALSE,100,1);

insert into rentacar_quick_reservations (rentacar_id,quick_reservations_id) values (1,1);
--dodavanje filijala
insert into branch_offices (id, branches) values (1,'London');
insert into branch_offices (id, branches) values (1,'Liverpool');
insert into branch_offices (id, branches) values (1,'Manchester');
insert into branch_offices (id, branches) values (2,'Paris');
insert into branch_offices (id, branches) values (2,'Toulouse');
insert into branch_offices (id, branches) values (2,'Marseille');
insert into branch_offices (id, branches) values (3,'New York');
insert into branch_offices (id, branches) values (3,'New Jersey');
insert into branch_offices (id, branches) values (3,'Washington');
insert into branch_offices (id, branches) values (4,'Berlin');
insert into branch_offices (id, branches) values (4,'Frankfurt');
insert into branch_offices (id, branches) values (4,'Dortmund');
insert into branch_offices (id, branches) values (5,'Rio de Janeiro');
insert into branch_offices (id, branches) values (5,'Brasilia');
insert into branch_offices (id, branches) values (5,'Sao Paulo');

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
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RentACarAdmin',4,'address4','email4@gmail.com','name4','lastname4','rentacaradmin1','12345','51542',2,1,null,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RentACarAdmin',14,'address14','email14@gmail.com','name14','lastname14','rentacaradmin2','12345','51542',2,2,null,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RentACarAdmin',15,'address15','email15@gmail.com','name15','lastname15','rentacaradmin3','12345','51542',2,3,null,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RentACarAdmin',16,'address16','email16@gmail.com','name16','lastname16','rentacaradmin4','12345','51542',2,4,null,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('RentACarAdmin',17,'address17','email17@gmail.com','name17','lastname17','rentacaradmin5','12345','51542',2,5,null,null, FALSE, TRUE);

--Airline-Company admins
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('AirlineCompanyAdmin',5,'address5','email5@gmail.com','name5','lastname5','airlineadmin1','12345','51543',3,null,1,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('AirlineCompanyAdmin',6,'address6','email6@gmail.com','name6','lastname6','airlineadmin2','12345','51543',3,null,2,null, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('AirlineCompanyAdmin',7,'address7','email7@gmail.com','name7','lastname7','airlineadmin3','12345','51543',3,null,3,null, FALSE, TRUE);

--Hotel admins
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('HotelAdmin',8,'address8','email8@gmail.com','name8','lastname8','hoteladmin1','12345','11111',4,null,null,1, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('HotelAdmin',9,'address9','email9@gmail.com','name9','lastname9','hoteladmin2','12345','22222',4,null,null,2, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('HotelAdmin',10,'address10','email10@gmail.com','name10','lastname10','hoteladmin3','12345','33333',4,null,null,3, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('HotelAdmin',11,'address11','email11@gmail.com','name11','lastname11','hoteladmin4','12345','44444',4,null,null,4, FALSE, TRUE);
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('HotelAdmin',12,'address12','email12@gmail.com','name12','lastname12','hoteladmin5','12345','55555',4,null,null,5, FALSE, TRUE);

--System admin
insert into users (dtype,id,address,email,firstname,lastname,username,password,phone,role_id,rentacar_id,airline_company_id,hotel_id, logged_first_time, verified) values ('SystemAdmin',13,'adresa5','email5','ime5','prezime5','systemadmin','12345','51545',5,null,null,null, FALSE, TRUE);
------------------------------------------------------------------------------------------------------------------------

