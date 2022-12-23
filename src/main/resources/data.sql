CREATE TABLE conta
(
    id_conta IDENTITY NOT NULL PRIMARY KEY,
    nome_responsavel VARCHAR(50) NOT NULL
);


CREATE TABLE transferencia
(
    id IDENTITY NOT NULL PRIMARY KEY,
    data_transferencia TIMESTAMP WITH TIME ZONE NOT NULL,
    valor NUMERIC (20,2) NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    nome_operador_transacao VARCHAR (50),
    conta_id INT NOT NULL,

        CONSTRAINT FK_CONTA
        FOREIGN KEY (conta_id)
        REFERENCES conta(id_conta)
);

INSERT INTO CONTA (id_conta, nome_responsavel) VALUES (1,'Fulano');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (2,'Sicrano');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (3,'Ronnyscley');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (4,'Beltrano');

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (1,'2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (2,'2019-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (3,'2020-05-04 08:12:45+03',-500.50,'SAQUE', 'Sicrano',1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (4,'2021-05-04 08:12:45+03',-500.50,'SAQUE', 'Beltrano',1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (5,'2021-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (6,'2021-05-04 08:12:45+03',-500.50,'SAQUE', null,1);

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (7,'2021-02-03 11:10:04+03',-25143.19,'SAQUE', 'Ronnyscley',2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (8,'2022-07-10 10:15:01+03',-1231.23,'SAQUE', 'Beltrano',2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (9,'2020-04-03 12:12:04+03',2232.09,'TRANSFERENCIA', 'Ronnyscley',2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (10,'2022-06-09 11:10:04+03',-2123.19,'SAQUE', 'Ronnyscley',2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (11,'2019-02-03 09:53:27+03',12.24,'DEPOSITO', 'Sicrano',2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (12,'2019-08-07 08:12:45+03',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (13,'2021-04-01 12:12:04+03',25173.09,'TRANSFERENCIA', 'Ronnyscley',2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (14,'2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA', 'Beltrano',2);

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (21,'2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 3);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (22,'2019-05-04 08:12:45+03',-500.50,'SAQUE', 'Fulano',3);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (23,'2020-05-04 08:12:45+03',-500.50,'SAQUE', null,3);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (24,'2021-05-04 08:12:45+03',-500.50,'SAQUE', 'Fulano',3);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (25,'2021-05-04 08:12:45+03',-500.50,'SAQUE', null,3);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (26,'2021-05-04 08:12:45+03',-500.50,'SAQUE', 'Fulano',3);

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (37,'2021-02-03 11:10:04+03',-25143.19,'SAQUE', 'Ronnyscley',4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (38,'2022-07-10 10:15:01+03',-1231.23,'SAQUE', 'Fulano',4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (39,'2020-04-03 12:12:04+03',2232.09,'TRANSFERENCIA', 'Ronnyscley',4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (310,'2022-06-09 11:10:04+03',-2123.19,'SAQUE', 'Ronnyscley',4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (311,'2019-02-03 09:53:27+03',12.24,'DEPOSITO', null,4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (312,'2019-08-07 08:12:45+03',-530.50,'SAQUE', 'Ronnyscley',4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (313,'2021-04-01 12:12:04+03',25173.09,'TRANSFERENCIA', 'Ronnyscley',4);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (314,'2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA', 'Beltrano',4);
