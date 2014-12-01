CREATE TABLE rzut_karny
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_zawodnika INT,
	id_meczu INT,
	czas TIME,
	id_strzalu INT,
	czy_bramka VARCHAR(10),
	CONSTRAINT id_zawodnika_rzut_karny
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczu_rzut_karny
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id),
	CONSTRAINT id_strzalu_rzut_karny
	FOREIGN KEY (id_strzalu)
	REFERENCES strzal(id)	
)