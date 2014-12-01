CREATE TABLE rzut_rozny
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_zawodnika INT,
	id_meczu INT,
	czas TIME,
	CONSTRAINT id_zawodnika_rzut_rozny
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczy_rzut_rozny
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id)
)