CREATE TABLE zawodnik
(
	id INT PRIMARY KEY IDENTITY(1,1),
	imie VARCHAR(50),
	nazwisko VARCHAR(100),
	numer INT,
	pozycja VARCHAR(20),
	id_druzyny INT,
	CONSTRAINT id_druzyny
	FOREIGN KEY (id_druzyny)
	REFERENCES druzyna(id)
)