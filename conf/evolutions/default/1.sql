# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                            bigint not null,
  street                        varchar(255),
  number                        varchar(255),
  postal_code                   varchar(255),
  city                          varchar(255),
  country                       varchar(255),
  constraint pk_address primary key (id)
);
create sequence address_seq;

create table product (
  id                            bigint not null,
  ean                           varchar(255),
  name                          varchar(255),
  picture                       varbinary(255),
  description                   varchar(255),
  constraint pk_product primary key (id)
);
create sequence product_seq;

create table product__tag (
  product_id                    bigint not null,
  tag_id                        bigint not null,
  constraint pk_product__tag primary key (product_id,tag_id)
);

create table stock_item (
  warehouse_id                  bigint,
  product_id                    bigint,
  quantity                      bigint
);

create table tag (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_tag primary key (id)
);
create sequence tag_seq;

create table warehouse (
  id                            bigint not null,
  name                          varchar(255),
  address_id                    bigint,
  constraint uq_warehouse_address_id unique (address_id),
  constraint pk_warehouse primary key (id)
);
create sequence warehouse_seq;

alter table product__tag add constraint fk_product__tag_product foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_product__tag_product on product__tag (product_id);

alter table product__tag add constraint fk_product__tag_tag foreign key (tag_id) references tag (id) on delete restrict on update restrict;
create index ix_product__tag_tag on product__tag (tag_id);

alter table stock_item add constraint fk_stock_item_warehouse_id foreign key (warehouse_id) references warehouse (id) on delete restrict on update restrict;
create index ix_stock_item_warehouse_id on stock_item (warehouse_id);

alter table stock_item add constraint fk_stock_item_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_stock_item_product_id on stock_item (product_id);

alter table warehouse add constraint fk_warehouse_address_id foreign key (address_id) references address (id) on delete restrict on update restrict;


# --- !Downs

alter table product__tag drop constraint if exists fk_product__tag_product;
drop index if exists ix_product__tag_product;

alter table product__tag drop constraint if exists fk_product__tag_tag;
drop index if exists ix_product__tag_tag;

alter table stock_item drop constraint if exists fk_stock_item_warehouse_id;
drop index if exists ix_stock_item_warehouse_id;

alter table stock_item drop constraint if exists fk_stock_item_product_id;
drop index if exists ix_stock_item_product_id;

alter table warehouse drop constraint if exists fk_warehouse_address_id;

drop table if exists address;
drop sequence if exists address_seq;

drop table if exists product;
drop sequence if exists product_seq;

drop table if exists product__tag;

drop table if exists stock_item;

drop table if exists tag;
drop sequence if exists tag_seq;

drop table if exists warehouse;
drop sequence if exists warehouse_seq;

