create database if not exists lp1 default character set utf8;
use lp1;

create table Funcionario(
id_funcionario int not null auto_increment,
nome varchar(100) not null,
cpf varchar(14) not null,
salario decimal(10,2) not null,
sexo char(1) not null,
telefone varchar(14) not null,
primary key(id_funcionario)
);

create table Fornecedor(
cnpj varchar(18) not null,
nome varchar(100) not null,
telefone varchar(14) not null,
primary key(cnpj)
);

create table Insumo(
id_insumo int not null auto_increment,
nome varchar(100) not null,
preco decimal(10,2) not null,
primary key (id_insumo)
);

create table Fornece(
fornecedor_cnpj varchar(18) not null,
insumo_id_insumo int not null ,
qnt_fornecida decimal(10,2) not null, 
foreign key(fornecedor_cnpj) references Fornecedor(cnpj),
foreign key(insumo_id_insumo) references Insumo(id_insumo)
);

create table Pedido(
id_pedido int not null auto_increment,
id_funcionario int not null,
data_pedido date not null,
valor_total DECIMAL(10,2) not null,
descricao varchar(100) not null,
foreign key(id_funcionario) references Funcionario(id_funcionario),
primary key (id_pedido)
);

create table Produto(
id_produto int auto_increment,
nome varchar(100) not null,
primary key(id_produto)
);

create table ItemPedido(
id_pedido int not null,
id_produto int not null,
quantidade int not null,
foreign key(id_pedido) references Pedido(id_pedido),
foreign key(id_produto) references Produto(id_produto)
);

create table ItemProduto(
id_produto int not null,
id_insumo int not null,
quantidade int not null,
foreign key(id_produto) references Produto(id_produto),
foreign key(id_insumo) references Insumo(id_insumo)
);