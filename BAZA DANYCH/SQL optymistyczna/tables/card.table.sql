CREATE TABLE Card
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        time INT, 
        kind CHAR(250),
        comment TEXT,
        owner_id INT,
        swap_id INT,
        faul_id INT,
		FOREIGN KEY (player_id)
		REFERENCES Player(id),
		FOREIGN KEY (game_id)
		REFERENCES Game(id),
		FOREIGN KEY (owner_id)
		REFERENCES User(id),
		FOREIGN KEY (swap_id)
		REFERENCES Swap(id),
		FOREIGN KEY (faul_id)
		REFERENCES Faul(id)
		
)