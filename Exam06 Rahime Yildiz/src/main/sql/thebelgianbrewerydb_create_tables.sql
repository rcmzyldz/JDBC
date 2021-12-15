create table `customer`
(
    `id` int unique auto_increment,
    `first_name` varchar(100) not null,
    `last_name` varchar(100) not null,
    `email` nvarchar(255) unique not null,
    `passcode` nvarchar(255) null,
    `registry_date` date default '2000-01-01' null,
    `active` boolean default true null,
    primary key (`id`)
) charset = utf8;
create table `category`
(
    `id` int unique auto_increment,
    `title` varchar(255) not null,
    `slug` varchar(255) unique not null,
    primary key (`id`)
) charset = utf8;
create table `brewer`
(
    `id` int unique auto_increment,
    `name` varchar(50) null,
    `address` varchar(50) null,
    `postcode` varchar(10) null,
    `city` varchar(50) null,
    `turnover` int default 0 null,
    primary key (`id`)
) charset = utf8;
create table `beer`
(
    `id` int unique auto_increment,
    `name` varchar(100) null,
    `brewer_id` int not null,
    `category_id` int not null,
    `price` float default 0.00 null,
    `stock` int default 0 null,
    `alcohol` float default 0.00 null,
    `version` int default 0 null,
    `image_url` varchar(255) null,
    constraint brewer_to_beer_fk foreign key (`brewer_id`) references brewer
        (`id`),
    constraint category_to_beer_fk foreign key (`category_id`) references category
        (`id`),
    primary key (`id`)
) charset = utf8;
create table `order`
(
    `id` int unique auto_increment,
    `customer_id` int not null,
    `brewer_id` int not null,
    `registered` timestamp null default CURRENT_TIMESTAMP,
    `updated` timestamp null default CURRENT_TIMESTAMP,
    `payment` float null default 0.00,
    constraint brewer_to_order_fk foreign key (`brewer_id`) references brewer
        (`id`),
    constraint customer_to_order_fk foreign key (`customer_id`) references
        customer (`id`),
    primary key (`id`)
) charset = utf8;
create table `order_detail`
(
    `id` int unique auto_increment,
    `order_id` int not null,
    `quantity` int null default 1,
    `discount` float default 0.00 null,
    constraint order_to_details_fk foreign key (`order_id`) references `order`
        (`id`),
    primary key (`id`)
) charset = utf8;