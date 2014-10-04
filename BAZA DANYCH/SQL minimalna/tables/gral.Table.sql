CREATE TABLE gral
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_zawodnika INT,
	id_meczu INT,
	CONSTRAINT id_zawodnika_gral 
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik (id),
	CONSTRAINT id_meczu_gral
	FOREIGN KEY (id_meczu)
	REFERENCES mecz (id)
)