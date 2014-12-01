CREATE TABLE Played
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    team_id INT,
    game_id INT,
    owner_id INT,
	FOREIGN KEY (game_id)
	REFERENCES Game(id),
	FOREIGN KEY (owner_id)
	REFERENCES User(id)
)