CREATE TABLE kartka
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_meczu INT,
	id_zawodnika INT,
	czas TIME,
	kolor VARCHAR(10),
	CONSTRAINT id_meczu_kartka
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id),
	CONSTRAINT id_zawodnika_kartka
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik(id)
)