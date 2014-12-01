CREATE TABLE mecz
(
	id INT PRIMARY KEY IDENTITY(1,1),
	data DATE,
	miejsce VARCHAR(100),
	wynik VARCHAR(10),
	gole_strzelone INT,
	gole_stracone INT,
	przeciwnik VARCHAR(20)
)