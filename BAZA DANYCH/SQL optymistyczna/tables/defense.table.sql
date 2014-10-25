CREATE TABLE Defense
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        time TIME,
		owner_id INT,
        comment TEXT,
		FOREIGN KEY (player_id)
		REFERENCES Player(id),
		FOREIGN KEY (game_id)
		REFERENCES Game(id),
		FOREIGN KEY (owner_id)
		REFERENCES User(id)
)