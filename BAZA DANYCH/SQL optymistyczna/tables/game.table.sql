CREATE TABLE Game 
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        date DATE,
        place CHAR(150),
        lost_goals INT,
        scored_goals INT,
        oponent CHAR(150),
        owner_id INT,
		FOREIGN KEY (owner_id)
		REFERENCES User(id)
)