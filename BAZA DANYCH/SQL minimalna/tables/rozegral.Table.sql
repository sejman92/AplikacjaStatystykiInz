CREATE TABLE rozegral
(
	id INT PRIMARY KEY IDENTITY(1,1),
	id_druzyny INT, 
	id_meczu INT,
	CONSTRAINT id_druzyny_rozegral
	FOREIGN KEY (id_druzyny)
	REFERENCES druzyna(id),
	CONSTRAINT id_meczu_rozegral
	FOREIGN KEY (id_meczu)
	REFERENCES mecz (id)
)