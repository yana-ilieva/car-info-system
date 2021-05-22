insert into cities (id, name)
values (1000, 'Varna'), (1001, 'Burgas'), (1002, 'Plovdiv');

insert into branches (id, name, city_id)
values (1000, 'Levski', 1000), (1001, 'Industrial zone', 1000),
       (1002, 'Izgrev', 1001), (1003, 'Meden Rudnik', 1001),
       (1004, 'Iztochen', 1002);

insert into brands (id, name)
values (1000, 'Fiat'), (1001, 'Mercedes'), (1002, 'Renault');

insert into models (id, name, brand_id)
values (1000, 'Tipo', 1000), (1001, 'Stilo', 1000), (1002, 'Punto', 1000),
       (1003, 'A-Class', 1001), (1004, 'B-Class', 1001),
       (1005, 'Clio', 1002), (1006, 'Megane', 1002);

insert into extras (id, name)
values (1000, 'Air conditioner'), (1001, 'Parktronik');

insert into cars (id, registration_number, car_type, color, deleted, fuel_type, production_year, model_id, branch_id)
values (1000, 'B1234CA','MANUAL' ,'WHITE', false, 'GAS', '2012', 1000, 1000),
       (1001, 'A4321TA','AUTOMATIC', 'BLACK', false, 'ELECTRICITY', '2016', 1004, 1000),
       (1002, 'CC5555A','MANUAL', 'RED', false, 'DIESEL', '2013', 1001, 1001),
       (1003, 'B9999BT','AUTOMATIC', 'ORANGE', false, 'GAS', '2015', 1005, 1004);

insert into cars_extras (car_id, extras_id)
values(1000, 1000), (1001, 1000), (1001, 1001), (1002, 1000);

