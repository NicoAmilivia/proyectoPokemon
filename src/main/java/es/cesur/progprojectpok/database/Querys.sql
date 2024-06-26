--AsignarMoteController
SELECT MAX(ID_POKEMON) FROM POKEMON;

UPDATE POKEMON SET MOTE = ? WHERE ID_POKEMON = ?

--BatallaController
SELECT * FROM POKEMON WHERE CAJA = ?

SELECT M.*
FROM MOVIMIENTOS M
INNER JOIN MOVIMIENTOS_POKEMON MP
ON M.ID_MOVIMIENTO = MP.ID_MOVIMIENTO
WHERE MP.ID_POKEMON = ?

SELECT * FROM MOVIMIENTOS ORDER BY RAND() LIMIT 4

UPDATE POKEMON SET EXPERIENCIA = ? WHERE ID_POKEMON = ?

UPDATE POKEMON SET NIVEL = ? WHERE ID_POKEMON = ?

UPDATE POKEMON SET ATAQUE = ?, AT_ESPECIAL = ?, DEFENSA = ?, DEF_ESPECIAL = ?, VITALIDAD = ?, VELOCIDAD = ? WHERE ID_POKEMON = ?

--CapturaController
SELECT * FROM pokedex WHERE NUM_POKEDEX = ?

INSERT INTO POKEMON (NUM_POKEDEX, ID_ENTRENADOR, MOTE, CAJA, ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EXPERIENCIA, VITALIDAD)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

--CentroController
UPDATE POKEMON SET VIDA_ACTUAL = VITALIDAD WHERE CAJA = 0;

SELECT VIDA_ACTUAL, VITALIDAD FROM POKEMON WHERE CAJA = 0 ORDER BY NUM_POKEDEX;

--ConfirmarCompraController
SELECT * FROM POKEMON WHERE SEXO = ?

INSERT INTO POKEMON (NUM_POKEDEX,ID_ENTRENADOR, MOTE, CAJA, ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EXPERIENCIA, VITALIDAD,VIDA_ACTUAL)
VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)

--ElegirPokemonCombateController
SELECT * FROM POKEMON WHERE CAJA = ?

--ElegirPokemonEntrenamientoController
SELECT * FROM POKEMON WHERE CAJA = ?

--EntrenamientoController
SELECT * FROM POKEMON WHERE CAJA = ?

SELECT M.*
FROM MOVIMIENTOS M
INNER JOIN MOVIMIENTOS_POKEMON MP
ON M.ID_MOVIMIENTO = MP.ID_MOVIMIENTO
WHERE MP.ID_POKEMON = ?

SELECT * FROM MOVIMIENTOS ORDER BY RAND() LIMIT 4

UPDATE POKEMON SET EXPERIENCIA = ? WHERE ID_POKEMON = ?

UPDATE POKEMON SET NIVEL = ? WHERE ID_POKEMON = ?

UPDATE POKEMON SET ATAQUE = ?, AT_ESPECIAL = ?, DEFENSA = ?, DEF_ESPECIAL = ?, VITALIDAD = ?, VELOCIDAD = ? WHERE ID_POKEMON = ?

--EquipoController
SELECT * FROM POKEMON WHERE CAJA = ?

UPDATE POKEMON SET CAJA = ? WHERE ID_POKEMON = ?

--LoginController
SELECT * FROM entrenador WHERE NOM_ENTRENADOR = ? AND PASS = ?

INSERT INTO entrenador (nom_entrenador, pass, pokedollars) VALUES (?, ?, ?)

--MochilaController
SELECT m.ID_OBJETO, m.NUM_OBJETO, o.URL FROM Objeto o INNER JOIN Mochila m ON o.ID_OBJETO = m.ID_OBJETO WHERE m.ID_ENTRENADOR = ? AND m.ID_OBJETO != 8

SELECT o.NOMBRE FROM OBJETO o INNER JOIN POKEMON p ON o.ID_OBJETO = p.ID_OBJETO WHERE p.ID_POKEMON = ?

SELECT URL FROM Objeto WHERE ID_OBJETO = ?

DELETE FROM Mochila WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?

INSERT INTO MOCHILA (ID_ENTRENADOR, ID_OBJETO, NUM_OBJETO) VALUES (?, ?, 1) ON DUPLICATE KEY UPDATE NUM_OBJETO = NUM_OBJETO + 1

--TiendaController
UPDATE ENTRENADOR SET POKEDOLLARS = ? WHERE ID_ENTRENADOR = ?

SELECT ID_OBJETO FROM OBJETO WHERE NOMBRE = ?

INSERT INTO MOCHILA (ID_ENTRENADOR, ID_OBJETO, NUM_OBJETO) VALUES (?, ?, 1) ON DUPLICATE KEY UPDATE NUM_OBJETO = NUM_OBJETO + 1

SELECT precio FROM objeto WHERE nombre = ?







