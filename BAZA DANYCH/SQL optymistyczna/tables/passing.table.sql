CREATE TABLE Passing
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_passing_id INT,
        player_getting_id INT,
        game_id INT,
        time TIME,
        comment TEXT,
		owner_id INT,
        successful BOOL,
		FOREIGN KEY(player_passing_id)
		REFERENCES Player(id),
		FOREIGN KEY(player_getting_id)
		REFERENCES Player(id),
		FOREIGN KEY(game_id)
		REFERENCES Game(id),
		FOREIGN KEY(game_id)
		REFERENCES Game(id),
		FOREIGN KEY(owner_id)
		REFERENCES User(id)
)