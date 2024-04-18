//Tablas creadas en el proyecto
CREATE TABLE POKEMON (
ID_POKEMON INT PRIMARY KEY,
NUM_POKEDEX INT,
ID_ENTRENADOR INT,
MOTE VARCHAR(30),
CAJA INT NOT NULL,
ATAQUE INT NOT NULL,
AT_ESPECIAL INT NOT NULL,
DEFENSA INT NOT NULL,
DEF_ESPECIAL INT NOT NULL,
VELOCIDAD INT NOT NULL,
NIVEL INT NOT NULL,
FERTILIDAD INT NOT NULL,
SEXO CHAR(1) NOT NULL,
ESTADO VARCHAR(20) NOT NULL,
EXPERIENCIA INT NOT NULL,
VITALIDAD INT NOT NULL,
ID_OBJETO INT
);

CREATE TABLE ENTRENADOR(
ID_ENTRENADOR INT PRIMARY KEY,
    NOM_ENTRENADOR VARCHAR(20) NOT NULL,
    PASS VARCHAR(30),
    POKEDOLLARS INT NOT NULL
);

ALTER TABLE POKEMON
ADD CONSTRAINT FK_ENTRENADOR_ID FOREIGN KEY (ID_ENTRENADOR) REFERENCES ENTRENADOR(ID_ENTRENADOR);

CREATE TABLE POKEDEX(
NUM_POKEDEX INT PRIMARY KEY,
NOM_POKEMON VARCHAR(30) NOT NULL,
TIPO1 VARCHAR(20) NOT NULL,
TIPO2 VARCHAR(20),
IMAGEN VARCHAR(100) NOT NULL,
SONIDO VARCHAR(100) NOT NULL,
NIVEL_EVOLUCION INT,
NUM_POKEDEX_EVO INT
);

ALTER TABLE POKEMON
ADD CONSTRAINT FK_NUM_POKEDEX FOREIGN KEY (NUM_POKEDEX) REFERENCES POKEDEX(NUM_POKEDEX);

INSERT INTO POKEDEX (NUM_POKEDEX, NOM_POKEMON, TIPO1, TIPO2, IMAGEN, SONIDO, NIVEL_EVOLUCION, NUM_POKEDEX_EVO) VALUES
(1, 'Bulbasaur', 'Planta', 'Veneno', '@../images/pokemons/bulbasaur-front.png', 'bulbasaur.mp3', 16, 2),
(2, 'Ivysaur', 'Planta', 'Veneno', '@../images/pokemons/ivysaur-front.png', 'ivysaur.mp3', 32, 3),
(3, 'Venusaur', 'Planta', 'Veneno', '@../images/pokemons/venusaur-front.png', 'venusaur.mp3', NULL, NULL),
(4, 'Charmander', 'Fuego', NULL, '@../images/pokemons/charmander-front.png', 'charmander.mp3', 16, 5),
(5, 'Charmeleon', 'Fuego', NULL, '@../images/pokemons/charmeleon-front.png', 'charmeleon.mp3', 36, 6),
(6, 'Charizard', 'Fuego', 'Volador', '@../images/pokemons/charizard-front.png', 'charizard.mp3', NULL, NULL),
(7, 'Squirtle', 'Agua', NULL, '@../images/pokemons/squirtle-front.png', 'squirtle.mp3', 16, 8),
(8, 'Wartortle', 'Agua', NULL, '@../images/pokemons/wartortle-front.png', 'wartortle.mp3', 36, 9),
(9, 'Blastoise', 'Agua', NULL, '@../images/pokemons/blastoise-front.png', 'blastoise.mp3', NULL, NULL),
(10, 'Caterpie', 'Bicho', NULL, '@../images/pokemons/caterpie-front.png', 'caterpie.mp3', 7, 11),
(11, 'Metapod', 'Bicho', NULL, '@../images/pokemons/metapod-front.png', 'metapod.mp3', 10, 12),
(12, 'Butterfree', 'Bicho', 'Volador', '@../images/pokemons/butterfree-front.png', 'butterfree.mp3', NULL, NULL),
(13, 'Weedle', 'Bicho', 'Veneno', '@../images/pokemons/weedle-front.png', 'weedle.mp3', 7, 14),
(14, 'Kakuna', 'Bicho', 'Veneno', '@../images/pokemons/kakuna-front.png', 'kakuna.mp3', 10, 15),
(15, 'Beedrill', 'Bicho', 'Veneno', '@../images/pokemons/beedrill-front.png', 'beedrill.mp3', NULL, NULL),
(16, 'Pidgey', 'Normal', 'Volador', '@../images/pokemons/pidgey-front.png', 'pidgey.mp3', 18, 17),
(17, 'Pidgeotto', 'Normal', 'Volador', '@../images/pokemons/pidgeotto-front.png', 'pidgeotto.mp3', 36, 18),
(18, 'Pidgeot', 'Normal', 'Volador', '@../images/pokemons/pidgeot-front.png', 'pidgeot.mp3', NULL, NULL),
(19, 'Rattata', 'Normal', NULL, '@../images/pokemons/rattata-front.png', 'rattata.mp3', 20, 20),
(20, 'Raticate', 'Normal', NULL, '@../images/pokemons/raticate-front.png', 'raticate.mp3', NULL, NULL),
(21, 'Spearow', 'Normal', 'Volador', '@../images/pokemons/spearow-front.png', 'spearow.mp3', 20, 22),
(22, 'Fearow', 'Normal', 'Volador', '@../images/pokemons/fearow-front.png', 'fearow.mp3', NULL, NULL),
(23, 'Ekans', 'Veneno', NULL, '@../images/pokemons/ekans-front.png', 'ekans.mp3', 22, 24),
(24, 'Arbok', 'Veneno', NULL, '@../images/pokemons/arbok-front.png', 'arbok.mp3', NULL, NULL),
(25, 'Pikachu', 'Eléctrico', NULL, '@../images/pokemons/pikachu-front.png', 'pikachu.mp3', 22, 26),
(26, 'Raichu', 'Eléctrico', NULL, '@../images/pokemons/raichu-front.png', 'raichu.mp3', NULL, NULL);
