insert into destination (id,airport,country,name,zip) values (1,'Kenedi', 'SAD', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Srbija', 'Belgrade', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'london', 'Velika britanija', 'London', 'LON');

insert into rentacar (id,name,address,promotional_description) values (1,'Drive X', 'North Road 7 London','DriveX has been continually growing ever since its foundation in 1989 in London, Great Britain, with branches all over the country.At all our locations we offer customers affordable and trusted rent a car services.');


insert into airline_company (id,description, address, name) values (1, 'Cheap flights', 'Kraljevica Marka 2', 'AirSerbia');
insert into airline_company (id,description, address, name) values (2, '', 'Oslobodjenja 3', 'AirFrance');

insert into airline_company_destinations(airline_company_id, destinations ) values (1, "New York");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "Belgrade");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "London");


INSERT INTO flight (id,departure_date,departure_time,distance,flight_time,landing_date,landing_time,ticket_price,airline_company_id,final_destination_id,start_destination_id) VALUES (3,'2019-03-16','11:02',100,'11:11','2019-03-20','11:11',100,1,2,3);
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time,landing_date,landing_time,ticket_price,airline_company_id,final_destination_id,start_destination_id) VALUES (2,'2019-03-16','15:02',100,'02:11','2019-07-20','11:11',200,1,3,2);



insert into airline_company_flights(airline_company_id, flights_id) values (1, 2);
insert into airline_company_flights(airline_company_id, flights_id) values (1, 3);



insert into rentacar (id,name,address,promotional_description) values (2,'EcoCar', '2nd Street 63 Paris','With over 60 years of experience in the industry, you can be sure that whether you are looking for a family car, sedan or prestige car for your business or leisure trips, EcoCar has the vehicle to meet every car rental need.Make the most out of our large range of services to make car rental experience a unique experience');

insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,price_per_day,average_rating) values (99,'Test vehicle','Audi','A4','Sedan',4,2014,50,0);

insert into rentacar_vehicles (rentacar_id, vehicles_id) values (1,99);