CREATE TABLE strzal
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_zawodnika INT, 
	efekt VARCHAR(20),
	id_meczu INT,

	CONSTRAINT id_zawodnika_strzal
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczu_strzal
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id)
)