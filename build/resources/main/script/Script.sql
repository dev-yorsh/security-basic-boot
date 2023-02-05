# DDL TABLA PARAMETRO: Tabla que contiene datos para la configuración y manejo del negocio, por ejemplo: el tipo de documento, moneda, etc.
create table security_basic.parametro(
    parametro_id serial primary key,
    codigo varchar(20) unique not null ,
    descripcion_codigo varchar(100),
    referencia_codigo varchar(20) not null ,
    valor_1 varchar(100) not null ,
    valor_2 varchar(100),
    orden smallint,
    estado boolean not null,
    constraint fk_valor_codigo foreign key(referencia_codigo) references security_basic.parametro(codigo)
);