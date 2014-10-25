CREATE TABLE Assist
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        shot_id INT,
        comment TEXT,
        owner_id INT,
	FOREIGN KEY(player_id)
	REFERENCES Player(id),
	FOREIGN KEY(game_id)
	REFERENCES Game(id),
	FOREIGN KEY(shot_id)
	REFERENCES Shot(id),
	FOREIGN KEY (owner_id)
	REFERENCES User(id)
)