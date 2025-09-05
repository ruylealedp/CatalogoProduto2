CREATE TABLE quarkus-manuntencaocarro;

CREATE TABLE USUARIO (
	id bigserial not null primary key,
	Nome varchar(100) not null,
	Email varchar(40) not null,
	Idade int not null
)

CREATE TABLE ABASTECIMENTO(
    id bigserial not null primary key,
    nomePosto varchar(50) not null,
    litros decimal(2,2)  not null,
    valorGasto decimal(3,2) not null,
    kmPercorrido decimal(3,2)) not null,


)