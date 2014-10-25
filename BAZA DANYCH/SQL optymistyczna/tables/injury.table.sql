CREATE TABLE Injury
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_injured_id INT,
        game_id INT,
        time TIME, 
        duration INT,
        kind CHAR(250),
        comment TEXT,
        owner_id INT,
        swap_id INT,
		FOREIGN KEY(player_id)
		REFERENCES Player(id),
		FOREIGN KEY(game_id)
		REFERENCES Game(id),
		FOREIGN KEY(owner_id)
		REFERENCES User(id),
		FOREIGN KEY(swap_id)
		REFERENCES Swap(id)
)