CREATE TABLE Faul
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        game_id INT,
        player_victim_id INT,
        player_ofender_id INT,
        time Time,
        comment TEXT,
        owner_id INT,
        injury_id INT,
        card_id INT,
		FOREIGN KEY(game_id)
		REFERENCES Game(id),
		FOREIGN KEY(player_victim_id)
		REFERENCES Player(id),
		FOREIGN KEY(player_ofender_id)
		REFERENCES Player(id),
		FOREIGN KEY(owner_id)
		REFERENCES User(id),
		FOREIGN KEY(injury_id)
		REFERENCES Injury(id),
		FOREIGN KEY(card_id)
		REFERENCES Card(id)
)