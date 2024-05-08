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
(26, 'Raichu', 'Eléctrico', NULL, '@../images/pokemons/raichu-front.png', 'raichu.mp3', NULL, NULL),
(27, 'Sandshrew', 'Tierra', NULL, '@../images/pokemons/sandshrew-front.png', 'sandshrew.mp3', 22, 28),
(28, 'Sandslash', 'Tierra', NULL, '@../images/pokemons/sandslash-front.png', 'sandslash.mp3', NULL, NULL),
(29, 'Nidoran♀', 'Veneno', NULL, '@../images/pokemons/nidoran-female-front.png', 'nidoran-female.mp3', 16, 30),
(30, 'Nidorina', 'Veneno', NULL, '@../images/pokemons/nidorina-front.png', 'nidorina.mp3', 32, 31),
(31, 'Nidoqueen', 'Veneno', 'Tierra', '@../images/pokemons/nidoqueen-front.png', 'nidoqueen.mp3', NULL, NULL),
(32, 'Nidoran♂', 'Veneno', NULL, '@../images/pokemons/nidoran-male-front.png', 'nidoran-male.mp3', 16, 33),
(33, 'Nidorino', 'Veneno', NULL, '@../images/pokemons/nidorino-front.png', 'nidorino.mp3', 32, 34),
(34, 'Nidoking', 'Veneno', 'Tierra', '@../images/pokemons/nidoking-front.png', 'nidoking.mp3', NULL, NULL),
(35, 'Clefairy', 'Hada', NULL, '@../images/pokemons/clefairy-front.png', 'clefairy.mp3', 32, 36),
(36, 'Clefable', 'Hada', NULL, '@../images/pokemons/clefable-front.png', 'clefable.mp3', NULL, NULL),
(37, 'Vulpix', 'Fuego', NULL, '@../images/pokemons/vulpix-front.png', 'vulpix.mp3', 22, 38),
(38, 'Ninetales', 'Fuego', NULL, '@../images/pokemons/ninetales-front.png', 'ninetales.mp3', NULL, NULL),
(39, 'Jigglypuff', 'Normal', 'Hada', '@../images/pokemons/jigglypuff-front.png', 'jigglypuff.mp3', 32, 40),
(40, 'Wigglytuff', 'Normal', 'Hada', '@../images/pokemons/wigglytuff-front.png', 'wigglytuff.mp3', NULL, NULL),
(41, 'Zubat', 'Veneno', 'Volador', '@../images/pokemons/zubat-front.png', 'zubat.mp3', 22, 42),
(42, 'Golbat', 'Veneno', 'Volador', '@../images/pokemons/golbat-front.png', 'golbat.mp3', NULL, NULL),
(43, 'Oddish', 'Planta', 'Veneno', '@../images/pokemons/oddish-front.png', 'oddish.mp3', 21, 44),
(44, 'Gloom', 'Planta', 'Veneno', '@../images/pokemons/gloom-front.png', 'gloom.mp3', 32, 45),
(45, 'Vileplume', 'Planta', 'Veneno', '@../images/pokemons/vileplume-front.png', 'vileplume.mp3', NULL, NULL),
(46, 'Paras', 'Bicho', 'Planta', '@../images/pokemons/paras-front.png', 'paras.mp3', 24, 47),
(47, 'Parasect', 'Bicho', 'Planta', '@../images/pokemons/parasect-front.png', 'parasect.mp3', NULL, NULL),
(48, 'Venonat', 'Bicho', 'Veneno', '@../images/pokemons/venonat-front.png', 'venonat.mp3', 31, 49),
(49, 'Venomoth', 'Bicho', 'Veneno', '@../images/pokemons/venomoth-front.png', 'venomoth.mp3', NULL, NULL),
(50, 'Diglett', 'Tierra', NULL, '@../images/pokemons/diglett-front.png', 'diglett.mp3', 26, 51),
(51, 'Dugtrio', 'Tierra', NULL, '@../images/pokemons/dugtrio-front.png', 'dugtrio.mp3', NULL, NULL),
(52, 'Meowth', 'Normal', NULL, '@../images/pokemons/meowth-front.png', 'meowth.mp3', 28, 53),
(53, 'Persian', 'Normal', NULL, '@../images/pokemons/persian-front.png', 'persian.mp3', NULL, NULL),
(54, 'Psyduck', 'Agua', NULL, '@../images/pokemons/psyduck-front.png', 'psyduck.mp3', 33, 55),
(55, 'Golduck', 'Agua', NULL, '@../images/pokemons/golduck-front.png', 'golduck.mp3', NULL, NULL),
(56, 'Mankey', 'Lucha', NULL, '@../images/pokemons/mankey-front.png', 'mankey.mp3', 28, 57),
(57, 'Primeape', 'Lucha', NULL, '@../images/pokemons/primeape-front.png', 'primeape.mp3', NULL, NULL),
(58, 'Growlithe', 'Fuego', NULL, '@../images/pokemons/growlithe-front.png', 'growlithe.mp3', 36, 59),
(59, 'Arcanine', 'Fuego', NULL, '@../images/pokemons/arcanine-front.png', 'arcanine.mp3', NULL, NULL),
(60, 'Poliwag', 'Agua', NULL, '@../images/pokemons/poliwag-front.png', 'poliwag.mp3', 25, 61),
(61, 'Poliwhirl', 'Agua', NULL, '@../images/pokemons/poliwhirl-front.png', 'poliwhirl.mp3', 36, 62),
(62, 'Poliwrath', 'Agua', 'Lucha', '@../images/pokemons/poliwrath-front.png', 'poliwrath.mp3', NULL, NULL),
(63, 'Abra', 'Psíquico', NULL, '@../images/pokemons/abra-front.png', 'abra.mp3', 16, 64),
(64, 'Kadabra', 'Psíquico', NULL, '@../images/pokemons/kadabra-front.png', 'kadabra.mp3', 36, 65),
(65, 'Alakazam', 'Psíquico', NULL, '@../images/pokemons/alakazam-front.png', 'alakazam.mp3', NULL, NULL),
(66, 'Machop', 'Lucha', NULL, '@../images/pokemons/machop-front.png', 'machop.mp3', 28, 67),
(67, 'Machoke', 'Lucha', NULL, '@../images/pokemons/machoke-front.png', 'machoke.mp3', 36, 68),
(68, 'Machamp', 'Lucha', NULL, '@../images/pokemons/machamp-front.png', 'machamp.mp3', NULL, NULL),
(69, 'Bellsprout', 'Planta', 'Veneno', '@../images/pokemons/bellsprout-front.png', 'bellsprout.mp3', 21, 70),
(70, 'Weepinbell', 'Planta', 'Veneno', '@../images/pokemons/weepinbell-front.png', 'weepinbell.mp3', 32, 71),
(71, 'Victreebel', 'Planta', 'Veneno', '@../images/pokemons/victreebel-front.png', 'victreebel.mp3', NULL, NULL),
(72, 'Tentacool', 'Agua', 'Veneno', '@../images/pokemons/tentacool-front.png', 'tentacool.mp3', 30, 73),
(73, 'Tentacruel', 'Agua', 'Veneno', '@../images/pokemons/tentacruel-front.png', 'tentacruel.mp3', NULL, NULL),
(74, 'Geodude', 'Roca', 'Tierra', '@../images/pokemons/geodude-front.png', 'geodude.mp3', 25, 75),
(75, 'Graveler', 'Roca', 'Tierra', '@../images/pokemons/graveler-front.png', 'graveler.mp3', 36, 76),
(76, 'Golem', 'Roca', 'Tierra', '@../images/pokemons/golem-front.png', 'golem.mp3', NULL, NULL),
(77, 'Ponyta', 'Fuego', NULL, '@../images/pokemons/ponyta-front.png', 'ponyta.mp3', 40, 78),
(78, 'Rapidash', 'Fuego', NULL, '@../images/pokemons/rapidash-front.png', 'rapidash.mp3', NULL, NULL),
(79, 'Slowpoke', 'Agua', 'Psíquico', '@../images/pokemons/slowpoke-front.png', 'slowpoke.mp3', 37, 80),
(80, 'Slowbro', 'Agua', 'Psíquico', '@../images/pokemons/slowbro-front.png', 'slowbro.mp3', NULL, NULL),
(81, 'Magnemite', 'Eléctrico', 'Acero', '@../images/pokemons/magnemite-front.png', 'magnemite.mp3', 30, 82),
(82, 'Magneton', 'Eléctrico', 'Acero', '@../images/pokemons/magneton-front.png', 'magneton.mp3', NULL, NULL),
(83, 'Farfetchd', 'Normal', 'Volador', '@../images/pokemons/farfetchd-front.png', 'farfetchd.mp3', NULL, NULL),
(84, 'Doduo', 'Normal', 'Volador', '@../images/pokemons/doduo-front.png', 'doduo.mp3', 31, 85),
(85, 'Dodrio', 'Normal', 'Volador', '@../images/pokemons/dodrio-front.png', 'dodrio.mp3', NULL, NULL),
(86, 'Seel', 'Agua', NULL, '@../images/pokemons/seel-front.png', 'seel.mp3', 34, 87),
(87, 'Dewgong', 'Agua', 'Hielo', '@../images/pokemons/dewgong-front.png', 'dewgong.mp3', NULL, NULL),
(88, 'Grimer', 'Veneno', NULL, '@../images/pokemons/grimer-front.png', 'grimer.mp3', 38, 89),
(89, 'Muk', 'Veneno', NULL, '@../images/pokemons/muk-front.png', 'muk.mp3', NULL, NULL),
(90, 'Shellder', 'Agua', NULL, '@../images/pokemons/shellder-front.png', 'shellder.mp3', 22, 91),
(91, 'Cloyster', 'Agua', 'Hielo', '@../images/pokemons/cloyster-front.png', 'cloyster.mp3', NULL, NULL),
(92, 'Gastly', 'Fantasma', 'Veneno', '@../images/pokemons/gastly-front.png', 'gastly.mp3', 25, 93),
(93, 'Haunter', 'Fantasma', 'Veneno', '@../images/pokemons/haunter-front.png', 'haunter.mp3', 36, 94),
(94, 'Gengar', 'Fantasma', 'Veneno', '@../images/pokemons/gengar-front.png', 'gengar.mp3', NULL, NULL),
(95, 'Onix', 'Roca', 'Tierra', '@../images/pokemons/onix-front.png', 'onix.mp3', NULL, NULL),
(96, 'Drowzee', 'Psíquico', NULL, '@../images/pokemons/drowzee-front.png', 'drowzee.mp3', 26, 97),
(97, 'Hypno', 'Psíquico', NULL, '@../images/pokemons/hypno-front.png', 'hypno.mp3', NULL, NULL),
(98, 'Krabby', 'Agua', NULL, '@../images/pokemons/krabby-front.png', 'krabby.mp3', 28, 99),
(99, 'Kingler', 'Agua', NULL, '@../images/pokemons/kingler-front.png', 'kingler.mp3', NULL, NULL),
(100, 'Voltorb', 'Eléctrico', NULL, '@../images/pokemons/voltorb-front.png', 'voltorb.mp3', 30, 101),
(101, 'Electrode', 'Eléctrico', NULL, '@../images/pokemons/electrode-front.png', 'electrode.mp3', NULL, NULL),
(102, 'Exeggcute', 'Planta', 'Psíquico', '@../images/pokemons/exeggcute-front.png', 'exeggcute.mp3', 38, 103),
(103, 'Exeggutor', 'Planta', 'Psíquico', '@../images/pokemons/exeggutor-front.png', 'exeggutor.mp3', NULL, NULL),
(104, 'Cubone', 'Tierra', NULL, '@../images/pokemons/cubone-front.png', 'cubone.mp3', 28, 105),
(105, 'Marowak', 'Tierra', NULL, '@../images/pokemons/marowak-front.png', 'marowak.mp3', NULL, NULL),
(106, 'Hitmonlee', 'Lucha', NULL, '@../images/pokemons/hitmonlee-front.png', 'hitmonlee.mp3', NULL, NULL),
(107, 'Hitmonchan', 'Lucha', NULL, '@../images/pokemons/hitmonchan-front.png', 'hitmonchan.mp3', NULL, NULL),
(108, 'Lickitung', 'Normal', NULL, '@../images/pokemons/lickitung-front.png', 'lickitung.mp3', NULL, NULL),
(109, 'Koffing', 'Veneno', NULL, '@../images/pokemons/koffing-front.png', 'koffing.mp3', 35, 110),
(110, 'Weezing', 'Veneno', NULL, '@../images/pokemons/weezing-front.png', 'weezing.mp3', NULL, NULL),
(111, 'Rhyhorn', 'Tierra', 'Roca', '@../images/pokemons/rhyhorn-front.png', 'rhyhorn.mp3', 42, 112),
(112, 'Rhydon', 'Tierra', 'Roca', '@../images/pokemons/rhydon-front.png', 'rhydon.mp3', NULL, NULL),
(113, 'Chansey', 'Normal', NULL, '@../images/pokemons/chansey-front.png', 'chansey.mp3', NULL, NULL),
(114, 'Tangela', 'Planta', NULL, '@../images/pokemons/tangela-front.png', 'tangela.mp3', NULL, NULL),
(115, 'Kangaskhan', 'Normal', NULL, '@../images/pokemons/kangaskhan-front.png', 'kangaskhan.mp3', NULL, NULL),
(116, 'Horsea', 'Agua', NULL, '@../images/pokemons/horsea-front.png', 'horsea.mp3', 32, 117),
(117, 'Seadra', 'Agua', NULL, '@../images/pokemons/seadra-front.png', 'seadra.mp3', NULL, NULL),
(118, 'Goldeen', 'Agua', NULL, '@../images/pokemons/goldeen-front.png', 'goldeen.mp3', 33, 119),
(119, 'Seaking', 'Agua', NULL, '@../images/pokemons/seaking-front.png', 'seaking.mp3', NULL, NULL),
(120, 'Staryu', 'Agua', NULL, '@../images/pokemons/staryu-front.png', 'staryu.mp3', 30, 121),
(121, 'Starmie', 'Agua', 'Psíquico', '@../images/pokemons/starmie-front.png', 'starmie.mp3', NULL, NULL),
(122, 'Mr. Mime', 'Psíquico', 'Hada', '@../images/pokemons/mr-mime-front.png', 'mr-mime.mp3', NULL, NULL),
(123, 'Scyther', 'Bicho', 'Volador', '@../images/pokemons/scyther-front.png', 'scyther.mp3', NULL, NULL),
(124, 'Jynx', 'Hielo', 'Psíquico', '@../images/pokemons/jynx-front.png', 'jynx.mp3', NULL, NULL),
(125, 'Electabuzz', 'Eléctrico', NULL, '@../images/pokemons/electabuzz-front.png', 'electabuzz.mp3', NULL, NULL),
(126, 'Magmar', 'Fuego', NULL, '@../images/pokemons/magmar-front.png', 'magmar.mp3', NULL, NULL),
(127, 'Pinsir', 'Bicho', NULL, '@../images/pokemons/pinsir-front.png', 'pinsir.mp3', NULL, NULL),
(128, 'Tauros', 'Normal', NULL, '@../images/pokemons/tauros-front.png', 'tauros.mp3', NULL, NULL),
(129, 'Magikarp', 'Agua', NULL, '@../images/pokemons/magikarp-front.png', 'magikarp.mp3', 20, 130),
(130, 'Gyarados', 'Agua', 'Volador', '@../images/pokemons/gyarados-front.png', 'gyarados.mp3', NULL, NULL),
(131, 'Lapras', 'Agua', 'Hielo', '@../images/pokemons/lapras-front.png', 'lapras.mp3', NULL, NULL),
(132, 'Ditto', 'Normal', NULL, '@../images/pokemons/ditto-front.png', 'ditto.mp3', NULL, NULL),
(133, 'Eevee', 'Normal', NULL, '@../images/pokemons/eevee-front.png', 'eevee.mp3', 32, 134),
(134, 'Vaporeon', 'Agua', NULL, '@../images/pokemons/vaporeon-front.png', 'vaporeon.mp3', NULL, NULL),
(135, 'Jolteon', 'Eléctrico', NULL, '@../images/pokemons/jolteon-front.png', 'jolteon.mp3', NULL, NULL),
(136, 'Flareon', 'Fuego', NULL, '@../images/pokemons/flareon-front.png', 'flareon.mp3', NULL, NULL),
(137, 'Porygon', 'Normal', NULL, '@../images/pokemons/porygon-front.png', 'porygon.mp3', NULL, NULL),
(138, 'Omanyte', 'Roca', 'Agua', '@../images/pokemons/omanyte-front.png', 'omanyte.mp3', 40, 139),
(139, 'Omastar', 'Roca', 'Agua', '@../images/pokemons/omastar-front.png', 'omastar.mp3', NULL, NULL),
(140, 'Kabuto', 'Roca', 'Agua', '@../images/pokemons/kabuto-front.png', 'kabuto.mp3', 40, 141),
(141, 'Kabutops', 'Roca', 'Agua', '@../images/pokemons/kabutops-front.png', 'kabutops.mp3', NULL, NULL),
(142, 'Aerodactyl', 'Roca', 'Volador', '@../images/pokemons/aerodactyl-front.png', 'aerodactyl.mp3', NULL, NULL),
(143, 'Snorlax', 'Normal', NULL, '@../images/pokemons/snorlax-front.png', 'snorlax.mp3', NULL, NULL),
(144, 'Articuno', 'Hielo', 'Volador', '@../images/pokemons/articuno-front.png', 'articuno.mp3', NULL, NULL),
(145, 'Zapdos', 'Eléctrico', 'Volador', '@../images/pokemons/zapdos-front.png', 'zapdos.mp3', NULL, NULL),
(146, 'Moltres', 'Fuego', 'Volador', '@../images/pokemons/moltres-front.png', 'moltres.mp3', NULL, NULL),
(147, 'Dratini', 'Dragón', NULL, '@../images/pokemons/dratini-front.png', 'dratini.mp3', 30, 148),
(148, 'Dragonair', 'Dragón', NULL, '@../images/pokemons/dragonair-front.png', 'dragonair.mp3', 55, 149),
(149, 'Dragonite', 'Dragón', 'Volador', '@../images/pokemons/dragonite-front.png', 'dragonite.mp3', NULL, NULL),
(150, 'Mewtwo', 'Psíquico', NULL, '@../images/pokemons/mewtwo-front.png', 'mewtwo.mp3', NULL, NULL),
(151, 'Mew', 'Psíquico', NULL, '@../images/pokemons/mew-front.png', 'mew.mp3', NULL, NULL);



CREATE TABLE MOVIMIENTOS(
ID_MOVIMIENTO INT PRIMARY KEY,
NOM_MOVIMIENTO VARCHAR(20) NOT NULL,
POTENCIA INT,
TIPO VARCHAR(20) NOT NULL,
ESTADO VARCHAR(20),
QUITA INT,
TURNOS INT,
MEJORA VARCHAR(20),
CANT_MEJORA INT,
NIVEL_APRENDIZAJE INT NOT NULL
);


CREATE TABLE MOVIMIENTOS_POKEMON(
ID_MOVIMIENTO INT,
ID_POKEMON INT,
ARCHIVO CHAR(1) NOT NULL
);

ALTER TABLE movimientos_pokemon ADD CONSTRAINT PK_ID_MOVIMIENTO_AND_NOM_MOVIMIENTO PRIMARY KEY (ID_MOVIMIENTO,ID_POKEMON);

ALTER TABLE MOVIMIENTOS_POKEMON ADD CONSTRAINT FK_ID_POKEMON FOREIGN KEY (ID_POKEMON) REFERENCES pokemon(ID_POKEMON),
ADD CONSTRAINT FK_ID_MOVIMIENTO FOREIGN KEY (ID_MOVIMIENTO) REFERENCES movimientos(ID_MOVIMIENTO);

CREATE TABLE COMBATE(
    ID_COMBATE INT PRIMARY KEY,
    FECHA_HORA TIMESTAMP NOT NULL,
    ID_ENTRENADOR INT
    );

ALTER TABLE COMBATE
ADD CONSTRAINT fk_combate_entrenador FOREIGN KEY (ID_ENTRENADOR) REFERENCES ENTRENADOR(ID_ENTRENADOR);


CREATE TABLE TURNO(
    ID_TURNO INT PRIMARY KEY,
    ACCION_ENTRENADOR VARCHAR(150) NOT NULL,
    ACCION_RIVAL VARCHAR(150) NOT NULL,
    ID_COMBATE INT
);

ALTER TABLE TURNO
ADD CONSTRAINT fk_turno_combate FOREIGN KEY (ID_COMBATE) REFERENCES COMBATE(ID_COMBATE);


CREATE TABLE OBJETO(
       ID_OBJETO INT,
       NOMBRE VARCHAR(20) NOT NULL,
       ATAQUE INT,
       AT_ESPECIAL INT,
       DEFENSA INT,
       DEF_ESPECIAL INT,
       VELOCIDAD INT,
       PRECIO INT NOT NULL
);

ALTER TABLE OBJETO ADD CONSTRAINT PK_ID_OBJETO PRIMARY KEY (ID_OBJETO);


CREATE TABLE MOCHILA(
ID_ENTRENADOR INT,
ID_OBJETO INT,
NUM_OBJETO INT NOT NULL
);

ALTER TABLE MOCHILA ADD CONSTRAINT PK_ID_ENTRENADOR_AND_ID_OBJETO PRIMARY KEY (ID_ENTRENADOR,ID_OBJETO);

ALTER TABLE MOCHILA ADD CONSTRAINT FK_ID_ENTRENADOR FOREIGN KEY (ID_ENTRENADOR) REFERENCES ENTRENADOR(ID_ENTRENADOR);

ALTER TABLE MOCHILA ADD CONSTRAINT FK_ID_OBJETO FOREIGN KEY (ID_OBJETO) REFERENCES OBJETO(ID_OBJETO);

ALTER TABLE POKEDEX ADD CONSTRAINT FK_NUM_POKEDEX_EVO FOREIGN KEY (NUM_POKEDEX_EVO) REFERENCES POKEDEX (NUM_POKEDEX);


UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/001.png' WHERE num_pokedex = 1;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/002.png' WHERE num_pokedex = 2;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/003.png' WHERE num_pokedex = 3;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/004.png' WHERE num_pokedex = 4;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/005.png' WHERE num_pokedex = 5;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/006.png' WHERE num_pokedex = 6;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/007.png' WHERE num_pokedex = 7;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/008.png' WHERE num_pokedex = 8;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/009.png' WHERE num_pokedex = 9;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/010.png' WHERE num_pokedex = 10;


UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/011.png' WHERE num_pokedex = 11;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/012.png' WHERE num_pokedex = 12;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/013.png' WHERE num_pokedex = 13;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/014.png' WHERE num_pokedex = 14;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/015.png' WHERE num_pokedex = 15;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/016.png' WHERE num_pokedex = 16;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/017.png' WHERE num_pokedex = 17;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/018.png' WHERE num_pokedex = 18;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/019.png' WHERE num_pokedex = 19;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/020.png' WHERE num_pokedex = 20;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/021.png' WHERE num_pokedex = 21;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/022.png' WHERE num_pokedex = 22;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/023.png' WHERE num_pokedex = 23;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/024.png' WHERE num_pokedex = 24;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/025.png' WHERE num_pokedex = 25;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/026.png' WHERE num_pokedex = 26;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/027.png' WHERE num_pokedex = 27;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/028.png' WHERE num_pokedex = 28;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/029.png' WHERE num_pokedex = 29;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/030.png' WHERE num_pokedex = 30;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/031.png' WHERE num_pokedex = 31;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/032.png' WHERE num_pokedex = 32;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/033.png' WHERE num_pokedex = 33;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/034.png' WHERE num_pokedex = 34;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/035.png' WHERE num_pokedex = 35;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/036.png' WHERE num_pokedex = 36;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/037.png' WHERE num_pokedex = 37;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/038.png' WHERE num_pokedex = 38;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/039.png' WHERE num_pokedex = 39;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/040.png' WHERE num_pokedex = 40;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/041.png' WHERE num_pokedex = 41;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/042.png' WHERE num_pokedex = 42;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/043.png' WHERE num_pokedex = 43;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/044.png' WHERE num_pokedex = 44;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/045.png' WHERE num_pokedex = 45;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/046.png' WHERE num_pokedex = 46;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/047.png' WHERE num_pokedex = 47;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/048.png' WHERE num_pokedex = 48;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/049.png' WHERE num_pokedex = 49;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/050.png' WHERE num_pokedex = 50;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/051.png' WHERE num_pokedex = 51;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/052.png' WHERE num_pokedex = 52;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/053.png' WHERE num_pokedex = 53;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/054.png' WHERE num_pokedex = 54;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/055.png' WHERE num_pokedex = 55;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/056.png' WHERE num_pokedex = 56;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/057.png' WHERE num_pokedex = 57;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/058.png' WHERE num_pokedex = 58;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/059.png' WHERE num_pokedex = 59;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/060.png' WHERE num_pokedex = 60;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/061.png' WHERE num_pokedex = 61;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/062.png' WHERE num_pokedex = 62;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/063.png' WHERE num_pokedex = 63;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/064.png' WHERE num_pokedex = 64;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/065.png' WHERE num_pokedex = 65;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/066.png' WHERE num_pokedex = 66;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/067.png' WHERE num_pokedex = 67;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/068.png' WHERE num_pokedex = 68;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/069.png' WHERE num_pokedex = 69;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/070.png' WHERE num_pokedex = 70;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/071.png' WHERE num_pokedex = 71;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/072.png' WHERE num_pokedex = 72;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/073.png' WHERE num_pokedex = 73;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/074.png' WHERE num_pokedex = 74;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/075.png' WHERE num_pokedex = 75;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/076.png' WHERE num_pokedex = 76;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/077.png' WHERE num_pokedex = 77;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/078.png' WHERE num_pokedex = 78;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/079.png' WHERE num_pokedex = 79;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/080.png' WHERE num_pokedex = 80;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/081.png' WHERE num_pokedex = 81;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/082.png' WHERE num_pokedex = 82;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/083.png' WHERE num_pokedex = 83;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/084.png' WHERE num_pokedex = 84;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/085.png' WHERE num_pokedex = 85;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/086.png' WHERE num_pokedex = 86;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/087.png' WHERE num_pokedex = 87;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/088.png' WHERE num_pokedex = 88;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/089.png' WHERE num_pokedex = 89;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/090.png' WHERE num_pokedex = 90;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/091.png' WHERE num_pokedex = 91;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/092.png' WHERE num_pokedex = 92;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/093.png' WHERE num_pokedex = 93;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/094.png' WHERE num_pokedex = 94;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/095.png' WHERE num_pokedex = 95;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/096.png' WHERE num_pokedex = 96;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/097.png' WHERE num_pokedex = 97;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/098.png' WHERE num_pokedex = 98;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/099.png' WHERE num_pokedex = 99;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/100.png' WHERE num_pokedex = 100;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/101.png' WHERE num_pokedex = 101;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/102.png' WHERE num_pokedex = 102;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/103.png' WHERE num_pokedex = 103;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/104.png' WHERE num_pokedex = 104;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/105.png' WHERE num_pokedex = 105;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/106.png' WHERE num_pokedex = 106;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/107.png' WHERE num_pokedex = 107;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/108.png' WHERE num_pokedex = 108;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/109.png' WHERE num_pokedex = 109;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/110.png' WHERE num_pokedex = 110;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/111.png' WHERE num_pokedex = 111;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/112.png' WHERE num_pokedex = 112;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/113.png' WHERE num_pokedex = 113;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/114.png' WHERE num_pokedex = 114;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/115.png' WHERE num_pokedex = 115;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/116.png' WHERE num_pokedex = 116;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/117.png' WHERE num_pokedex = 117;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/118.png' WHERE num_pokedex = 118;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/119.png' WHERE num_pokedex = 119;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/120.png' WHERE num_pokedex = 120;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/121.png' WHERE num_pokedex = 121;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/122.png' WHERE num_pokedex = 122;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/123.png' WHERE num_pokedex = 123;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/124.png' WHERE num_pokedex = 124;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/125.png' WHERE num_pokedex = 125;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/126.png' WHERE num_pokedex = 126;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/127.png' WHERE num_pokedex = 127;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/128.png' WHERE num_pokedex = 128;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/129.png' WHERE num_pokedex = 129;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/130.png' WHERE num_pokedex = 130;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/131.png' WHERE num_pokedex = 131;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/132.png' WHERE num_pokedex = 132;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/133.png' WHERE num_pokedex = 133;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/134.png' WHERE num_pokedex = 134;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/135.png' WHERE num_pokedex = 135;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/136.png' WHERE num_pokedex = 136;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/137.png' WHERE num_pokedex = 137;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/138.png' WHERE num_pokedex = 138;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/139.png' WHERE num_pokedex = 139;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/140.png' WHERE num_pokedex = 140;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/141.png' WHERE num_pokedex = 141;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/142.png' WHERE num_pokedex = 142;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/143.png' WHERE num_pokedex = 143;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/144.png' WHERE num_pokedex = 144;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/145.png' WHERE num_pokedex = 145;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/146.png' WHERE num_pokedex = 146;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/147.png' WHERE num_pokedex = 147;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/148.png' WHERE num_pokedex = 148;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/149.png' WHERE num_pokedex =149;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/150.png' WHERE num_pokedex =150;

UPDATE pokedex SET imagen = '/es/cesur/progprojectpok/images/pokemon/151.png' WHERE num_pokedex =151;

ALTER TABLE POKEMON ADD COLUMN VIDA_ACTUAL INT;

ALTER TABLE entrenador
MODIFY COLUMN ID_ENTRENADOR INT AUTO_INCREMENT;

ALTER TABLE pokemon
MODIFY COLUMN ID_POKEMON INT AUTO_INCREMENT;

-- Movimientos de ataque
INSERT INTO MOVIMIENTOS (ID_MOVIMIENTO, NOM_MOVIMIENTO, POTENCIA, TIPO, QUITA, NIVEL_APRENDIZAJE)
VALUES
(1, 'Lanzallamas', 90, 'FUEGO', NULL, 20),
(2, 'Rayo Burbuja', 65, 'AGUA', NULL, 15),
(3, 'Trueno', 110, 'ELÉCTRICO', NULL, 20),
(4, 'Corte', 70, 'NORMAL', NULL, 15),
(5, 'Hoja Afilada', 70, 'PLANTA', NULL, 20),
(6, 'Lanza Lodo', 60, 'TIERRA', NULL, 15),
(7, 'Placaje', 50, 'NORMAL', NULL, 5),
(8, 'Golpe Karate', 50, 'LUCHA', NULL, 25),
(9, 'Cabezazo', 70, 'NORMAL', NULL, 15),
(10, 'Ataque Alarido', 80, 'NORMAL', NULL, 20);

-- Movimientos de estado
INSERT INTO MOVIMIENTOS (ID_MOVIMIENTO, NOM_MOVIMIENTO, ESTADO, TURNOS, NIVEL_APRENDIZAJE)
VALUES
(11, 'Confusión', 'CONFUSO', 2, 15),
(12, 'Paralizador', 'PARALIZADO', 1, 20),
(13, 'Hipnosis', 'SOMNIOLENTO', 1, 25),
(14, 'Canto', 'SOMNIOLENTO', 2, 15),

-- Movimientos de mejora
INSERT INTO MOVIMIENTOS (ID_MOVIMIENTO, NOM_MOVIMIENTO, MEJORA, CANT_MEJORA, TURNOS, NIVEL_APRENDIZAJE)
VALUES
(15, 'Amnesia', 'DEFENSA_ESPECIAL', 2, 2, 15),
(16, 'Afilagarras', 'ATAQUE', 1, 1, 15),
(17, 'Danza Espada', 'ATAQUE', 2, 2, 25),
(18, 'Defensa Arenosa', 'DEFENSA', 1, 1, 20);

INSERT INTO OBJETO
VALUES
(1,'Pesa',NULL,NULL,NULL,NULL,NULL,80),
(2,'Pluma',NULL,NULL,NULL,NULL,NULL,80),
(3,'Chaleco',NULL,NULL,NULL,NULL,NULL,80),
(4,'Baston',NULL,NULL,NULL,NULL,NULL,80),
(5,'Pilas',NULL,NULL,NULL,NULL,NULL,80),
(6,'Eter',NULL,NULL,NULL,NULL,NULL,90),
(7,'anilloUnico',NULL,NULL,NULL,NULL,NULL,80),
(8,'pokeball',NULL,NULL,NULL,NULL,NULL,80);

ALTER TABLE MOVIMIENTOS
ADD TIPO_MOVIMIENTO VARCHAR(20);

UPDATE MOVIMIENTOS
SET TIPO_MOVIMIENTO =
    CASE
        WHEN ID_MOVIMIENTO BETWEEN 1 AND 10 THEN 'ATAQUE'
        WHEN ID_MOVIMIENTO BETWEEN 11 AND 14 THEN 'ESTADO'
        WHEN ID_MOVIMIENTO BETWEEN 15 AND 18 THEN 'MEJORA'
    END;

     ALTER TABLE MOVIMIENTOS
    ADD NUM_USOS INT;


ALTER TABLE OBJETO ADD URL VARCHAR(100);

UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\mancuerna-objeto.png' WHERE ID_OBJETO = 1;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\pluma-objeto.png' WHERE ID_OBJETO = 2;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\chaleco-objeto.png' WHERE ID_OBJETO = 3;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\baston-objeto.png' WHERE ID_OBJETO = 4;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\pilas-objeto.png' WHERE ID_OBJETO = 5;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\eter-objeto.png' WHERE ID_OBJETO = 6;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\anillo-objeto.png' WHERE ID_OBJETO = 7;
UPDATE OBJETO SET URL = 'src\\main\\resources\\es\\cesur\\progprojectpok\\images\\pokemons\\pokeball-objeto.png' WHERE ID_OBJETO = 8;









