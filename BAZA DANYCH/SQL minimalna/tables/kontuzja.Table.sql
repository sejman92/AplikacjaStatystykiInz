CREATE TABLE kontuzja
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_zawodnika INT,
	id_meczu INT, 
	id_zmiany INT,
	czas TIME,
	czas_trwania VARCHAR(128),
	rodzaj VARCHAR(128),
	id_faulu INT,
	CONSTRAINT id_zawodnika_kontuzja
	FOREIGN KEY (id_zawodnika)
	REFERENCES zawodnik(id),
	CONSTRAINT id_meczu_kontuzja
	FOREIGN KEY (id_meczu)
	REFERENCES mecz(id),
	CONSTRAINT id_zmiany_kontuzja
	FOREIGN KEY (id_zmiany)
	REFERENCES zmiana(id),
	CONSTRAINT id_faulu_kontuzja
	FOREIGN KEY (id_faulu)
	REFERENCES faul(id)
)

