CREATE TABLE Swap
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_in_id INT,
        player_out_id INT,
        game_id INT,
        comment TEXT,
        injury_id INT,
        owner_id INT,
		FOREIGN KEY (player_in_id)
		REFERENCES Player(id),
		FOREIGN KEY (player_out_id)
		REFERENCES Player(id),
		FOREIGN KEY (game_id)
		REFERENCES Game(id),
		FOREIGN KEY (owner_id)
		REFERENCES User(id)
)