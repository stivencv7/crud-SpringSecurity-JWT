INSERT INTO `db_empresa`.`categorias` (`nombre`) VALUES ('computacion');
INSERT INTO `db_empresa`.`categorias` (`nombre`) VALUES ('tecnologia');
INSERT INTO `db_empresa`.`categorias` (`nombre`) VALUES ('deporte');

INSERT INTO `db_empresa`.`productos` (`cantidad`, `fecha_registro`, `precio`, `id_categoria`, `nombre`) VALUES ('4', '2023-08-22', '300', '2', 'Monitor');
INSERT INTO `db_empresa`.`productos` (`cantidad`, `fecha_registro`, `precio`, `id_categoria`, `nombre`) VALUES ('5', '2023-08-22', '100', '1', 'Tecaldo');
INSERT INTO `db_empresa`.`productos` (`cantidad`, `fecha_registro`, `precio`, `id_categoria`, `nombre`) VALUES ('1', '2023-08-22', '300', '3', 'Balon');

UPDATE `db_empresa`.`productos` SET `modo_visible` = 1 WHERE (`id` = '1');
UPDATE `db_empresa`.`productos` SET `modo_visible` = 1 WHERE (`id` = '2');
UPDATE `db_empresa`.`productos` SET `modo_visible` = 1 WHERE (`id` = '3');