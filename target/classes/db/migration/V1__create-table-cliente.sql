create table cliente(
      id bigint not null auto_increment,
      nombre varchar(100) not null,
      email varchar(100) not null unique,
      dni varchar(6) not null unique,

      primary key(id)
);