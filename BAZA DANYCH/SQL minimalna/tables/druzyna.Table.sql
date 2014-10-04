CREATE TABLE druzyna
(
	id INT PRIMARY KEY IDENTITY(1,1),
	nazwa VARCHAR(256),
	id_uzytkownika INT,
	CONSTRAINT id_uzytkownika
	FOREIGN KEY (id_uzytkownika)
	REFERENCES uzytkownik (id)
)