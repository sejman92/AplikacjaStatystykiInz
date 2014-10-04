CREATE TABLE zmiana
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_meczu INT,
	id_zawodnika_schodzacego INT,
	id_zawodnika_wchodzacego INT,
	czas TIME,
	przyczyna VARCHAR(500),
	CONSTRAINT id_zawodnika_schodzacego_zmiana
	FOREIGN KEY (id_zawodnika_schodzacego)
	REFERENCES zawodnik(id),
	CONSTRAINT id_zawodnika_wchodzacego_zmiana
	FOREIGN KEY (id_zawodnika_wchodzacego)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczu_zmiana
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id),
)