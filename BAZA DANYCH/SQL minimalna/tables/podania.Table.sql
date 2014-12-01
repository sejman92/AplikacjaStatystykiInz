CREATE TABLE podania
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_meczu INT,
	id_zawodnika_podajacego INT,
	id_zawodnika_odbiorcy INT,
	czas TIME,
	udane VARCHAR(5),
	CONSTRAINT id_meczu_podania
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id),
	CONSTRAINT id_zawodnika_podajacego_podania
	FOREIGN KEY (id_zawodnika_podajacego)
	REFERENCES zawodnik(id),
	CONSTRAINT id_zawodnika_obiorcy_podania
	FOREIGN KEY (id_zawodnika_odbiorcy)
	REFERENCES zawodnik(id)
)