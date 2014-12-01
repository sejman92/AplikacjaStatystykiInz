CREATE TABLE obrony
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_zawodnika INT,
	id_meczu INT,
	czas TIME,
	CONSTRAINT id_zawodnika_obrony
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczu_obrony
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id)
)