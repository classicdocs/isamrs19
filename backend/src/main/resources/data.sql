insert into destination (id,airport,country,name,zip) values (1,'Kenedi', 'SAD', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Srbija', 'Belgrade', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'london', 'Velika britanija', 'London', 'LON');


insert into vehicle (id,name,vehicle_manufacturer,vehicle_model,vehicle_type,number_of_passengers,year_of_production,
price_per_day,average_rating) values ('1','Automobil','BMW','320','Sedan',5,2014,40,0);

insert into rentacar (id,name,address,promotional_description) values ('1','test rentacar', 'adress unknown',
'no promotional description');

insert into airline_company (id,description, address, name) values (1, 'Cheap flights', 'Kraljevica Marka 2', 'AirSerbia');
insert into airline_company (id,description, address, name) values (2, '', 'Oslobodjenja 3', 'AirFrance');

insert into airline_company_destinations(airline_company_id, destinations ) values (1, "New York");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "Belgrade");
insert into airline_company_destinations(airline_company_id, destinations ) values (1, "London");


insert into airplane(id, model) values (1, 'Boing 747');
insert into airline_company_airplanes(airline_company_id, airplanes_id) values (1,1);

INSERT INTO flight (id,departure_date,departure_time,distance,flight_time,landing_date,landing_time,ticket_price,airline_company_id,final_destination_id,start_destination_id, airplane_id) VALUES (3,'2019-03-16','11:02',100,'11:11','2019-03-20','11:11',100,1,2,3,1);
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time,landing_date,landing_time,ticket_price,airline_company_id,final_destination_id,start_destination_id, airplane_id) VALUES (2,'2019-03-16','15:02',100,'02:11','2019-07-20','11:11',200,1,3,2,1);


insert into airline_company_flights(airline_company_id, flights_id) values (1, 2);
insert into airline_company_flights(airline_company_id, flights_id) values (1, 3);

insert into airplane_flight(airplane_id,flight_id) values(1,2);
insert into airplane_flight(airplane_id,flight_id) values(1,3);

insert into seat(id, col_num, row_num, seat_class) values (1, 1, 1, 'First');
insert into seat(id, col_num, row_num, seat_class) values (2, 1, 2, 'First');
insert into seat(id, col_num, row_num, seat_class) values (3, 2, 1, 'Buissness');
insert into seat(id, col_num, row_num, seat_class) values (4, 2, 2, 'Economy');

insert into airplane_seats(airplane_id, seats_id) values (1, 1);
insert into airplane_seats(airplane_id, seats_id) values (1, 2);
insert into airplane_seats(airplane_id, seats_id) values (1, 3);
insert into airplane_seats(airplane_id, seats_id) values (1, 4);