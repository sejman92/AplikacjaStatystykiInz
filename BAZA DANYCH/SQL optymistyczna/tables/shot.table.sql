CREATE TABLE Shot
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    player_id INT,
    game_id INT,
    comment TEXT,
    bodyPart CHAR(20),
	success CHAR(20),
    owner_id INT,
	corner BOOL,
	freekick BOOL,
	penalty BOOL,
	FOREIGN KEY (player_id)
	REFERENCES Player(id),
	FOREIGN KEY (game_id)
	REFERENCES Game(id),
	FOREIGN KEY (owner_id)
	REFERENCES User(id)
)