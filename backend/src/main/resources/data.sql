insert into destination (id,airport,country,name,zip) values (1,'Kenedi', 'SAD', 'New York', 'JFK');
insert into destination (id,airport,country,name,zip) values (2,'Nikola Tesla', 'Srbija', 'Beograd', 'BGD');
insert into destination (id,airport,country,name,zip) values (3,'london', 'Velika britanija', 'London', 'LON');

insert into airline_company (id,description, name) values (1, 'dobaar', 'AirSerbia');

INSERT INTO flight (id,departure_date,departure_time,distance,flight_time,landing_date,landing_time,ticket_price,airline_company_id,final_destination_id,start_destination_id) VALUES (3,'2019-03-16','11:02',100,'11:11','2019-03-20','11:11',100,1,2,3);
INSERT INTO flight (id,departure_date,departure_time,distance,flight_time,landing_date,landing_time,ticket_price,airline_company_id,final_destination_id,start_destination_id) VALUES (2,'2019-03-16','15:02',100,'02:11','2019-07-20','11:11',200,1,3,2);
-- Janko DODAO OVO ISPOD
--insert into hotel (Id,name,address,description) values (1,'Hajat','Srbija/Beograd/Nemanjina 22','Srpski hotel');
--
--insert into hotels_offer (Id,price,type,hotel) values(1, 150.00, 2, 1);
--insert into hotels_offer (Id,price,type,hotel) values(2, 300.00, 3, 1);
--
--insert into room (Id,roomNumber,numberOfBeds,hotel) values (1, 1, 3, 1);
--insert into room (Id,roomNumber,numberOfBeds,hotel) values (2, 2, 3, 1);
--
--insert into roomTaken (Id, startDate, endDate) values (1,'2019-03-16','2019-03-19');
--insert into roomTaken (Id, startDate, endDate) values (2,'2019-04-16','2019-04-19');