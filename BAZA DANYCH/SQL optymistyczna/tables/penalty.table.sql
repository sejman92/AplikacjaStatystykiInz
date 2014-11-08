CREATE TABLE Penalty
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
		success BOOL,
        game_id INT,
        time TIME, 
        shot_id INT,
        comment TEXT,
        owner_id INT,
		FOREIGN KEY(player_id)
		REFERENCES Player(id),
		FOREIGN KEY(game_id)
		REFERENCES Game(id),
		FOREIGN KEY (shot_id)
		REFERENCES Shot(id),
		FOREIGN KEY (owner_id)
		REFERENCES User(id)
)