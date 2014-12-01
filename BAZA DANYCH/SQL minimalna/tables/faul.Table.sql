CREATE TABLE faul
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_meczu INT,
	id_zawodnika_faulujacego INT,
	id_zawodnika_faulowanego INT,
	czas TIME,
	CONSTRAINT id_zawodnika_faulujacego_faul
	FOREIGN KEY (id_zawodnika_faulujacego)
	REFERENCES zawodnik(id),
	CONSTRAINT id_zawodnika_faulowanego_faul
	FOREIGN KEY (id_zawodnika_faulowanego)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczu_faul
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id)
)	