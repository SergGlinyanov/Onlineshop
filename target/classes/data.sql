insert into products(name, price, count)
values('Брюки', 1500, 100);
insert into products(name, price, count)
values('Платье', 1500, 100);

insert into categories(nameCategory, parentId)
values('Online shop', 1);
insert into categories(nameCategory, parentId)
values('Мужская одежда', 1);
insert into categories(nameCategory, parentId)
values('Женская одежда', 1);
insert into categories(nameCategory, parentId)
values('Брюки', 2);
insert into categories(nameCategory, parentId)
values('Плятья', 3);

insert into products_categories(id_product, id_category)
values (1,2);
insert into products_categories(id_product, id_category)
values (1,4);
insert into products_categories(id_product, id_category)
values (2,3);
insert into products_categories(id_product, id_category)
values (2,5);



