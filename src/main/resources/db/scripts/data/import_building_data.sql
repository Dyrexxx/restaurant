SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE RESTAURANT.PUBLIC.BUILDING;
TRUNCATE TABLE RESTAURANT.PUBLIC.WAREHOUSE;
TRUNCATE TABLE RESTAURANT.PUBLIC.NEW_DELIVERY_BASKET;
TRUNCATE TABLE RESTAURANT.PUBLIC.NEW_DELIVERY_INGREDIENTS;
TRUNCATE TABLE RESTAURANT.PUBLIC.ORDER_BASKET;
TRUNCATE TABLE RESTAURANT.PUBLIC.ORDER_PRODUCT;
TRUNCATE TABLE RESTAURANT.PUBLIC.ORDER_PRODUCT_INGREDIENT;
SET REFERENTIAL_INTEGRITY TRUE;

insert into building(id, title) values (1, 'dodo1');
insert into building(id, title) values (2, 'dodo2');
insert into building(id, title) values (3, 'dodo3');
insert into building(id, title) values (4, 'dodo4');
insert into building(id, title) values (5, 'dodo5');

insert into warehouse(title, weight, building_id) values ('Тесто', 10000, 1);
insert into warehouse(title, weight, building_id) values ('Острый соус', 300, 1);
insert into warehouse(title, weight, building_id) values ('Огурцы', 400, 1);
insert into warehouse(title, weight, building_id) values ('Сыр', 300, 1);
insert into warehouse(title, weight, building_id) values ('Молоко', 100, 2);
insert into warehouse(title, weight, building_id) values ('Острый соус', 300, 2);
insert into warehouse(title, weight, building_id) values ('Огурцы', 400, 2);
insert into warehouse(title, weight, building_id) values ('Сыр', 300, 2);

