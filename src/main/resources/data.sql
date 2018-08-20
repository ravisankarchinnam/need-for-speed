/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');
 
 -- Create 4 manufacturer.

insert into manufacturer (id, date_created, manufacturer) values (1, now(), 'Mercedes');
insert into manufacturer (id, date_created, manufacturer) values (2, now(), 'Porsche');
insert into manufacturer (id, date_created, manufacturer) values (3, now(), 'Ferrari');
insert into manufacturer (id, date_created, manufacturer) values (4, now(), 'Audi');

-- Create 3 convertible  cars.

insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (1, now(), 'JII A123 345', 4, true, 4, 'petrol', 1);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id)
values
 (2, now(), 'JKK AA-233', 4, true, 2.5, 'petrol', 4);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id)
values
 (3, now(), 'ADD C11 435', 4, true, 3.7, 'petrol', 2);

-- Create 3 non convertible cars.

insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (4, now(),'TN DE 23417', 5, false, 4.9, 'petrol', 3);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (5, now(),'MD IND 324', 4, false, 2, 'petrol', 2);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (6, now(),'CD CN2 223', 4, false, 4, 'petrol', 1);
 
 -- Create one convertible car with electric engine.
 
 insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (7, now(),'DLO 87567', 4, true, 4.5, 'electric', 1);
 
 --Create one convertible car with gas engine.
 
  insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (8, now(),'AB 123', 4, false, 3.9, 'gas', 3);
 
 
-- Create 3 ONLINE drivers with a car.

insert into driver (id, date_created, deleted, online_status, password, username, car)
values
 (9, now(), false, 'ONLINE', 'driver09pw', 'driver09', 4);

insert into driver (id, date_created, deleted, online_status, password, username, car)
values
 (10, now(), false, 'OFFLINE', 'driver10pw', 'driver10', 7);

insert into driver (id, date_created, deleted, online_status, password, username, car)
values
 (11, now(), true, 'ONLINE', 'driver11pw', 'driver11', 8);
