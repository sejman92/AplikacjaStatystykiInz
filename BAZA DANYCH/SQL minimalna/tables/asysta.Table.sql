CREATE TABLE asysta
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_strzal INT, 
	id_zawodnik INT,
	id_mecz INT,
	CONSTRAINT id_strzal_asysta
	FOREIGN KEY (id_strzal)
	REFERENCES strzal(id),
	CONSTRAINT id_zawodnik_asysta
	FOREIGN KEY (id_zawodnik)
	REFERENCES zawodnik(id),
	CONSTRAINT id_mecz_asysta
	FOREIGN KEY (id_mecz)
	REFERENCES mecz(id)
)